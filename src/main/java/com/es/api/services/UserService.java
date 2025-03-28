package com.es.api.services;

import com.es.api.entities.DomainEntity;
import com.es.api.entities.RoleEntity;
import com.es.api.entities.UserEntity;
import com.es.api.messages.ErrorMessage;
import com.es.api.repositories.DomainRepository;
import com.es.api.repositories.RoleRepository;
import com.es.api.repositories.UserRepository;
import com.es.api.requests.UserRequest;
import com.es.api.responses.UserResponse;
import com.es.api.utils.LogsConstants;

import lombok.extern.slf4j.Slf4j;

import com.es.api.responses.PageResponse;

import java.util.Optional;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

    // ~ ATTRIBUTE(S) ---------------------------------------------------------

    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private DomainRepository domainRepo;

    // ~ CONSTANT(S) ----------------------------------------------------------

    private final String CLASS_NAME = this.getClass().getSimpleName();

    // ~ CONSTRUCTOR(S) -------------------------------------------------------

    public UserService(UserRepository userRepo, RoleRepository roleRepo, DomainRepository domainRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.domainRepo = domainRepo;
    }

    // ~ METHOD(S) ------------------------------------------------------------

    @Transactional(readOnly = true)
    public PageResponse<UserResponse> findAll(Pageable pageable) {
        StopWatch timer = new StopWatch();
        Page<UserResponse> page = userRepo.findAll(pageable).map(UserResponse::instance);
        String time = String.valueOf(timer.getDuration().toMillis());
        log.atInfo().addKeyValue(CLASS_NAME, LogsConstants.FIND_ALL).addKeyValue(LogsConstants.EXECUTION_TIME, time)
                .log();
        return new PageResponse<>(page);

    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        StopWatch timer = new StopWatch();
        UserEntity user = getUserById(id);
        String time = String.valueOf(timer.getDuration().toMillis());
        log.atInfo().addKeyValue(CLASS_NAME, LogsConstants.FIND_BY_ID).addKeyValue(LogsConstants.EXECUTION_TIME, time)
                .log();
        return UserResponse.instance(user);
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        DomainEntity domain = domainRepo
                .findById(request.domain())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.DOMAIN_NOT_FOUND));
        RoleEntity role = roleRepo
                .findById(request.role())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ROLE_NOT_FOUND));
        StopWatch timer = new StopWatch();
        UserEntity user = new UserEntity();
        mapFromRequestToUserEntity(user, request);
        user.setDomainId(domain.getId());
        user.setRoleId(role.getId());
        UserResponse response = UserResponse.instance(userRepo.save(user));
        String time = String.valueOf(timer.getDuration().toMillis());
        log.atInfo().addKeyValue(CLASS_NAME, LogsConstants.SAVE).addKeyValue(LogsConstants.EXECUTION_TIME, time).log();
        return response;
    }

    @Transactional
    public UserResponse fullyUpdate(UserRequest request, Long id) {
        StopWatch timer = new StopWatch();
        UserEntity user = getUserById(id);
        checkForDomainAndRoleModifications(request);
        mapFromRequestToUserEntity(user, request);
        UserResponse response = UserResponse.instance(userRepo.saveAndFlush(user));
        String time = String.valueOf(timer.getDuration().toMillis());
        log.atInfo().addKeyValue(CLASS_NAME, LogsConstants.FULLY_UPDATE).addKeyValue(LogsConstants.EXECUTION_TIME, time)
                .log();
        return response;
    }

    @Transactional
    public UserResponse partialUpdate(UserRequest request, Long id) {
        StopWatch timer = new StopWatch();
        UserEntity user = getUserById(id);
        checkForDomainAndRoleModifications(request);
        Optional.ofNullable(request.password()).ifPresent(pass -> {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(pass));
        });
        Optional.ofNullable(request.username()).ifPresent(user::setUsername);
        Optional.ofNullable(request.name()).ifPresent(user::setName);
        Optional.ofNullable(request.lastName()).ifPresent(user::setLastName);
        Optional.ofNullable(request.email()).ifPresent(user::setEmail);
        String time = String.valueOf(timer.getDuration().toMillis());
        log.atInfo().addKeyValue(CLASS_NAME, LogsConstants.PARTIAL_UPDATE)
                .addKeyValue(LogsConstants.EXECUTION_TIME, time).log();
        return UserResponse.instance(userRepo.saveAndFlush(user));
    }

    @Transactional
    public void delete(Long id) {
        StopWatch timer = new StopWatch();
        UserEntity user = getUserById(id);
        userRepo.delete(user);
        String time = String.valueOf(timer.getDuration().toMillis());
        log.atInfo().addKeyValue(CLASS_NAME, LogsConstants.DELETE).addKeyValue(LogsConstants.EXECUTION_TIME, time)
                .log();
    }

    private UserEntity getUserById(Long id) {
        return userRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }

    private void mapFromRequestToUserEntity(UserEntity user, UserRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(request.password()));

        user.setUsername(request.username());
        user.setName(request.name());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
    }

    private void checkForDomainAndRoleModifications(UserRequest request) {
        Optional.ofNullable(request.domain()).ifPresent(domainId -> {
            throw new IllegalArgumentException(ErrorMessage.DOMAIN_CANNOT_BE_MODIFIED);
        });

        Optional.ofNullable(request.role()).ifPresent(roleId -> {
            throw new IllegalArgumentException(ErrorMessage.ROLE_CANNOT_BE_MODIFIED);
        });
    }
}

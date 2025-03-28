package com.es.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.es.api.entities.RoleEntity;
import com.es.api.messages.ErrorMessage;

import com.es.api.repositories.RoleRepository;
import com.es.api.requests.RoleRequest;
import com.es.api.responses.PageResponse;
import com.es.api.responses.RoleResponse;

@Service
public class RoleService {

    private RoleRepository roleRepo;

    // ~ CONSTRUCTOR(S) ------------------------------------------

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    // ~ METHOD(S) ------------------------------------------

    @Transactional(readOnly = true)
    public PageResponse<RoleResponse> findAll(Pageable pageable) {
        Page<RoleResponse> page = roleRepo.findAll(pageable).map(RoleResponse::instance);
        return new PageResponse<>(page);
    }

    @Transactional(readOnly = true)
    public RoleResponse findById(Long roleId) {
        RoleEntity role = getRole(roleId);
        return RoleResponse.instance(role);
    }

    @Transactional
    public RoleResponse create(RoleRequest request) {
        RoleEntity role = new RoleEntity();
        mapFromRequestToRoleEntity(role, request);
        return RoleResponse.instance(roleRepo.saveAndFlush(role));
    }

    @Transactional
    public RoleResponse fullyUpdate(Long roleId, RoleRequest request) {
        RoleEntity role = getRole(roleId);
        mapFromRequestToRoleEntity(role, request);
        return RoleResponse.instance(roleRepo.saveAndFlush(role));
    }

    @Transactional
    public RoleResponse partialUpdate(Long roleId, RoleRequest request) {
        RoleEntity role = getRole(roleId);
        Optional.ofNullable(request.name()).ifPresent(role::setName);
        return RoleResponse.instance(roleRepo.saveAndFlush(role));
    }

    @Transactional
    public void delete(Long roleId) {
        RoleEntity role = getRole(roleId);
        roleRepo.delete(role);
    }

    private RoleEntity getRole(Long roleId) {
        return roleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ROLE_NOT_FOUND));
    }

    private void mapFromRequestToRoleEntity(RoleEntity role, RoleRequest request) {
        role.setName(request.name());
    }
}

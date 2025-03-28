package com.es.api.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.es.api.requests.RoleRequest;
import com.es.api.responses.PageResponse;
import com.es.api.responses.RoleResponse;
import com.es.api.services.RoleService;

@RestController
@RequestMapping("api/0/rest/roles")
public class RoleController {

    private RoleService roleService;

    // ~ CONSTRUCTOR(S) -------------------------------------------

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // ~ METHOD(S) --------------------------------------------------

    @GetMapping
    public PageResponse<RoleResponse> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int pageSize) {
        PageRequest pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return roleService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public RoleResponse get(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse post(@RequestBody RoleRequest role) {
        return roleService.create(role);
    }

    @PutMapping("/{id}")
    public RoleResponse put(@PathVariable Long id, @RequestBody RoleRequest role) {
        return roleService.fullyUpdate(id, role);
    }

    @PatchMapping("/{id}")
    public RoleResponse patch(@PathVariable Long id, @RequestBody RoleRequest role) {
        return roleService.partialUpdate(id, role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}

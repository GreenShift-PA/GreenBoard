package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Role;

import java.util.Arrays;

public class RoleService extends BaseCrudService<Role> {

    public RoleService(String baseUrl) {
        super(baseUrl);
    }

    public static void main(String[] args) {
        RoleService roleService = new RoleService("http://localhost:3000/api/v1/roles");
        String roleId = "c487fec0-85f3-4246-abb6-5d48677b4a72";

        Role role = roleService.getById(roleId, Role.class);
        System.out.println("Role: " + role);

        Role[] roles = roleService.getAll(Role[].class);
        System.out.println("All Roles: " + Arrays.toString(roles));
    }

    @Override
    protected String getEntityId(Role entity) {
        return entity.getId();
    }
}

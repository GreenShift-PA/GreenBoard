package com.greenshift.greenboard.services;

import com.google.gson.reflect.TypeToken;
import com.greenshift.greenboard.models.entities.Role;

import java.util.ArrayList;
import java.util.Arrays;

public class RoleService extends BaseCrudService<Role> {

    public RoleService() {
        super("http://localhost:3000/api/v1/roles");
    }

    public static void main(String[] args) {
        RoleService roleService = new RoleService();
        String roleId = "c487fec0-85f3-4246-abb6-5d48677b4a72";

        Role role = roleService.getById(roleId);
        System.out.println("Role: " + role);

        Role[] roles = roleService.getAll();
        System.out.println("All Roles: " + Arrays.toString(roles));
    }

    @Override
    protected String getEntityId(Role entity) {
        return entity.getId();
    }

    @Override
    protected TypeToken<Role> getTypeToken() {
        return new TypeToken<>() {
        };
    }

    @Override
    protected TypeToken<Role[]> getArrayTypeToken() {
        return new TypeToken<>() {
        };
    }
}

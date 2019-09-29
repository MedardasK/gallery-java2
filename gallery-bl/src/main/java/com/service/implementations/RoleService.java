package com.service.implementations;

import com.DAO.IRoleRep;
import com.entity.Role;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRep roleRep;

    public Role findByRoleName(String name) {
        Role role = roleRep.findByName(name);
        if(role == null){
            role = new Role();
            role.setName(name);
            roleRep.save(role);
            return roleRep.findByName(name);
        } else {
            return role;
        }

    }
}

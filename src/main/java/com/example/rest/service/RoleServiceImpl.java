package com.example.rest.service;

import com.example.rest.dao.RoleDao;
import com.example.rest.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        roleDao.deleteById(id);
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = null;
        Optional<Role> optional = roleDao.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleDao.findByRole(roleName);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}


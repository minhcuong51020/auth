package com.hmc.auth.config;

import com.hmc.auth.entity.Role;
import com.hmc.auth.entity.User;
import com.hmc.auth.repository.RoleRepository;
import com.hmc.auth.repository.UserRepository;
import com.hmc.auth.support.RoleEnum;
import com.hmc.common.util.IdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SeedDatabaseRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initAdmin();
        initUserRole();
    }

    @Transactional
    public void initAdmin() {
        Role role = initAdminRole();
        User user = new User();
        String username = "admin";
        if(Objects.nonNull(this.userRepository.findUserByUsername(username))) {
            return;
        }
        user.setId(IdUtils.nextId());
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode("admin"));
        user.setFullName("admin");
        user.setRoleId(role.getId());
        this.userRepository.save(user);
    }

    @Transactional
    public Role initAdminRole() {
        Role role = this.roleRepository.findRoleByName(RoleEnum.ADMIN.getName());
        if(Objects.nonNull(role)) {
            return role;
        }
        role = new Role();
        role.setId(IdUtils.nextId());
        role.setName(RoleEnum.ADMIN.getName());
        this.roleRepository.save(role);
        return role;
    }

    @Transactional
    public void initUserRole() {
        Role role = this.roleRepository.findRoleByName(RoleEnum.USER.getName());
        if(!Objects.nonNull(role)) {
            role = new Role();
            role.setId(IdUtils.nextId());
            role.setName(RoleEnum.USER.getName());
            this.roleRepository.save(role);
        }
    }
}

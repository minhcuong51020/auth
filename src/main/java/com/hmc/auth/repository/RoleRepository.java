package com.hmc.auth.repository;

import com.hmc.auth.entity.Role;
import com.hmc.auth.support.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findRoleByName(String name);

}

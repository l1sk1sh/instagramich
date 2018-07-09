package com.multiheaded.webapp.repository;

import com.multiheaded.webapp.model.main.Role;
import com.multiheaded.webapp.model.main.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

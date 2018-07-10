package com.multiheaded.webapp.model.main.repo;

import com.multiheaded.webapp.model.main.domain.Role;
import com.multiheaded.webapp.model.main.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

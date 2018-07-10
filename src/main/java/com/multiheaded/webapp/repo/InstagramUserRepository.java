package com.multiheaded.webapp.repo;

import com.multiheaded.webapp.domain.InstagramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstagramUserRepository extends JpaRepository<InstagramUser, Long> {
    Optional<InstagramUser> findByUsername(String username);
}
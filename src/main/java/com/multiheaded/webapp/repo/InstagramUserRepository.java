package com.multiheaded.webapp.repo;

import com.multiheaded.webapp.domain.InstagramUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstagramUserRepository extends JpaRepository<InstagramUser, Long> {
    Optional<InstagramUser> findByUsername(String username);

    Page<InstagramUser> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long instaUserId);

    List<InstagramUser> findByIdIn(List<Long> instaUserIds);

    List<InstagramUser> findByIdIn(List<Long> instaUserIds, Sort sort);
}
package com.multiheaded.webapp.repo;

import com.multiheaded.webapp.domain.SignedInstagramUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignedInstagramUserRepository extends JpaRepository<SignedInstagramUser, Long> {

    Page<SignedInstagramUser> findByFollowers(Long userId, Pageable pageable);

    long countByFollowers(Long instaUserId);

    Page<SignedInstagramUser> findByFollowings(Long userId, Pageable pageable);

    long countByFollowings(Long instaUserId);

    Page<SignedInstagramUser> findByCreatedBy(Long userId, Pageable pageable);

    List<SignedInstagramUser> findByIdIn(List<Long> instaUserIds);

    List<SignedInstagramUser> findByIdIn(List<Long> instaUserIds, Sort sort);
}

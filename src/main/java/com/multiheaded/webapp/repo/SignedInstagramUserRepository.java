package com.multiheaded.webapp.repo;

import com.multiheaded.webapp.domain.InstagramUser;
import com.multiheaded.webapp.domain.SignedInstagramUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SignedInstagramUserRepository extends JpaRepository<SignedInstagramUser, Long> {

    Page<SignedInstagramUser> findByFollowers(Long userId, Pageable pageable);

    long countByFollowers(Long instaUserId);

    Page<SignedInstagramUser> findByCreatedBy(Long userId, Pageable pageable);

    Set<SignedInstagramUser> findByIdIn(List<Long> instaUserIds);

    Set<SignedInstagramUser> findByIdIn(List<Long> instaUserIds, Sort sort);

    @Query("SELECT f FROM SignedInstagramUser siu " +
            "JOIN siu.followers f JOIN siu.instagramUser iu1 WHERE iu1.username = :username")
    Set<InstagramUser> findFollowersBySignedUsername(@Param("username") String username);

    @Query("SELECT f FROM SignedInstagramUser siu " +
            "JOIN siu.followings f JOIN siu.instagramUser iu1 WHERE iu1.username = :username")
    Set<InstagramUser> findFollowingsBySignedUsername(@Param("username") String username);

}
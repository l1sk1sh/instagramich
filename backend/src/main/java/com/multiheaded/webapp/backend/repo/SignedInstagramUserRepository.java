package com.multiheaded.webapp.backend.repo;

import com.multiheaded.webapp.backend.domain.InstagramUser;
import com.multiheaded.webapp.backend.domain.SignedInstagramUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SignedInstagramUserRepository extends JpaRepository<SignedInstagramUser, Long> {

    Page<SignedInstagramUser> findByFollowers(Long userId, Pageable pageable);

    long countByFollowers(Long instaUserId);

    Page<SignedInstagramUser> findByCreatedBy(Long userId, Pageable pageable);

    Set<SignedInstagramUser> findByIdIn(List<Long> instaUserIds);

    Set<SignedInstagramUser> findByIdIn(List<Long> instaUserIds, Sort sort);

    @Query(value = "SELECT siu FROM SignedInstagramUser siu " +
            "JOIN siu.iUser iu WHERE iu.username = :sUsername AND siu.createdBy = :userId")
    Optional<SignedInstagramUser> findBySUsernameAndUserId(@Param("userId") Long userId,
                                                           @Param("sUsername") String sUsername);

    @Query("SELECT f FROM SignedInstagramUser siu " +
            "JOIN siu.followers f JOIN siu.iUser iu1 WHERE iu1.username = :sUsername")
    Set<InstagramUser> findFollowersBySUsername(@Param("sUsername") String sUsername);

    @Query("SELECT f FROM SignedInstagramUser siu " +
            "JOIN siu.followings f JOIN siu.iUser iu1 WHERE iu1.username = :sUsername")
    Set<InstagramUser> findFollowingsBySUsername(@Param("sUsername") String sUsername);

}
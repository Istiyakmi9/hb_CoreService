package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.JobRequirement;
import com.bot.coreservice.entity.AppliedPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppliedPostsRepository  extends JpaRepository<AppliedPosts, Long> {

    @Query(value = "select l.* from applied_posts l order by l.AppliedPostsId desc limit 1", nativeQuery = true)
    AppliedPosts getLastAppliedPost();

    @Query(value = "select l.* from applied_posts l where l.PostId = :postId and l.UserId = :userId", nativeQuery = true)
    AppliedPosts existingAppliedPostBy(@Param("postId") long postId, @Param("userId") long userId);
}
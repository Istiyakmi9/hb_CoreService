package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.JobRequirement;
import com.bot.coreservice.entity.LikedPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikedPostsRepository  extends JpaRepository<LikedPosts, Long> {

    @Query(value = "select l.* from liked_posts l order by l.LikedPostsId desc limit 1", nativeQuery = true)
    LikedPosts getLastLikedPost();

    @Query(value = "select l.* from liked_posts l where l.PostId = :postId and l.UserId = :userId", nativeQuery = true)
    LikedPosts existingLikedPostBy(@Param("postId") long postId, @Param("userId") long userId);
}
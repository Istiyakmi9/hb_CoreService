package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.SavedPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SavedPostsRepository extends JpaRepository<SavedPosts, Long> {

    @Query(value = "select sp.* from saved_posts sp order by sp.SavedPostsId desc limit 1", nativeQuery = true)
    SavedPosts getLastSavedPost();

    @Query(value = "select sp.* from saved_posts sp where sp.PostId = :postId and sp.UserId = :userId", nativeQuery = true)
    SavedPosts existingSavedPostBy(@Param("postId") long postId, @Param("userId") long userId);
}
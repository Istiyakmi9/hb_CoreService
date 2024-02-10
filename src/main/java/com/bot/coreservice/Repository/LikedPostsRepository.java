package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.LikedPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedPostsRepository  extends JpaRepository<LikedPosts, Long> {

    
}

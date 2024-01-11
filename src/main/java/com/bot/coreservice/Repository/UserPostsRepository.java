package com.bot.coreservice.Repository;


import com.bot.coreservice.entity.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostsRepository extends JpaRepository<UserPosts, Long> {

    @Query(nativeQuery = true, value = "select up.* from userposts up order by up.UserPostId desc limit 1")
    UserPosts getLastUserPostRecord();

}

package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.User;
import com.bot.coreservice.entity.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocument, Long> {

    @Query(value = "select e.* from user_document e order by e.UserId desc limit 1", nativeQuery = true)
    UserDocument getLastUserDocumentId();
}

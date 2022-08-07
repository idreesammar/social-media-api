package com.cooksysteam1.socialmedia.repository;

import com.cooksysteam1.socialmedia.entity.Hashtag;
import com.cooksysteam1.socialmedia.entity.User;
import com.cooksysteam1.socialmedia.entity.resource.Credentials;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByCredentials(Credentials credentials);

    boolean existsUserByCredentialsAndDeletedFalse(Credentials credentials);

    List<User> findAllUsersByDeletedFalse();

    Optional<User> findUserByCredentials_UsernameAndDeletedFalse(String username);
    
    Optional<User> findUserByCredentials_UsernameAndCredentials_PasswordAndDeletedFalse(String username, String password);

    boolean existsByCredentials_UsernameAndDeletedFalse(String username);

    boolean existsByCredentials_Username(String username);
}

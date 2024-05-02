package com.patrick.ohwoonwan.repository;

import com.patrick.ohwoonwan.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
        boolean existsByUserId(String userId);
        UserEntity findByUserId(String userId);
}

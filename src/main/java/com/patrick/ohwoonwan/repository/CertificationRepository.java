package com.patrick.ohwoonwan.repository;

import com.patrick.ohwoonwan.domain.Certification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/
@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
    Certification findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);
}

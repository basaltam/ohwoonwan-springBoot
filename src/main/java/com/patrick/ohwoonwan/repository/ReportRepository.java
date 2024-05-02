package com.patrick.ohwoonwan.repository;

import com.patrick.ohwoonwan.domain.ReportEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/
@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    ReportEntity findBywId(Integer wId);
    @Transactional
    void deleteBywId(Integer wId);

    List<ReportEntity> findAll();

}

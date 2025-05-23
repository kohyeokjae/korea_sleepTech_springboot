package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
    List<UploadFile> findByTargetTypeAndTargetId(String targetType, Long targetId);
}

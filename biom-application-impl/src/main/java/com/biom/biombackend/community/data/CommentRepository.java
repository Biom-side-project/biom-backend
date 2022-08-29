package com.biom.biombackend.community.data;

import com.biom.biombackend.biom.data.KoreaRegionCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByRegionCode(KoreaRegionCode regionCodeEntity);
}
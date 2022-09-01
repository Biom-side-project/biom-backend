package com.biom.biombackend.community.data;

import com.biom.biombackend.biom.data.KoreaRegionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    /*
    * 생성일자를 기준으로 내림차순 정렬한다.
    * 개수는 상위 10개를 뽑는다. */
    List<Comment> findTop10ByRegionCodeOrderByCreatedAtDesc(KoreaRegionCode regionCodeEntity);
    /*
    * 생성일자를 기준으로 내림차순 정렬한다.
    * 개수는 Pageable 을 통해서 지정된다.*/
    Page<Comment> findByRegionCodeOrderByCreatedAtDesc(KoreaRegionCode regionCode, Pageable pageable);
}

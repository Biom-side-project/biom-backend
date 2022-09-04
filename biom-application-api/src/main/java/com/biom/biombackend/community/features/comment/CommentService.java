package com.biom.biombackend.community.features.comment;

public interface CommentService {
    LeaveCommentResponse handle(LeaveComment command);
    
    /*
    * 페이징이 가능하도록 구현한다.
    * org.springframework.data.domain.Pageable 인터페이스를 이 메서드에 직접 시그니처로 받으면
    * api 모듈에 불필요한 의존성이 생기기 때문에, 커맨드 객체에 page, size 를 받도록 해서
    * PageRequest 객체를 직접 만드는 방식을 사용한다. */
    GetCommentsInARegionResponse handle(GetCommentsInARegion command);
    
    void handle(LikeComment command);
}

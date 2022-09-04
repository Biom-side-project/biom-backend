package com.biom.biombackend.community.web;

import com.biom.biombackend.community.features.comment.CommentService;
import com.biom.biombackend.community.features.comment.GetCommentsInARegion;
import com.biom.biombackend.community.features.comment.LeaveComment;
import com.biom.biombackend.community.features.comment.LikeComment;
import com.biom.biombackend.users.features.jwt.AccessToken;
import com.biom.biombackend.users.features.jwt.JwtManager;
import com.biom.biombackend.users.web.dto.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtManager jwtManager;
    private final MessageSource ms;
    
    @PostMapping("/api/v1/community/comment")
    public ResponseEntity<SuccessResponseBody> postComment(@AccessToken String accessToken,
                                                           @Valid @RequestBody LeaveCommentRequest request,
                                                           HttpServletRequest httpRequest) {
        return ResponseEntity.ok().body(SuccessResponseBody.builder().message(ms.getMessage("community.comment.posted", null, httpRequest.getLocale()))
                                                           .data(commentService.handle(LeaveComment.builder()
                                                                                 .content(request.getContent())
                                                                                 .userId(jwtManager.resolveUserId(
                                                                                         accessToken))
                                                                                 .regionCode(request.getRegionCode())
                                                                                 .build())).build());
    }
    
    @GetMapping("/api/v1/community/comments")
    public ResponseEntity<SuccessResponseBody> getComments(@RequestParam Long regionCode,
                                                           @Nullable @RequestParam(defaultValue = "0") Integer page,
                                                           @Nullable @RequestParam(defaultValue = "10") Integer size,
                                                           HttpServletRequest httpRequest) {
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                           .message(ms.getMessage("community.comment.comments", null, httpRequest.getLocale()))
                                                           .data(commentService.handle(GetCommentsInARegion.builder().page(page)
                                                                                          .size(size)
                                                                                          .regionCode(regionCode)
                                                                                          .build())).build());
    }
    
    @PostMapping("/api/v1/community/comment/like")
    public ResponseEntity<SuccessResponseBody> likeComment(@Valid @RequestBody LikeCommentRequest request,
                                                           HttpServletRequest httpRequest){
        commentService.handle(LikeComment.builder().commentId(UUID.fromString(request.getCommentId())).build());
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                           .message(ms.getMessage("community.comment.liked", null, httpRequest.getLocale()))
                                                           .build());
    }
}

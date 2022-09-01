package com.biom.biombackend.community.features.comment;

import com.biom.biombackend.biom.data.KoreaRegionCode;
import com.biom.biombackend.biom.data.KoreaRegionCodeRepository;
import com.biom.biombackend.biom.features.region.GetRegionCodeResponse;
import com.biom.biombackend.community.data.Comment;
import com.biom.biombackend.community.data.CommentRepository;
import com.biom.biombackend.community.exceptions.CommentNotFoundException;
import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import com.biom.biombackend.users.exceptions.RegionCodeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class DefaultCommentService implements CommentService {
    
    private final KoreaRegionCodeRepository regionCodeRepository;
    private final BiomUserRepository userRepository;
    private final CommentRepository commentRepository;
    
    @Override
    @Transactional
    public UUID handle(LeaveComment command) {
        log.debug("handling command: {}", command);
        Long regionCode = command.getRegionCode();
        KoreaRegionCode region = regionCodeRepository.getReferenceById(regionCode);
        
        UUID commentId = UUID.randomUUID();
        BiomUser user = userRepository.getReferenceById(command.getUserId());
        Comment comment = Comment.builder().commentId(commentId).user(user).content(command.getContent()).likes(0).regionCode(region).build();
        Comment savedComment = commentRepository.save(comment);
        log.info("코멘트를 저장하였습니다.: {}", savedComment);
        return savedComment.getCommentId();
    }
    
    @Override
    @Transactional
    public GetCommentsInARegionResponse handle(GetCommentsInARegion command) {
        log.debug("handling command: {}", command);
        Long regionCode = command.getRegionCode();
        KoreaRegionCode region = regionCodeRepository.findById(regionCode)
                                                     .orElseThrow(RegionCodeNotFoundException::new);
        List<Comment> commentEntities = commentRepository.findTop10ByRegionCodeOrderByCreatedAtDesc(region);
        GetCommentsInARegionResponse response = createGetCommentsInARegionResponse(region, commentEntities);
        log.info("코멘트들을 반환합니다.: {}", response);
        return response;
    }
    
    @Override
    @Transactional
    public void handle(LikeComment command) {
        UUID commentId = command.getCommentId();
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.incrementLikes();
    }
    
    private GetCommentsInARegionResponse createGetCommentsInARegionResponse(KoreaRegionCode region,
                                                                            List<Comment> commentEntities) {
        return GetCommentsInARegionResponse.builder()
                                           .comments(convertCommentEntitiesIntoSingleComments(commentEntities))
                                           .region(createGetRegionCodeResponse(region))
                                           .total(commentEntities.size())
                                           .build();
    }
    
    private List<SingleComment> convertCommentEntitiesIntoSingleComments(List<Comment> commentEntities) {
        return commentEntities
                          .stream()
                          .map(createSingleCommentWithAEntity())
                          .sorted(Comparator.comparing(SingleComment::getCreatedAt).reversed())
                          .collect(Collectors.toList());
    }
    
    private Function<Comment, SingleComment> createSingleCommentWithAEntity() {
        return entity -> SingleComment.builder()
                                  .commentId(entity.getCommentId())
                                  .content(entity.getContent())
                                  .createdAt(entity.getCreatedAt())
                                  .name(entity.getUser().getNickname())
                                  .likes(Optional.ofNullable(entity.getLikes()).orElse(0))
                                  .build();
    }
    
    private GetRegionCodeResponse createGetRegionCodeResponse(KoreaRegionCode region) {
        return GetRegionCodeResponse.builder().regionCode(region.getRegionCode())
                                    .sidoName(region.getSidoName())
                                    .sigunguName(region.getSigunguName())
                                    .eupmyeondongName(region.getEupmyeondongName())
                                    .dongliName(region.getDongliName()).build();
    }
}

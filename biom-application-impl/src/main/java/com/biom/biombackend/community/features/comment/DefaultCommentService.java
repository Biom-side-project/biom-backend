package com.biom.biombackend.community.features.comment;

import com.biom.biombackend.biom.data.KoreaRegionCode;
import com.biom.biombackend.biom.data.KoreaRegionCodeRepository;
import com.biom.biombackend.biom.features.region.GetRegionCodeResponse;
import com.biom.biombackend.community.data.Comment;
import com.biom.biombackend.community.data.CommentRepository;
import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import com.biom.biombackend.users.exceptions.RegionCodeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
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
        Comment comment = Comment.builder().commentId(commentId).user(user).content(command.getContent())
                                 .regionCode(region).build();
        Comment savedComment = commentRepository.save(comment);
        log.info("saved comment: {}", savedComment);
        return savedComment.getCommentId();
    }
    
    @Override
    @Transactional
    public GetCommentsInARegionResponse handle(GetCommentsInARegion command) {
        log.debug("handling command: {}", command);
        Long regionCode = command.getRegionCode();
        KoreaRegionCode region = regionCodeRepository.findById(regionCode)
                                                     .orElseThrow(RegionCodeNotFoundException::new);
        List<Comment> commentEntities = commentRepository.findAllByRegionCode(region).subList(0, 10);
        GetCommentsInARegionResponse response = createGetCommentsInARegionResponse(region, commentEntities);
        log.info("코멘트들을 반환합니다.: {}", response);
        return response;
    }
    
    private GetCommentsInARegionResponse createGetCommentsInARegionResponse(KoreaRegionCode region,
                                                                            List<Comment> commentEntities) {
        return GetCommentsInARegionResponse.builder()
                                           .comments(commentEntities
                                                             .stream()
                                                             .map(entity -> SingleComment.builder()
                                                                                       .commentId(entity.getCommentId())
                                                                                       .content(entity.getContent())
                                                                                       .createdAt(entity.getCreatedAt())
                                                                                       .name(entity.getUser().getNickname())
                                                                                       .likes(0).build())
                                                             .sorted(Comparator.comparing(SingleComment::getCreatedAt).reversed())
                                                             .collect(Collectors.toList()))
                                           .region(GetRegionCodeResponse.builder().regionCode(region.getRegionCode())
                                                                        .sidoName(region.getSidoName())
                                                                        .sigunguName(region.getSigunguName())
                                                                        .eupmyeondongName(region.getEupmyeondongName())
                                                                        .dongliName(region.getDongliName()).build())
                                           .total(commentEntities.size())
                                           .build();
    }
}

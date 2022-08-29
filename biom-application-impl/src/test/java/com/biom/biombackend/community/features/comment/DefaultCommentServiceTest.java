package com.biom.biombackend.community.features.comment;

import com.biom.biombackend.BiomApplicationConfiguration;
import com.biom.biombackend.biom.data.KoreaRegionCode;
import com.biom.biombackend.biom.data.KoreaRegionCodeRepository;
import com.biom.biombackend.community.data.Comment;
import com.biom.biombackend.community.data.CommentRepository;
import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = BiomApplicationConfiguration.class)
class DefaultCommentServiceTest {
    
    @Autowired private CommentService commentService;
    @Autowired private CommentRepository commentRepository;
    
    @Autowired private BiomUserRepository userRepository;
    @Autowired private KoreaRegionCodeRepository regionCodeRepository;
    
    private Long regionCode = 1159010700L;
    private String content = "날씨가 좋다!!!";
    private Long userId = 1L;
    private UUID commentId;
    
    @BeforeEach
    void before() {
        BiomUser user = BiomUser.builder().userId(userId).build();
        userRepository.save(user);
        KoreaRegionCode region = KoreaRegionCode.builder().regionCode(regionCode).build();
        regionCodeRepository.save(region);
    }
    
    @Test
    @DisplayName("코멘트를 남길 수 있다.")
    void test01() {
        // given
        LeaveComment command = LeaveComment.builder().regionCode(regionCode).content(content).userId(userId).build();
        // when
        commentId = commentService.handle(command);
    
        // then
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        assertThat(optionalComment.isPresent()).isTrue();
        Comment comment = optionalComment.get();
        assertThat(comment.getCommentId()).isEqualTo(commentId);
    }
    
    @Test
    @DisplayName("comment 들을 가져올 수 있다.")
    void test02() {
        // given
        LeaveComment command = LeaveComment.builder().regionCode(regionCode).content(content).userId(userId).build();
        int times = 10;
        ArrayList<UUID> uuids = new ArrayList<>();
        
        for (int i = 0; i < times; i++) {
            UUID result = commentService.handle(command);
            uuids.add(result);
        }
        GetCommentsInARegion command2 = GetCommentsInARegion.builder().regionCode(regionCode).build();
        // when
        GetCommentsInARegionResponse response = commentService.handle(command2);
    
        // then
        assertThat(response.getTotal()).isEqualTo(times);
    }
    
    @AfterEach
    void after(){
        if (commentId != null) {
            commentRepository.deleteById(commentId);
        }
    }
}
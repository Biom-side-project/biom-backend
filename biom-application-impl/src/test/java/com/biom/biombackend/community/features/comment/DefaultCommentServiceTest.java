package com.biom.biombackend.community.features.comment;

import com.biom.biombackend.BiomApplicationConfiguration;
import com.biom.biombackend.biom.data.KoreaRegionCode;
import com.biom.biombackend.biom.data.KoreaRegionCodeRepository;
import com.biom.biombackend.community.data.Comment;
import com.biom.biombackend.community.data.CommentRepository;
import com.biom.biombackend.community.exceptions.CommentNotFoundException;
import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TestTransaction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    private static int timeAmount = 0;
    private static long freeMemory = 0L;
    
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
        UUID commentId = commentService.handle(command).getCommentId();
    
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
            UUID result = commentService.handle(command).getCommentId();
            uuids.add(result);
        }
        GetCommentsInARegion command2 = GetCommentsInARegion.builder().regionCode(regionCode).build();
        // when
        GetCommentsInARegionResponse response = commentService.handle(command2);
    
        // then
        assertThat(response.getTotal()).isEqualTo(times);
    }
    
    @RepeatedTest(1)
    @DisplayName("comment 들을 가져올 수 있다.")
    void test03() {
        // given
        LeaveComment command = LeaveComment.builder().regionCode(regionCode).content(content).userId(userId).build();
        int times = 10;
        
        for (int i = 0; i < times; i++) {
            UUID result = commentService.handle(command).getCommentId();
        }
        GetCommentsInARegion command2 = GetCommentsInARegion.builder().regionCode(regionCode).build();
        // when
        GetCommentsInARegionResponse response = commentService.handle(command2);
        printResult(response);
        
        // then
//        assertThat(response.getTotal()).isEqualTo(times);
    }
    
    @Test
    @DisplayName("코멘트에 좋아요를 할 수 있다.")
    void test04() {
        // given
        LeaveComment command = LeaveComment.builder().regionCode(regionCode).content(content).userId(userId).build();
        UUID commentId = commentService.handle(command).getCommentId();
        // when
        LikeComment command2 = LikeComment.builder().commentId(commentId).userId(userId).build();
        for (int i = 0; i < 10; i++) {
            commentService.handle(command2);
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow();
    
        // then
        Integer likes = comment.getLikes();
        assertThat(likes).isEqualTo(10);
    }
    
    @Test
    @DisplayName("없는 코멘트를 찾으면 예외를 던진다.")
    void test05() {
        // given
        UUID unsavedCommentId = UUID.randomUUID();
        LikeComment command = LikeComment.builder().commentId(unsavedCommentId).userId(userId).build();
        assertThatThrownBy(() -> commentService.handle(command)).isInstanceOf(CommentNotFoundException.class);
        // when
        
        // then
    }
    
    void printResult(GetCommentsInARegionResponse response){
        Instant start = Instant.now();
        System.out.println(response);
        Instant end = Instant.now();
        int nano = end.minus(start.toEpochMilli(), ChronoUnit.MILLIS).getNano();
        long l1 = Runtime.getRuntime().freeMemory();
        System.out.println("걸린 시간: " + nano);
        System.out.println("free memory: " + l1);
        
        timeAmount += nano;
        freeMemory += l1;
    }
    
    @Test
    void test() {
        // given
        int i = 743732 * 500;
        System.out.println(i);
        // when
        
        // then
    }
    
    @AfterEach
    void after(){
        if (commentId != null) {
            commentRepository.deleteById(commentId);
        }
    }
    @AfterAll
    static void afterAll() {
        System.out.println("평균: " + (timeAmount / 500));
        System.out.println("free 평균: " + (freeMemory / 500));
    }
}
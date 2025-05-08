package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.request.CommentCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.response.CommentResponseDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.entity.D_Comment;
import com.example.korea_sleepTech_springboot.entity.D_Post;
import com.example.korea_sleepTech_springboot.repository.CommentRepository;
import com.example.korea_sleepTech_springboot.repository.PostRepository;
import com.example.korea_sleepTech_springboot.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = false) // readOnly 속성: 읽기 전용 트랜잭션 설정 여부 (기본값 false)
    /*
        @Transactional
        : Spring Framework에서 제공하는 프랜텍션 관리 어노테이션
        : DB의 일관성, 무결성, 원자성 등을 보장
        - Spring의 AOP(관점 지향 프로그래밍)를 활용하여
          메서드의 시작과 종료 시점에 트랜잭션을 시작하고, 종료 시점에 자동으로 커밋하거나 롤백

        >> 메서드가 정상적으로 실행되면 commit(), 예외가 발생하면 rollback()

        cf) 조회(Read)의 경우 내부 로직에서 변경 작업이 감지되면 예외가 발생하여 rollback() 처리
     */
    public ResponseDto<CommentResponseDto> createComment(CommentCreateRequestDto dto) {
        CommentResponseDto responseDto = null;

        // post가 존재하는지 확인
        D_Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + dto.getPostId()));

        // 새로운 Comment 생성
        D_Comment newComment = D_Comment.builder()
                .content(dto.getContent())
                .commenter(dto.getCommenter())
                .post(post)
                .build();

        D_Comment savedComment = commentRepository.save(newComment);

        responseDto = CommentResponseDto.builder()
                .id(savedComment.getId())
                .postId(savedComment.getPost().getId())
                .content(savedComment.getContent())
                .commenter(savedComment.getCommenter())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}

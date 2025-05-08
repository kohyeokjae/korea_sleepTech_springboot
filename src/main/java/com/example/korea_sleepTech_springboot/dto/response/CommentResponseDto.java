package com.example.korea_sleepTech_springboot.dto.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private Long postId;
    private String content;
    private String commenter;
}

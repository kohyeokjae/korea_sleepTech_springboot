package com.example.korea_sleepTech_springboot.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    private List<CommentResponseDto> comments;
}

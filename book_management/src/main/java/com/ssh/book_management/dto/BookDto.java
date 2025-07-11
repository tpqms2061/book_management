package com.ssh.book_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Integer id;

    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotNull(message = "저자 ID 를 지정하세요") //Integer 값이 빠져있는지 확인
    private Integer authorId;
}

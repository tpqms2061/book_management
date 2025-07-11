package com.ssh.book_management.controller;

import com.ssh.book_management.model.Author;
import com.ssh.book_management.service.AuthorService;
import com.ssh.book_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping("/api/dashboard/authors")
    public Map<Author, Long> countByAuthor() {
        return authorService.getAll().stream()
                .collect( //List ,set ,Map 컬렉션 데이터를 처리할수있는것
                        Collectors.toMap( // MAp을 사용
                                author -> author,  //키 값
                                author -> bookService.getAll().stream() //숫자 => 모든 책의 정보
                                        .filter(book -> book.getAuthorId().equals(author.getId()))
                                        .count() //filter : 책한권씩 살펴보면서 책의 작가이름과 작가 이름과 비교하여 필터하여 수를셈
                        )

                );
    }

}

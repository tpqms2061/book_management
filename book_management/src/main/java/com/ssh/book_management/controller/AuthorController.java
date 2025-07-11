package com.ssh.book_management.controller;


import com.ssh.book_management.service.AuthorService;
import com.ssh.book_management.dto.AuthorDto;
import com.ssh.book_management.model.Author;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

//    @GetMapping
//    public List<Author> list() {
//        return authorService.getAll();
//    }

    @GetMapping
    public ResponseEntity<List<Author>> list() {
        List<Author> authors = authorService.getAll();
        return ResponseEntity.ok(authors);
    }

//    @GetMapping("{id}")
//    public Author get(@PathVariable Integer id) {
//        return authorService.getById(id);
//    }
    //위와같음
@GetMapping("/{id}")
public ResponseEntity<Author> get(@PathVariable Integer id) {
    Author author = authorService.getById(id);

    return ResponseEntity.ok(author);
    //ok 가 제네릭 값이니까 그것을 써야됨
}


//    @PostMapping
//    public Author create(@Valid @RequestBody AuthorDto authorDto) {
//        Author author = new Author();
//        author.setName(authorDto.getName());
//
//        return authorService.create(author);
//    }

    //위 주석과 같은 형태
    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());

        Author saved = authorService.create(author);

        return ResponseEntity.created(URI.create("/api/authors" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(
            @PathVariable Integer id,
            @Valid @RequestBody AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());

        Author updated = authorService.update(id, author);

        return ResponseEntity.ok(updated); //업데이트도 200ok요청 이라 ok사용
    }


    @DeleteMapping("{id}") //id값이 필요함 :삭제 대상이 있어야되니까
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        authorService.delete(id);

        return ResponseEntity.noContent().build(); //delete는 204 noContent라 nocontent사용
    }
//    ResponseEntity<Void> :원래 이걸 반환했어야됨- author가 아니라 ResponseEntity<Author>
//    하지만 스프링 내부의 기능으로 Response형태가 아니라 author같은 타입으로 쓸시 알아서 변환해줌

}

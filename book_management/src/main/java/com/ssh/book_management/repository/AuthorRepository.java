package com.ssh.book_management.repository;

import com.ssh.book_management.model.Author;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorRepository {
    private final Map<Integer, Author> store = new LinkedHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);
    //원자성 : 연산결과에 대해 안전성을 보장
    //유니크함을 보장해주는 것

    public List<Author> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Author> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    } //값이 없으면 null 값으로 처리하기 위해서 optional 사용


    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(seq.incrementAndGet());

        }
        store.put(author.getId(), author);
        return author;
    }

//    update하려면 키가 필요함
    public Author update(Integer id, Author updatedAuthor) {
        if (!store.containsKey(id)) {
            throw new NoSuchElementException(id + "저자가 없습니다.");
        }

        updatedAuthor.setId(id); // 받아온 작가 데이터를 바꾸는거니까 id를 유지시키고
        store.put(id, updatedAuthor);

        return updatedAuthor;
    }

    public void delete(Integer id) {
        store.remove(id);
        //remove MAP의 기능
    }





}

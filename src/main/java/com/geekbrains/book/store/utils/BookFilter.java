package com.geekbrains.book.store.utils;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class BookFilter {
    private Specification<Book> spec;

    public BookFilter(Map<String, String> params) {
        spec = Specification.where(null);
        Specification<Book> spec1 = Specification.where(null);
        if (params.containsKey("det") && params.get("det").equals("on")) {
            spec1 = spec1.or(BookSpecifications.genreEqual(Genre.DETECTIVE));
        }
        if (params.containsKey("fic") && params.get("fic").equals("on")) {
            spec1 = spec1.or(BookSpecifications.genreEqual(Genre.FICTION));
        }
        if (params.containsKey("fan") && params.get("fan").equals("on")) {
            spec1 = spec1.or(BookSpecifications.genreEqual(Genre.FANTASY));
        }
        if (params.containsKey("maxPrice") && params.get("maxPrice").matches("\\d+")) {
            spec = spec.and(BookSpecifications.maxPrice(Integer.parseInt(params.get("maxPrice"))));
        }
        if (params.containsKey("minPrice") && params.get("minPrice").matches("\\d+")) {
            spec = spec.and(BookSpecifications.minPrice(Integer.parseInt(params.get("minPrice"))));
        }
        if (spec != null) {
            spec = spec.and(spec1);
        } else {
            spec = spec1;
        }
    }
}

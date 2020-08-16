package com.geekbrains.book.store.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Genre {
    FICTION("Фантастика", "fic"),
    FANTASY("Фэнтези", "fan"),
    DETECTIVE("Детектив", "det");

    private final String genre;
    private final String paramName;

    @Override
    public String toString() {
        return genre;
    }
}

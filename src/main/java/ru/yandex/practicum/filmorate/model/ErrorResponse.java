package ru.yandex.practicum.filmorate.model;

public class ErrorResponse {

    String error;
    String description;

    public ErrorResponse(String error, String description) {
        this.error = error;
        this.description = description;
    }

    // геттеры необходимы, чтобы Spring Boot мог получить значения полей
    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }
}


package com.marcps.desafioanotaai.domain.category.exceptions;

public class CategoryCanotBeNull extends RuntimeException{
    public CategoryCanotBeNull() {
        super("Category data cannot be null");
    }
}

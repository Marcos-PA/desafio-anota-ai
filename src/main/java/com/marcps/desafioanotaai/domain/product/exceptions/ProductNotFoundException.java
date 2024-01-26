package com.marcps.desafioanotaai.domain.product.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product not found");
    }
}

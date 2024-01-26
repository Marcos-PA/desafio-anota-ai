package com.marcps.desafioanotaai.domain.product.exceptions;

public class ProductCanotBeNull extends RuntimeException{
    public ProductCanotBeNull() {
        super("Product data cannot be null");
    }
}

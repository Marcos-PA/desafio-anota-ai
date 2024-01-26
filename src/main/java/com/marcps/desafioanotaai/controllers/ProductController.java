package com.marcps.desafioanotaai.controllers;

import com.marcps.desafioanotaai.domain.product.Product;
import com.marcps.desafioanotaai.domain.product.ProductDTO;
import com.marcps.desafioanotaai.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> createProduct(@Validated @RequestBody ProductDTO ProductData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(ProductData));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> verProdutos() {
        List<Product> products = this.productService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductDTO ProductData) {
        Product productUpdate = this.productService.update(id,ProductData);
        return ResponseEntity.ok().body(productUpdate);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

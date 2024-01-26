package com.marcps.desafioanotaai.service;

import com.marcps.desafioanotaai.domain.category.exceptions.CategoryCanotBeNull;
import com.marcps.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.marcps.desafioanotaai.domain.product.Product;
import com.marcps.desafioanotaai.domain.product.ProductDTO;
import com.marcps.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.marcps.desafioanotaai.repositories.ProductRepository;
import com.marcps.desafioanotaai.service.aws.AwsSnsService;
import com.marcps.desafioanotaai.service.aws.MessageDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {
    private final AwsSnsService snsService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public ProductService(CategoryService categoryService, ProductRepository productRepository, AwsSnsService snsService) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.snsService = snsService;
    }

    public Product createProduct(ProductDTO productDTO) {
        var newProduct = new Product();
        var category = this.categoryService.getById(productDTO.categoryID()).orElseThrow(CategoryNotFoundException::new);
        if (productDTO.title() == null
                || productDTO.description() == null
                || productDTO.ownerID() == null
                || productDTO.categoryID() == null
                || productDTO.price() == null)
            throw new CategoryCanotBeNull();
        BeanUtils.copyProperties(productDTO, newProduct);
        this.productRepository.save(newProduct);
        this.snsService.publish(new MessageDTO(newProduct.toString()));
        return newProduct;
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product deleteProduct(String id) {
        var product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product);
        return product;
    }

    public Product update(String id, ProductDTO ProductData) {
        var product = this.productRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (ProductData.categoryID() != null)
            this.categoryService.getById(ProductData.categoryID()).orElseThrow(CategoryNotFoundException::new);
            product.setCategory(ProductData.categoryID());
        if (ProductData.title() != null)
            product.setTitle(ProductData.title());
        if (ProductData.description() != null)
            product.setDescription(ProductData.description());
        if (ProductData.price() != null)
            product.setPrice(ProductData.price());
        this.productRepository.save(product);
        this.snsService.publish(new MessageDTO(product.toString()));
        return product;
    }
}

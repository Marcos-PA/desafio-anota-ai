package com.marcps.desafioanotaai.service;

import com.marcps.desafioanotaai.domain.category.Category;
import com.marcps.desafioanotaai.domain.category.CategoryDTO;
import com.marcps.desafioanotaai.domain.category.exceptions.CategoryCanotBeNull;
import com.marcps.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.marcps.desafioanotaai.domain.product.Product;
import com.marcps.desafioanotaai.domain.product.ProductDTO;
import com.marcps.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.marcps.desafioanotaai.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutctService {
    @Autowired
    private CategoryService categoryService;
    private ProductRepository productRepository;

    public Product createProduct(ProductDTO productDTO) {
        var newProduct = new Product();
        var cartegory = this.categoryService.getById(productDTO.categoryID()).orElseThrow(CategoryNotFoundException::new);
        if (productDTO.title() == null
                || productDTO.description() == null
                || productDTO.ownerID() == null
                || productDTO.categoryID() == null
                || productDTO.price() == null)
            throw new CategoryCanotBeNull();
        BeanUtils.copyProperties(productDTO, newProduct);
        this.productRepository.save(newProduct);
        return newProduct;
    }

    public List<Product> listarProdutos() {
        return this.productRepository.findAll();
    }

    public Product deleteCategory(String id) {
        var product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product);
        return product;
    }

    public Category update(String id, CategoryDTO categoryData) {
        var updatedCategory = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (categoryData.title() != null)
            updatedCategory.setTitle(categoryData.title());
        if (categoryData.description() != null)
            updatedCategory.setDescription(categoryData.description());
        this.categoryRepository.save(updatedCategory);
        return updatedCategory;
    }
}

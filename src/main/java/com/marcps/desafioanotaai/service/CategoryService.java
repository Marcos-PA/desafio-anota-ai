package com.marcps.desafioanotaai.service;

import com.marcps.desafioanotaai.domain.category.Category;
import com.marcps.desafioanotaai.domain.category.CategoryDTO;
import com.marcps.desafioanotaai.domain.category.exceptions.CategoryCanotBeNull;
import com.marcps.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.marcps.desafioanotaai.repositories.CategoryRepository;
import com.marcps.desafioanotaai.service.aws.AwsSnsService;
import com.marcps.desafioanotaai.service.aws.MessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final AwsSnsService snsService;
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO categoryData) {
        var newCategory = new Category();
        if (categoryData.title() == null || categoryData.description() == null || categoryData.ownerID() == null)
            throw new CategoryCanotBeNull();
        BeanUtils.copyProperties(categoryData, newCategory);
        this.categoryRepository.save(newCategory);
        this.snsService.publish(new MessageDTO(newCategory.toString()));
        return newCategory;
    }

    public List<Category> listarCategorias() {
        return this.categoryRepository.findAll();
    }

    public Category deleteCategory(String id) {
        var category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.categoryRepository.delete(category);
        return category;
    }

    public Category update(String id, CategoryDTO categoryData) {
        var updatedCategory = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (categoryData.title() != null)
            updatedCategory.setTitle(categoryData.title());
        if (categoryData.description() != null)
            updatedCategory.setDescription(categoryData.description());
        this.categoryRepository.save(updatedCategory);
        this.snsService.publish(new MessageDTO(updatedCategory.toString()));
        return updatedCategory;
    }

    public Optional<Category> getById(String id) {
        return this.categoryRepository.findById(id);
    }

}

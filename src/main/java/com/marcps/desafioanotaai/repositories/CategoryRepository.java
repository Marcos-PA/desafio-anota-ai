package com.marcps.desafioanotaai.repositories;

import com.marcps.desafioanotaai.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // This annotation is necessary to enable the use of MongoRepository
public interface CategoryRepository extends MongoRepository<Category, String> {
}

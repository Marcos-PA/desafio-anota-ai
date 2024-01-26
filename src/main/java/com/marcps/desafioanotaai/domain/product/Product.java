package com.marcps.desafioanotaai.domain.product;

import com.marcps.desafioanotaai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerID;
    private String category;
    private Integer price;
}

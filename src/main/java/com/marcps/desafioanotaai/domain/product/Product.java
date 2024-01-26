package com.marcps.desafioanotaai.domain.product;

import com.marcps.desafioanotaai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
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

@Override
    public String toString() {
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("id", this.id);
        jsonInfo.put("title", this.title);
        jsonInfo.put("description", this.description);
        jsonInfo.put("ownerID", this.ownerID);
        jsonInfo.put("category", this.category);
        jsonInfo.put("price", this.price);
        jsonInfo.put("type", "product" );
        return jsonInfo.toString();
    }
}

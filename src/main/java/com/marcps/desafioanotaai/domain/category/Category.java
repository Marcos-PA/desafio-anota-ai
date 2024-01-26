package com.marcps.desafioanotaai.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@Getter
@Setter
@NoArgsConstructor

public class Category {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerID;


    @Override
    public String toString() {
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("id", this.id);
        jsonInfo.put("title", this.title);
        jsonInfo.put("description", this.description);
        jsonInfo.put("ownerID", this.ownerID);
        jsonInfo.put("type", "category" );
        return jsonInfo.toString();
    }
}


package com.marcps.desafioanotaai.domain.product;

import jakarta.validation.constraints.NotBlank;

public record ProductDTO(@NotBlank String title, @NotBlank String description, @NotBlank String ownerID, @NotBlank String categoryID, @NotBlank Integer price) {
}

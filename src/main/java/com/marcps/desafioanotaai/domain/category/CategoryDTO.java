package com.marcps.desafioanotaai.domain.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(@NotBlank String title, @NotBlank String description, @NotBlank String ownerID) {
}

package com.dutra.dscomerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductEntry(
        @Size(min = 3, max = 80, message = "Campo reclama de 3 a 80 caracteres.")
        @NotBlank(message = "Campo obrigatório.")
        String name,

        @Size(min = 10, message = "Campo reclama 10 caracteres.")
        @NotBlank(message = "Campo obrigatório.")
        String description,
        @Positive
        Double price,
        @NotBlank(message = "Campo obrigatório.")
        String imgUrl
) {
}

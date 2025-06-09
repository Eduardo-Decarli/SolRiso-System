package com.decarli.solriso_system.model.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa o endereço de uma entidade ou usuário")
public class AddressResponseDto {

    @Size(max = 9, message = "Cep must be 9 caracters")
    private String cep;

    @Schema(description = "País do endereço", example = "Brasil")
    @NotBlank(message = "Country can't be blank")
    @Size(max = 100, message = "Country name must be less than 100 characters")
    private String state;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    @NotBlank(message = "City can't be blank")
    @Size(max = 100, message = "City name must be less than 100 characters")
    private String city;

    @Size(max = 100, message = "City name must be less than 100 characters")
    private String neighborhood;

    @Schema(description = "Rua do endereço", example = "Avenida Paulista")
    @NotBlank(message = "Street can't be blank")
    @Size(max = 150, message = "Street name must be less than 150 characters")
    private String street;

    @Schema(description = "Número da rua ou do imóvel", example = "1234")
    @NotBlank(message = "Number can't be blank")
    @Size(max = 20, message = "Number must be less than 20 characters")
    private String number;
}

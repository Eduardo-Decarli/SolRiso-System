package com.decarli.solriso_system.model.dto.responsibleBooking;

import com.decarli.solriso_system.model.dto.address.AddressCreateDto;
import com.decarli.solriso_system.model.dto.address.AddressResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Dados do responsável pela reserva")
public class ResponsibleBookingResponseDto {

    @Schema(description = "Nome completo do responsável", example = "João da Silva")
    @NotBlank(message = "Responsible name can't be blank")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Schema(description = "Número de telefone do responsável", example = "(11) 91234-5678")
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9\\d{4}-\\d{4}$", message = "Phone number must be valid")
    private String phoneNumber;

    @Schema(description = "E-mail do responsável", example = "joao.silva@email.com")
    @Email(message = "Insert a valid email")
    private String email;

    @Schema(description = "CPF do responsável", example = "123.456.789-09")
    @Pattern(
            regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
            message = "CPF must be in the format XXX.XXX.XXX-XX"
    )
    private String cpf;

    @Schema(description = "Endereço do responsável")
    @Valid
    private AddressResponseDto address;
}

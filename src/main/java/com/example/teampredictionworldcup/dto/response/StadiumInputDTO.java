package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record StadiumInputDTO(
        @NotBlank()
        @Pattern(regexp = "^\\d{4}$", message = "{validation.string.Pattern.numbersOnly}")
        @Length(min = 4, max = 4, message="{validation.code.length}")
        String code,

        @NotNull()
        @Min(value = 0, message = "{validation.checksum.min}")
        Integer checksum,

        @NotBlank()
        @Pattern(regexp =   "^[a-zA-Z ]+", message = "{validation.string.Pattern.lettersAndSpacesOnly}")
        String name,

        @NotBlank()
        @Pattern(regexp =   "^[a-zA-Z]+", message = "{validation.string.Pattern.numbersOnly}")
        String city,

        @NotNull
        @DecimalMin(value = "10000", message = "{validation.capacity.min}")
        @DecimalMax(value = "150000", message = "{validation.capacity.max}")
        Integer capacity) {}

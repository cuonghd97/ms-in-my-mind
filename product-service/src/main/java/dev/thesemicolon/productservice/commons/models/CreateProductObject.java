package dev.thesemicolon.productservice.commons.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateProductObject {
    String name;
    BigDecimal price;
    Integer quantity;
    MultipartFile image;
    String description;
}

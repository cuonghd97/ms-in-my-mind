package dev.thesemicolon.productservice.commons.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class UpdateProductObject {
    String name;
    BigDecimal price;
    Integer quantity;
    MultipartFile image;
    String description;
}

package dev.thesemicolon.productservice.apis;

import dev.thesemicolon.productservice.businesses.ProductService;
import dev.thesemicolon.productservice.commons.models.CreateProductObject;
import dev.thesemicolon.productservice.commons.models.UpdateProductObject;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import the.semicolon.productservice.server.api.ProductApi;
import the.semicolon.productservice.server.model.CreateProductResponse;
import the.semicolon.productservice.server.model.DeleteProductResponse;
import the.semicolon.productservice.server.model.ProductDetailResponse;
import the.semicolon.productservice.server.model.UpdateProductResponse;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("api/")
public class ProductController implements ProductApi {
    Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<CreateProductResponse> createProduct(
            @Parameter(name = "name", description = "", required = true) @Valid @RequestParam(value = "name", required = true) String name,
            @Parameter(name = "price", description = "", required = true) @Valid @RequestParam(value = "price", required = true) BigDecimal price,
            @Parameter(name = "quantity", description = "", required = true) @Valid @RequestParam(value = "quantity", required = true) Integer quantity,
            @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image,
            @Parameter(name = "description", description = "") @Valid @RequestParam(value = "description", required = false) String description
    ) throws Exception {
        CreateProductObject createProductObject = new CreateProductObject(name, price, quantity, image, description);
        CreateProductResponse response = this.productService.createProduct(createProductObject);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProductDetailResponse> getProductDetail(
            @Parameter(name = "productId", description = "id of product", required = true, in = ParameterIn.PATH) @PathVariable("productId") String productId
    ) throws Exception {
        ProductDetailResponse response = this.productService.getProductDetail(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UpdateProductResponse> updateProduct(
            @Parameter(name = "productId", description = "id of product", required = true, in = ParameterIn.PATH) @PathVariable("productId") String productId,
            @Parameter(name = "name", description = "", required = true) @Valid @RequestParam(value = "name", required = true) String name,
            @Parameter(name = "price", description = "", required = true) @Valid @RequestParam(value = "price", required = true) BigDecimal price,
            @Parameter(name = "quantity", description = "", required = true) @Valid @RequestParam(value = "quantity", required = true) Integer quantity,
            @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image,
            @Parameter(name = "description", description = "") @Valid @RequestParam(value = "description", required = false) String description
    ) throws Exception {
        UpdateProductObject updateProductObject = UpdateProductObject.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .image(image)
                .description(description)
                .build();
        UpdateProductResponse response = this.productService.updateProduct(productId, updateProductObject);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    public ResponseEntity<DeleteProductResponse> deleteProduct(
            @Parameter(name = "productId", description = "id of product", required = true, in = ParameterIn.PATH) @PathVariable("productId") String productId
    ) throws Exception {
        DeleteProductResponse response = this.productService.deleteProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}

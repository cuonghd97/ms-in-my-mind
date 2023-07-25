package dev.thesemicolon.productservice.apis;

import dev.thesemicolon.productservice.businesses.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.semicolon.productservice.server.api.ApiUtil;
import the.semicolon.productservice.server.api.ProductApi;
import the.semicolon.productservice.server.model.CreateProductRequest;
import the.semicolon.productservice.server.model.CreateProductResponse;
import the.semicolon.productservice.server.model.ProductDetailResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public class ProductController implements ProductApi {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<CreateProductResponse> createProduct(
            @Parameter(name = "CreateProductRequest", description = "", required = true) @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        CreateProductResponse response = this.productService.createProduct(createProductRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProductDetailResponse> getProductDetail(
            @Parameter(name = "productId", description = "id of product", required = true, in = ParameterIn.PATH) @PathVariable("productId") String productId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : 1, \"quantity\" : 6, \"price\" : 0.8008281904610115, \"imageUrl\" : \"imageUrl\", \"name\" : \"name\", \"description\" : \"description\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);

    }
}

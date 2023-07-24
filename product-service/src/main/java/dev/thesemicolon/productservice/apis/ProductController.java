package dev.thesemicolon.productservice.apis;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.semicolon.productservice.server.api.ApiUtil;
import the.semicolon.productservice.server.api.ProductApi;
import the.semicolon.productservice.server.model.CreateProductRequest;
import the.semicolon.productservice.server.model.CreateProductResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public class ProductController implements ProductApi {
    @Override
    public ResponseEntity<CreateProductResponse> createProduct(
            @Parameter(name = "CreateProductRequest", description = "", required = true) @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}

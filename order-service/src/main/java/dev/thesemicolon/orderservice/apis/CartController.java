package dev.thesemicolon.orderservice.apis;

import dev.thesemicolon.orderservice.businesses.CartService;
import dev.thesemicolon.orderservice.commons.models.UserInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import the.semicolon.orderservice.server.api.ApiUtil;
import the.semicolon.orderservice.server.api.CartApi;
import the.semicolon.orderservice.server.model.CartDetailResponse;
import the.semicolon.orderservice.server.model.CreateCartRequest;
import the.semicolon.orderservice.server.model.CreateCartResponse;
import the.semicolon.orderservice.server.model.DeleteCartResponse;
import the.semicolon.orderservice.server.model.UpdateCartRequest;
import the.semicolon.orderservice.server.model.UpdateCartResponse;

@RestController
@RequestMapping("api/")
public class CartController extends BaseController implements CartApi {
  private CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  public ResponseEntity<CreateCartResponse> creatCart(
      @Parameter(name = "CreateCartRequest", description = "", required = true) @Valid @RequestBody
          CreateCartRequest createCartRequest)
      throws Exception {
    UserInfo userInfo = super.getUserInfo();

    CreateCartResponse response =
        this.cartService.createCart(createCartRequest, userInfo.getUserID());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public ResponseEntity<DeleteCartResponse> deleteCart(
      @Parameter(
              name = "cartId",
              description = "id of cart",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("cartId")
          String cartId)
      throws Exception {

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<CartDetailResponse> getCartDetail(
      @Parameter(
              name = "cartId",
              description = "id of cart",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("cartId")
          String cartId)
      throws Exception {

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<UpdateCartResponse> updateCart(
      @Parameter(
              name = "cartId",
              description = "id of cart",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("cartId")
          String cartId,
      @Parameter(name = "UpdateCartRequest", description = "", required = true) @Valid @RequestBody
          UpdateCartRequest updateCartRequest)
      throws Exception {

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}

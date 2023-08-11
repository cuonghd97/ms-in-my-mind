package dev.thesemicolon.orderservice.businesses;

import the.semicolon.orderservice.server.model.CartDetailResponse;
import the.semicolon.orderservice.server.model.CreateCartRequest;
import the.semicolon.orderservice.server.model.CreateCartResponse;
import the.semicolon.orderservice.server.model.DeleteCartResponse;
import the.semicolon.orderservice.server.model.UpdateCartRequest;
import the.semicolon.orderservice.server.model.UpdateCartResponse;

/**
 * Cart Service.
 */
public interface CartService {
  CreateCartResponse createCart(CreateCartRequest createCartRequest, String userId)
      throws Exception;

  UpdateCartResponse updateCart(String cartId, UpdateCartRequest updateCartRequest)
      throws Exception;

  CartDetailResponse getCartDetail(String cartId) throws Exception;

  DeleteCartResponse deleteCart(String cartId) throws Exception;
}

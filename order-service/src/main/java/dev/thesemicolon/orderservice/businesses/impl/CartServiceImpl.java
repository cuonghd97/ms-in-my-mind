package dev.thesemicolon.orderservice.businesses.impl;

import dev.thesemicolon.orderservice.businesses.CartService;
import dev.thesemicolon.orderservice.daos.Cart;
import dev.thesemicolon.orderservice.repositories.CartRepository;
import dev.thesemicolon.orderservice.repositories.ProductClientRepository;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.semicolon.orderservice.server.model.CartDetailResponse;
import the.semicolon.orderservice.server.model.CreateCartRequest;
import the.semicolon.orderservice.server.model.CreateCartResponse;
import the.semicolon.orderservice.server.model.DeleteCartResponse;
import the.semicolon.orderservice.server.model.UpdateCartRequest;
import the.semicolon.orderservice.server.model.UpdateCartResponse;
import the.semicolon.productservice.client.model.ProductDetailResponse;

@Service
public class CartServiceImpl implements CartService {
  private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

  private CartRepository cartRepository;

  private ProductClientRepository productClientRepository;

  @Autowired
  public CartServiceImpl(
      CartRepository cartRepository, ProductClientRepository productClientRepository) {
    this.cartRepository = cartRepository;
    this.productClientRepository = productClientRepository;
  }

  @Override
  public CreateCartResponse createCart(CreateCartRequest createCartRequest, String userId)
      throws Exception {
    UUID uuid = UUID.randomUUID();
    Cart cart = new Cart();
    cart.setId(uuid.toString());
    cart.setProductId(createCartRequest.getProductId());
    cart.setQuantity(createCartRequest.getQuantity().intValue());
    cart.setUserId(userId);

    ProductDetailResponse product =
        this.productClientRepository
            .getProductDetail(cart.getProductId())
            .orElseThrow(() -> new Exception("Product not found"));

//    this.cartRepository.save(cart);
    return new CreateCartResponse().message("create cart success");
  }

  @Override
  public UpdateCartResponse updateCart(String cartId, UpdateCartRequest updateCartRequest)
      throws Exception {
    Cart cart =
        this.cartRepository.findById(cartId).orElseThrow(() -> new Exception("Cart not found"));
    cart.setQuantity(updateCartRequest.getQuantity());

    this.cartRepository.save(cart);
    return new UpdateCartResponse().message("update cart success");
  }

  @Override
  public CartDetailResponse getCartDetail(String cartId) throws Exception {
    Cart cart =
        this.cartRepository.findById(cartId).orElseThrow(() -> new Exception("Cart not found"));
    ProductDetailResponse product =
        this.productClientRepository
            .getProductDetail(cart.getProductId())
            .orElseThrow(() -> new Exception("Product not found"));

    //    update price
    return null;
  }

  @Override
  public DeleteCartResponse deleteCart(String cartId) throws Exception {
    return null;
  }
}

package dev.thesemicolon.orderservice.repositories;

import dev.thesemicolon.orderservice.daos.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Cart Repository. */
@Repository
public interface CartRepository extends JpaRepository<Cart, String> {}

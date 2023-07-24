package dev.thesemicolon.productservice.repositories;

import dev.thesemicolon.productservice.daos.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

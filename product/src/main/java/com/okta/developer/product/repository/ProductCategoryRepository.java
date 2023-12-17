package com.okta.developer.product.repository;

import com.okta.developer.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProductCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {}

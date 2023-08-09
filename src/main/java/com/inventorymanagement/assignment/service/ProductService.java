package com.inventorymanagement.assignment.service;

import com.inventorymanagement.assignment.entity.Product;
import com.inventorymanagement.assignment.payload.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

        Page<ProductDTO> getAllProducts(Pageable pageable);

        ProductDTO getProductById(Long productId);

        ProductDTO saveProduct(ProductDTO productDTO);

        ProductDTO updateProduct(Long productId, ProductDTO productDTO);

        void deleteProduct(Long productId);
        List<ProductDTO> getAllProducts();
}

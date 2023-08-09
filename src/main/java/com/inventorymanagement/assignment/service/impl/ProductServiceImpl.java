package com.inventorymanagement.assignment.service.impl;

import com.inventorymanagement.assignment.entity.Category;
import com.inventorymanagement.assignment.entity.Product;
import com.inventorymanagement.assignment.exception.EntityNotFoundException;
import com.inventorymanagement.assignment.exception.ProductNotFoundException;
import com.inventorymanagement.assignment.payload.CategoryDTO;
import com.inventorymanagement.assignment.payload.ProductDTO;
import com.inventorymanagement.assignment.repository.ProductRepository;
import com.inventorymanagement.assignment.service.CategoryService;
import com.inventorymanagement.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name", "price"));
        Page<Product> productsPage = productRepository.findAll(pageable);
        return productsPage.map(this::convertToProductDTO);
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
        return convertToProductDTO(product);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        // Fetch category from the database using the category ID from the DTO
        CategoryDTO categoryDTO = categoryService.getCategoryById(productDTO.getCategory());

        // Convert ProductDTO to Product entity
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());

        // Set the category for the product entity directly from the obtained CategoryDTO
        product.setCategory(new Category(categoryDTO.getCategory_id(), categoryDTO.getName(), categoryDTO.getDescription()));

        product = productRepository.save(product);
        return convertToProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        // Fetch the existing product by ID
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        // Update the fields of the existing product with the new data
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setPrice(productDTO.getPrice());

        // Fetch category from the database using the category ID from the DTO
        CategoryDTO categoryDTO = categoryService.getCategoryById(productDTO.getCategory());

        // Set the updated category for the existing product entity
        existingProduct.setCategory(new Category(categoryDTO.getCategory_id(), categoryDTO.getName(), categoryDTO.getDescription()));

        // Save the updated product
        existingProduct = productRepository.save(existingProduct);

        return convertToProductDTO(existingProduct);
}


    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);

    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }


    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_id(product.getProduct_id());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            productDTO.setCategory(product.getCategory().getCategory_id());
        }
        return productDTO;
    }

    private Product convertToProductEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProduct_id(productDTO.getProduct_id());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}


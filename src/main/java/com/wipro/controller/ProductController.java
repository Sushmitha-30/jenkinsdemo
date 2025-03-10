package com.wipro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.entity.Product;
import com.wipro.repository.ProductRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productRepository.findById(id)
            .map(product -> {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                return productRepository.save(product);
            })
            .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}

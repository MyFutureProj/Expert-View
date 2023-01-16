package com.camelcase.userauthservice.endpoint;

import com.camelcase.userauthservice.model.Product;
import com.camelcase.userauthservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductApi {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product saveProduct = productRepository.save(product);
        URI productUri = URI.create("/products/" + saveProduct.getId());
        return ResponseEntity.created(productUri).body(saveProduct);
    }

    @GetMapping
    public List<Product> list() {
        return productRepository.findAll();
    }

}

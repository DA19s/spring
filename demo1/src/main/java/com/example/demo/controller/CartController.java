package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartController(CartItemRepository cartItemRepository,
                          ProductRepository productRepository,
                          UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<CartItem> getMyCart(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.name()).orElseThrow();
        return cartItemRepository.findByUser(user);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable Long productId,
                                            Authentication authentication) {
        User user = userRepository.findByUsername(authentication.name()).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        CartItem item = new CartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(1);

        cartItemRepository.save(item);

        return ResponseEntity.ok("Product added to cart");
    }
}


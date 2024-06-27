package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Cart;
import com.pramu.fasteats.model.CartItem;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.request.CartItemRequest;
import com.pramu.fasteats.request.CartItemUpdateRequest;
import com.pramu.fasteats.service.CartService;
import com.pramu.fasteats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addCartItemToCart(@RequestBody CartItemRequest request,
                                                      @RequestHeader("Authorization") String token) throws Exception {
        CartItem cartItem = cartService.addItemToCart(request, token);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody CartItemUpdateRequest request,
                                                      @RequestHeader("Authorization") String token) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> updateCartItemQuantity(@PathVariable Long id,
                                                           @RequestHeader("Authorization") String token) throws Exception {

        Cart cart = cartService.removeItemFromCart(id, token);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}

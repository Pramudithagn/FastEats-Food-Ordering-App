package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Cart;
import com.pramu.fasteats.model.CartItem;
import com.pramu.fasteats.request.CartItemRequest;

public interface CartService {

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public CartItem addItemToCart(CartItemRequest request, String token) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Long getCartTotalPrice(Cart cart) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String token) throws Exception;

    public Cart clearCart(Long userId) throws Exception;

}

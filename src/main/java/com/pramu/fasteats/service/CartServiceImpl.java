package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Cart;
import com.pramu.fasteats.model.CartItem;
import com.pramu.fasteats.model.Food;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.repository.CartItemRepository;
import com.pramu.fasteats.repository.CartRepository;
import com.pramu.fasteats.repository.FoodRepository;
import com.pramu.fasteats.request.CartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;


    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()){
            throw new Exception("Cart with id " + id + " not found");
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByUserId(userId);
        cart.setTotal(getCartTotalPrice(cart)); // To be used in order service, in the creation of order
        return cart;
    }

    @Override
    public CartItem addItemToCart(CartItemRequest request, String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Food food = foodService.findFoodById(request.getFoodId());
        Cart cart = cartRepository.findByUserId(user.getId());

        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getFood().equals(food)) {
                int updatedQuantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), updatedQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setFood(food);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity() * food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getCartItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new Exception("CartItem not found");
        }
        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Long getCartTotalPrice(Cart cart) throws Exception {
        Long totalPrice = 0L;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Cart cart = cartRepository.findByUserId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new Exception("CartItem not found");
        }
        CartItem cartItem = cartItemOptional.get();
        cart.getCartItems().remove(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}

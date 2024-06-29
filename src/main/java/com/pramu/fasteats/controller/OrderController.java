package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.CartItem;
import com.pramu.fasteats.model.Order;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.request.CartItemRequest;
import com.pramu.fasteats.request.OrderRequest;
import com.pramu.fasteats.service.OrderService;
import com.pramu.fasteats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/user")
    public ResponseEntity<Order> addCartItemToCart(@RequestBody OrderRequest request,
                                                      @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Order order = orderService.createOrder(request, user);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrders(@RequestBody OrderRequest request,
                                                   @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        List<Order> order = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}

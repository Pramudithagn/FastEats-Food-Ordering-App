package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Order;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.request.OrderRequest;
import com.pramu.fasteats.service.OrderService;
import com.pramu.fasteats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long id,
                                                 @RequestParam(required = false) String orderStatus,
                                                 @RequestHeader("Authorization") String token) throws Exception {
        System.out.println(orderStatus);
        User user = userService.findUserByJwtToken(token);
        List<Order> order = orderService.getRestaurantOrders(id, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                 @PathVariable String orderStatus,
                                                 @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Order order = orderService.updateOrder(id, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

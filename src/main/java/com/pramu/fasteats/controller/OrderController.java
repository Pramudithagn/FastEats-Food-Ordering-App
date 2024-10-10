package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Order;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.request.OrderRequest;
import com.pramu.fasteats.response.MessageResponse;
import com.pramu.fasteats.response.PaymentResponse;
import com.pramu.fasteats.service.OrderService;
import com.pramu.fasteats.service.PaymentService;
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

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse > createOrder(@RequestBody OrderRequest request,
                                                       @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Order order = orderService.createOrder(request, user);
        PaymentResponse response = paymentService.createPaymentLink(order);
        response.setOrderId(order.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getUserOrders(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        List<Order> order = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}/cancel")
    public ResponseEntity<MessageResponse> cancelOrder(@PathVariable Long id) throws Exception {
        orderService.cancelOrder(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Order " + id + " cancelled!");
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }
}

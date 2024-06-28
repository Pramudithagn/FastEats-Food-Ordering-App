package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Order;
import com.pramu.fasteats.model.User;
import com.pramu.fasteats.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order findOrderById(Long orderId) throws Exception;

    public List<Order> getUserOrders(Long userId) throws Exception;

    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception;

    public Order createOrder(OrderRequest request, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

}

package com.pramu.fasteats.service;

import com.pramu.fasteats.model.*;
import com.pramu.fasteats.repository.*;
import com.pramu.fasteats.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new Exception("Order not found");
        }
        return order.get();
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws Exception {
//        return orderRepository.findByUserId(userId);
        return orderRepository.findAllUserOrders(userId);

    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception {
//        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);
        if (orderStatus != null) {
            orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order createOrder(OrderRequest request, User user) throws Exception {
        Address shippingAddress = request.getDeliveryAddress();
        Address address = addressRepository.save(shippingAddress);

        if(!user.getAddresses().contains(address)) {
            user.getAddresses().add(address);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
        Order order = new Order();
        order.setRestaurant(restaurant);
        order.setDeliveryAddress(address);
        order.setCustomer(user);
        order.setOrderedDate(new Date());
        order.setOrderStatus("PENDING");

        Cart cart = cartService.findCartByUserId(user.getId());

        List<Orderitem> orderitemList = new ArrayList<>();

        for(CartItem cartItem : cart.getCartItems()) {
            Orderitem orderitem = new Orderitem();
            orderitem.setFood(cartItem.getFood());
            orderitem.setQuantity(cartItem.getQuantity());
            orderitem.setIngredients(cartItem.getIngredients());
            orderitem.setTotalPrice(cartItem.getTotalPrice());

            Orderitem savedOrderitem = orderItemRepository.save(orderitem);
            orderitemList.add(savedOrderitem);
        }

//        Long totalPrice = 0L;
        order.setItems(orderitemList);
        order.setTotalPrice(cartService.getCartTotalPrice(cart));

        Order savedOrder = orderRepository.save(order);
        restaurant.getOrders().add(savedOrder);
        return order;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("PENDING") || orderStatus.equals("COMPLETED") || orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Invalid order status selected!");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);

    }
}

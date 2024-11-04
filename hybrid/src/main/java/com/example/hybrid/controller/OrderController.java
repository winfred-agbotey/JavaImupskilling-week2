package com.example.hybrid.controller;



import com.example.hybrid.model.Customer;
import com.example.hybrid.model.Order;
import com.example.hybrid.service.OrderService;
import com.example.hybrid.service.ShoppingCartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Customer customer) {
        return orderService.createOrder(customer, shoppingCartService.getCartItems());
    }
}

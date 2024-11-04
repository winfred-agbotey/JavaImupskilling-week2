package com.example.hybrid.service;

import com.example.hybrid.model.Customer;
import com.example.hybrid.model.Order;
import com.example.hybrid.model.OrderItem;
import com.example.hybrid.model.Product;
import com.example.hybrid.repository.CustomerRepository;
import com.example.hybrid.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductService productService;
    private final PaymentGateway paymentGateway;

    public OrderService(ProductService productService, PaymentGateway paymentGateway,
                        OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.productService = productService;
        this.paymentGateway = paymentGateway;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(Customer customer, Map<Long, Integer> cartItems) {
        Customer savedCustomer = customerRepository.save(customer);
        Order order = new Order();
        order.setCustomer(savedCustomer);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = cartItems.entrySet().stream()
                .map(entry -> {
                    Product product = productService.getProductById(entry.getKey());
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(entry.getValue());
                    return orderItem;
                }).collect(Collectors.toList());

        order.setItems(orderItems);
        order = orderRepository.save(order);

        boolean paymentSuccess = paymentGateway.processPayment(order);
        if (!paymentSuccess) {
            throw new RuntimeException("Payment processing failed");
        }

        return order;
    }
}

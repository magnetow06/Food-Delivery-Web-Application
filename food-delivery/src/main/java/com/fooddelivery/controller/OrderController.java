package com.fooddelivery.controller;

import com.fooddelivery.dto.OrderRequest;
import com.fooddelivery.entity.Order;
import com.fooddelivery.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(userDetails.getUsername(), request));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Order>> myOrders(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(orderService.getMyOrders(userDetails.getUsername()));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}
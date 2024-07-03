package com.pillgood.controller;

import com.pillgood.dto.OrderDto;
import com.pillgood.service.OrderService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String orderNo) {
        OrderDto orderDto = orderService.getOrderById(orderNo);
        if (orderDto != null) {
            return ResponseEntity.ok(orderDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto, HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        if (memberId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        orderDto.setMemberUniqueId(memberId);
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/update/{orderNo}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable String orderNo, @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(orderNo, orderDto);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{orderNo}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderNo) {
        orderService.deleteOrder(orderNo);
        return ResponseEntity.noContent().build();
    }
}

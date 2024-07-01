package com.pillgood.service;

import com.pillgood.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(String orderNo);
    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrder(String orderNo, OrderDto orderDto);
    void deleteOrder(String orderNo);
}

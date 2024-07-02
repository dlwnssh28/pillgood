package com.pillgood.service;

import com.pillgood.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> getAllOrderDetails();
    OrderDetailDto getOrderDetailById(int orderDetailNo);
    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto);
    OrderDetailDto updateOrderDetail(int orderDetailNo, OrderDetailDto orderDetailDto);
    void deleteOrderDetail(int orderDetailNo);
}

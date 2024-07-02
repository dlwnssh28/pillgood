package com.pillgood.service;

import com.pillgood.dto.OrderDetailDto;
import com.pillgood.entity.OrderDetail;
import com.pillgood.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailDto> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDto getOrderDetailById(int orderDetailNo) {
        Optional<OrderDetail> orderDetailOpt = orderDetailRepository.findById(orderDetailNo);
        return orderDetailOpt.map(this::convertToDto).orElse(null);
    }

    @Override
    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetailEntity = convertToEntity(orderDetailDto);
        orderDetailRepository.save(orderDetailEntity);
        return convertToDto(orderDetailEntity);
    }

    @Override
    public OrderDetailDto updateOrderDetail(int orderDetailNo, OrderDetailDto orderDetailDto) {
        Optional<OrderDetail> orderDetailOpt = orderDetailRepository.findById(orderDetailNo);
        if (orderDetailOpt.isPresent()) {
            OrderDetail orderDetailEntity = orderDetailOpt.get();
            updateEntityFromDto(orderDetailEntity, orderDetailDto);
            orderDetailRepository.save(orderDetailEntity);
            return convertToDto(orderDetailEntity);
        }
        return null;
    }

    @Override
    public void deleteOrderDetail(int orderDetailNo) {
        orderDetailRepository.deleteById(orderDetailNo);
    }

    private OrderDetailDto convertToDto(OrderDetail orderDetailEntity) {
        return new OrderDetailDto(
                orderDetailEntity.getOrderDetailNo(),
                orderDetailEntity.getOrderNo(),
                orderDetailEntity.getProductId(),
                orderDetailEntity.getQuantity(),
                orderDetailEntity.getAmount()
        );
    }

    private OrderDetail convertToEntity(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailNo(orderDetailDto.getOrderDetailNo());
        orderDetail.setOrderNo(orderDetailDto.getOrderNo());
        orderDetail.setProductId(orderDetailDto.getProductId());
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setAmount(orderDetailDto.getAmount());
        return orderDetail;
    }

    private void updateEntityFromDto(OrderDetail orderDetailEntity, OrderDetailDto orderDetailDto) {
        orderDetailEntity.setOrderNo(orderDetailDto.getOrderNo());
        orderDetailEntity.setProductId(orderDetailDto.getProductId());
        orderDetailEntity.setQuantity(orderDetailDto.getQuantity());
        orderDetailEntity.setAmount(orderDetailDto.getAmount());
    }
}

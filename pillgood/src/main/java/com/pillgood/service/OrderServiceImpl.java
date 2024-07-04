package com.pillgood.service;

import com.pillgood.dto.OrderDto;
import com.pillgood.entity.Order;
import com.pillgood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(String orderNo) {
        Optional<Order> orderOpt = orderRepository.findById(orderNo);
        return orderOpt.map(this::convertToDto).orElse(null);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order orderEntity = convertToEntity(orderDto);

        // 기본 키 수동 설정
        String orderNo = generateOrderNo(); // 고유한 주문 번호 생성 로직을 여기에 추가하세요.
        orderEntity.setOrderNo(orderNo);
        
        orderRepository.save(orderEntity);
        return convertToDto(orderEntity);
    }
    
    private String generateOrderNo() {
        // 고유한 주문 번호를 생성하는 로직을 구현합니다.
        // 예를 들어, 현재 시간과 랜덤 문자열을 조합하여 생성할 수 있습니다.
        return "ORD-" + System.currentTimeMillis();
    }

    @Override
    public OrderDto updateOrder(String orderNo, OrderDto orderDto) {
        Optional<Order> orderOpt = orderRepository.findById(orderNo);
        if (orderOpt.isPresent()) {
            Order orderEntity = orderOpt.get();
            updateEntityFromDto(orderEntity, orderDto);
            orderRepository.save(orderEntity);
            return convertToDto(orderEntity);
        }
        return null;
    }

    @Override
    public void deleteOrder(String orderNo) {
        orderRepository.deleteById(orderNo);
    }

    private OrderDto convertToDto(Order orderEntity) {
        return new OrderDto(
                orderEntity.getOrderNo(),
                orderEntity.getTotalAmount(),
                orderEntity.getOrderRequest(),
                orderEntity.getOrderDate(),
                orderEntity.getRecipient(),
                orderEntity.getPostalCode(),
                orderEntity.getAddress(),
                orderEntity.getDetailedAddress(),
                orderEntity.getPhoneNumber(),
                orderEntity.getMemberUniqueId(),
                orderEntity.getOwnedCouponId(),
                orderEntity.getOrderStatus(),
                orderEntity.isSubscriptionStatus()
        );
    }

    private Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderNo(orderDto.getOrderNo());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderRequest(orderDto.getOrderRequest());
        order.setOrderDate(orderDto.getOrderDate());
        order.setRecipient(orderDto.getRecipient());
        order.setPostalCode(orderDto.getPostalCode());
        order.setAddress(orderDto.getAddress());
        order.setDetailedAddress(orderDto.getDetailedAddress());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setMemberUniqueId(orderDto.getMemberUniqueId());
        order.setOwnedCouponId(orderDto.getOwnedCouponId());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setSubscriptionStatus(orderDto.isSubscriptionStatus());
        return order;
    }

    private void updateEntityFromDto(Order orderEntity, OrderDto orderDto) {
        orderEntity.setTotalAmount(orderDto.getTotalAmount());
        orderEntity.setOrderRequest(orderDto.getOrderRequest());
        orderEntity.setOrderDate(orderDto.getOrderDate());
        orderEntity.setRecipient(orderDto.getRecipient());
        orderEntity.setPostalCode(orderDto.getPostalCode()); 
        orderEntity.setAddress(orderDto.getAddress());
        orderEntity.setDetailedAddress(orderDto.getDetailedAddress());
        orderEntity.setPhoneNumber(orderDto.getPhoneNumber());
        orderEntity.setMemberUniqueId(orderDto.getMemberUniqueId());
        orderEntity.setOwnedCouponId(orderDto.getOwnedCouponId());
        orderEntity.setOrderStatus(orderDto.getOrderStatus());
        orderEntity.setSubscriptionStatus(orderDto.isSubscriptionStatus());
    }
}

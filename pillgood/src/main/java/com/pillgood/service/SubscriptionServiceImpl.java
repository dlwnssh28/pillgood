package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillgood.dto.SubscriptionDto;
import com.pillgood.entity.Subscription;
import com.pillgood.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public List<SubscriptionDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionDto getSubscriptionById(int subscriptionId) {
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findById(subscriptionId);
        return subscriptionOpt.map(this::convertToDto).orElse(null);
    }

    @Override
    public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscriptionEntity = convertToEntity(subscriptionDto);
        subscriptionRepository.save(subscriptionEntity);
        return convertToDto(subscriptionEntity);
    }

    @Override
    public SubscriptionDto updateSubscription(int subscriptionId, SubscriptionDto subscriptionDto) {
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findById(subscriptionId);
        if (subscriptionOpt.isPresent()) {
            Subscription subscriptionEntity = subscriptionOpt.get();
            updateEntityFromDto(subscriptionEntity, subscriptionDto);
            subscriptionRepository.save(subscriptionEntity);
            return convertToDto(subscriptionEntity);
        }
        return null;
    }

    @Override
    public void deleteSubscription(int subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    private SubscriptionDto convertToDto(Subscription subscriptionEntity) {
        return new SubscriptionDto(
                subscriptionEntity.getSubscriptionId(),
                subscriptionEntity.getMemberUniqueId(),
                subscriptionEntity.getStartDate(),
                subscriptionEntity.getEndDate(),
                subscriptionEntity.getSubscriptionStatus(),
                subscriptionEntity.getPaymentNo()
        );
    }

    private Subscription convertToEntity(SubscriptionDto subscriptionDto) {
        return new Subscription(
                subscriptionDto.getSubscriptionId(),
                subscriptionDto.getMemberUniqueId(),
                subscriptionDto.getStartDate(),
                subscriptionDto.getEndDate(),
                subscriptionDto.getSubscriptionStatus(),
                subscriptionDto.getPaymentNo()
        );
    }

    private void updateEntityFromDto(Subscription subscriptionEntity, SubscriptionDto subscriptionDto) {
        subscriptionEntity.setMemberUniqueId(subscriptionDto.getMemberUniqueId());
        subscriptionEntity.setStartDate(subscriptionDto.getStartDate());
        subscriptionEntity.setEndDate(subscriptionDto.getEndDate());
        subscriptionEntity.setSubscriptionStatus(subscriptionDto.getSubscriptionStatus());
        subscriptionEntity.setPaymentNo(subscriptionDto.getPaymentNo());
    }
}

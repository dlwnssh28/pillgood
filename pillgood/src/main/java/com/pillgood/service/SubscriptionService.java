package com.pillgood.service;

import java.util.List;

import com.pillgood.dto.SubscriptionDto;

public interface SubscriptionService {
    List<SubscriptionDto> getAllSubscriptions();
    SubscriptionDto getSubscriptionById(int subscriptionId);
    SubscriptionDto createSubscription(SubscriptionDto subscriptionDto);
    SubscriptionDto updateSubscription(int subscriptionId, SubscriptionDto subscriptionDto);
    void deleteSubscription(int subscriptionId);
}

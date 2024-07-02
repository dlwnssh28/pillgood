package com.pillgood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.SubscriptionDto;
import com.pillgood.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/list")
    public List<SubscriptionDto> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable int id) {
        SubscriptionDto subscriptionDto = subscriptionService.getSubscriptionById(id);
        if (subscriptionDto != null) {
            return ResponseEntity.ok(subscriptionDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        SubscriptionDto createdSubscription = subscriptionService.createSubscription(subscriptionDto);
        return ResponseEntity.ok(createdSubscription);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable int id, @RequestBody SubscriptionDto subscriptionDto) {
        SubscriptionDto updatedSubscription = subscriptionService.updateSubscription(id, subscriptionDto);
        if (updatedSubscription != null) {
            return ResponseEntity.ok(updatedSubscription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable int id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }
}

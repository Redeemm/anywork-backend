package com.reddev.anywork.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public String wholesalerMessage(String wholesaler, String poId) {
        return "You have received a purchase order from " + wholesaler + " with ID " + poId;
    }
}

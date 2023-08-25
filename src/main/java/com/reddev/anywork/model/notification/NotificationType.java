package com.reddev.anywork.model.notification;

public enum NotificationType {

    OTP("OTP"),
    PURCHASE_ORDER("PO"),
    PROFORMA("PF"),
    INVOICE("INV");

    final String value;
    NotificationType(String value) {
        this.value = value;
    }
}

package com.reddev.anywork.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    RETAILER_READ("management:read"),
    RETAILER_UPDATE("management:update"),
    RETAILER_CREATE("management:create"),
    RETAILER_DELETE("management:delete"),
    WHOLESALER_READ("management:read"),
    WHOLESALER_UPDATE("management:update"),
    WHOLESALER_CREATE("management:create"),
    WHOLESALER_DELETE("management:delete"),
    IAM_READ("management:read"),
    IAM_CREATE("management:create")


    ;

    @Getter
    private final String permission;
}

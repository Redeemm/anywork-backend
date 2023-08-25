package com.reddev.anywork.utils.otp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
public class VerifyOtp {

    private String mobileNumber;
    private String email;
    private Integer otp;
    private long expiryTime;
}

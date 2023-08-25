package com.reddev.anywork.utils.otp;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestOtp {

    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;



}

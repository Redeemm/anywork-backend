package com.reddev.anywork.utils.ForgotPassword;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPassword {

    private String email;
    private String phoneNumber;
    private String oldPassword;
    private String newPassword;
    private String origin;
}

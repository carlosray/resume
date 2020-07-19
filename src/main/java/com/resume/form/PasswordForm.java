package com.resume.form;

import com.resume.annotation.constraints.PasswordStrengthConstraint;
import com.resume.annotation.constraints.PasswordsEqualConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@PasswordsEqualConstraint
public class PasswordForm implements Serializable {
    private static final long serialVersionUID = 631404204513711528L;

    @PasswordStrengthConstraint
    private String password;
    private String confirmPassword;
}

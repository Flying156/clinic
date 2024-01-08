package com.learn.clinic.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改密码传输
 *
 * @author Milk
 * @version 2024/1/8 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {

    private String username;

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;

}

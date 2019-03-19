package com.tutorial.model;

import lombok.Data;

/**
 * @author jimmy
 * @date 2019-03-1922:42
 */
@Data
public class UserAccountModel {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 用户userName
     */
    private String userName;
    /**
     * 账户email
     */
    private String email;

    private Long balance;
}

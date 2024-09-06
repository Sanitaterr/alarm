package com.jzy.alarmsystembackend.pojo.VO.user;

import lombok.Data;

import java.io.Serializable;

/**
 * username<br>
 * password
 */
@Data
public class UserParamVO1 implements Serializable {

    private static final long serialVersionUID = 2408003453460409732L;
    private String username;
    private String password;
}

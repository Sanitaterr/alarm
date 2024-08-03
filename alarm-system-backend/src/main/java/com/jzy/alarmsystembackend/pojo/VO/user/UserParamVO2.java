package com.jzy.alarmsystembackend.pojo.VO.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserParamVO2 implements Serializable {

    private static final long serialVersionUID = 1433826799775475102L;
    private String username;
    private String password;
    private String confirmedPassword;
}

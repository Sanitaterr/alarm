package com.jzy.alarmsystembackend.service.impl.user;

import com.jzy.alarmsystembackend.mapper.UserMapper;
import com.jzy.alarmsystembackend.pojo.DO.User;
import com.jzy.alarmsystembackend.pojo.DTO.UserDetailsImpl;
import com.jzy.alarmsystembackend.service.user.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getInfo() {
        UserDetailsImpl loginUser = getUserDetailsFromAuth();
        return loginUser.getUser();
    }


    @Override
    public Integer updateFirm(Integer firmId) {
        UserDetailsImpl loginUser = getUserDetailsFromAuth();
        User user = loginUser.getUser();

        user.setFirmId(firmId);

        return userMapper.updateById(user);
    }


    private static UserDetailsImpl getUserDetailsFromAuth() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        return loginUser;
    }

}

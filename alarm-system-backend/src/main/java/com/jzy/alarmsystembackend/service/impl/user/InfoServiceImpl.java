package com.jzy.alarmsystembackend.service.impl.user;

import com.jzy.alarmsystembackend.pojo.DO.User;
import com.jzy.alarmsystembackend.pojo.DTO.UserDetailsImpl;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.service.user.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public AjaxResult getInfo() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("photo", user.getPhoto());
        return AjaxResult.successProjectInfoData(200, "get info success", map);
    }
}

package com.jzy.alarmsystembackend.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzy.alarmsystembackend.mapper.UserMapper;
import com.jzy.alarmsystembackend.pojo.DO.User;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.service.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AjaxResult register(String username, String password, String confirmedPassword) {
        if (username == null) {
            return AjaxResult.error("用户名不能为空");
        }
        if (password == null || confirmedPassword == null) {
            return AjaxResult.error("密码不能为空");
        }

        username = username.trim();
        if (username.length() == 0) {
            return AjaxResult.error("用户名不能为空");
        }

        if (password.length() == 0 || confirmedPassword.length() == 0) {
            return AjaxResult.error("密码不能为空");
        }

        if (username.length() > 100) {
            return AjaxResult.error("用户名长度不能大于255");
        }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
            return AjaxResult.error("密码长度不能大于255");
        }

        if (!password.equals(confirmedPassword)) {
            return AjaxResult.error("两次输入的密码不一致");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            return AjaxResult.error("用户名已存在");
        }

        String encodedPassword = passwordEncoder.encode(password);
        String photo = "https://profile-avatar.csdnimg.cn/1dd662a7799a42f0a187eb69e74d73a3_weixin_52971316.jpg!1";
        User user = new User(null, username, encodedPassword, photo);
        userMapper.insert(user);

        return AjaxResult.success("register success");
    }
}

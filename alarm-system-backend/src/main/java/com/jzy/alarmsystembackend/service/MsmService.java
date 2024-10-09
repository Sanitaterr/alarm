package com.jzy.alarmsystembackend.service;

import java.util.Map;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/10/6 13:27
 * @Description: TODO
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}

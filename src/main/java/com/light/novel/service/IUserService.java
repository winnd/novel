package com.light.novel.service;


import com.light.novel.common.ServerResponse;
import com.light.novel.enity.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);

    public ServerResponse<String> register(User user);

    public ServerResponse<String> checkValid(String str, String type);
}
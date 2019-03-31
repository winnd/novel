package com.light.novel.service;


import com.light.novel.common.ServerResponse;
import com.light.novel.enity.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);

    public ServerResponse<String> register(User user);

    public ServerResponse<String> checkValid(String str, String type);

    public ServerResponse selectQuestion(String username);

    public ServerResponse<String> checkAnswer(String username, String question, String answer);

    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken);

    public ServerResponse<String> resetPasswored(String passwordOld, String passwordNew, User user);
}

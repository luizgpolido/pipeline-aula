package com.vemser.rest.data.factory;

import com.vemser.rest.model.request.LoginRequest;

public class LoginDataFactory {

    public static LoginRequest validUserLogin(){
        return new LoginRequest("emilys", "emilyspass");
    }

    public static LoginRequest invalidUserLogin(){
        return new LoginRequest("luizpolido", "teste");
    }

    public static LoginRequest nullUserLogin(){
        return new LoginRequest("", "");
    }
}

package com.pramu.fasteats.service;

import com.pramu.fasteats.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwtToken) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}

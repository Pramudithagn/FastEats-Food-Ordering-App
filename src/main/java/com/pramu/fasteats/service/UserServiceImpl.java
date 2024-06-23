package com.pramu.fasteats.service;

import com.pramu.fasteats.model.User;
import com.pramu.fasteats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public User findUserByJwtToken(String jwtToken) throws Exception {

        String email = jwtService.getEmailFromToken(jwtToken);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User not found !");
        }
        return user;
    }
}

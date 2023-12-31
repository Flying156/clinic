package com.learn.clinic.service.Impl;

import com.learn.clinic.mapper.UserMapper;
import com.learn.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Milk
 * @version 2023/12/27 9:46
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;


}

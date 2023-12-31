package com.learn.clinic.controller;

import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.learn.clinic.dao.dto.UserDTO;
import com.learn.clinic.service.UserService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户
 *
 * @author Milk
 * @version 2023/12/27 16:11
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Producer producer;

    @PutMapping("/register")
    public void saveUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
    }

    @GetMapping("/captcha")
    public Result<Map<String, String>> generateCode() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img  = str + encoder.encode(outputStream.toByteArray());

        HashMap<String, String> kaptchVoMap = new HashMap<>();
        kaptchVoMap.put("uuid", key);
        kaptchVoMap.put("code", base64Img);

        return Results.success(kaptchVoMap);
    }
}

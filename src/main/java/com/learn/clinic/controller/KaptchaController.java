package com.learn.clinic.controller;

import com.google.code.kaptcha.Producer;
import com.learn.clinic.uitls.RedisUtil;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码
 *
 * @author Milk
 * @version 2024/1/3 10:20
 */

@RequiredArgsConstructor
@RestController
public class KaptchaController {

    private final Producer producer;
    private final RedisUtil redisUtil;
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
        // 设置过期时间
        redisUtil.hSet("code", key, code);
        redisUtil.setEx("code", 120L, TimeUnit.SECONDS);

        return Results.success(kaptchVoMap);
    }

}

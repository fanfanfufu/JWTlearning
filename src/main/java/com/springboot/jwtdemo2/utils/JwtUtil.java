package com.springboot.jwtdemo2.utils;

import com.springboot.jwtdemo2.commons.Constant;
import com.springboot.jwtdemo2.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: JWT工具类
 * @Author 傅琦
 * @Date 2019/4/13 9:29
 * @Version V1.0
 */
public class JwtUtil {
    /**
     * 生成密钥
     * @return SecretKey
     */
    private static SecretKey generalKey(){
        String stringKey = Constant.JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return secretKey;
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        return key;
    }

    /**
     * 根据用户信息为其签发tocken
     * @param user
     * @return String
     */
    public static String generalTocken(User user){
        try {
            // 设置签发算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            // 生成密钥
            SecretKey key = generalKey();
//            System.out.println("签发时生成的key为：" + key);
            // 设置私有声明
            Map<String, Object> claims = new HashMap<>(16);
            claims.put("userId", user.getUserId());
            claims.put("username", user.getUsername());
            // 记录生成JWT的时间
            long nowMillis = System.currentTimeMillis();
            Date nowTime = new Date(nowMillis);
            // 设置过期时间
            long expMillis = nowMillis + Constant.EXP_TIME_LENGTH;
            Date expTime = new Date(expMillis);
            // 创建tocken构建器实例
            JwtBuilder jwtBuilder = Jwts.builder()
                    .setClaims(claims)
                    .setId(UUID.randomUUID().toString())
                    .setIssuer("FUQI-PC")
                    .setIssuedAt(nowTime)
                    .setExpiration(expTime)
                    .setSubject("users")
                    .signWith(signatureAlgorithm, key);
            return jwtBuilder.compact();
        } catch (Exception e) {
            e.printStackTrace();
            return "生成tocken失败";
        }
    }

    /**
     * 解析tocken，从中提取出声明信息
     * @param tocken
     * @return Claims
     * @throws Exception
     */
    public static Claims parseTocken(String tocken) throws Exception{
        SecretKey key = generalKey();
//        System.out.println("解析tocken时生成的key为：" + key);
        // 获取tocken中的声明部分
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(tocken).getBody();
        return claims;
    }
}

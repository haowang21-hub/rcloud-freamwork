package com.rcloud.auth.common.util.jwt;

import org.joda.time.DateTime;

import com.rcloud.auth.common.constatns.AuthConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * <Description> JWT工具类
 * 
 * @author wanghao
 * @CreateDate 2018年4月18日 下午1:27:07
 * @since V1.0
 * @see com.rcloud.auth.common.util.jwt
 */
public class JWTHelper {

    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKeyPath
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(IJWTInfo jwtInfo, String priKeyPath, int expire) throws Exception {
        String compactJws = Jwts.builder().setSubject(jwtInfo.getUniqueName())
            .claim(AuthConstants.JWT_KEY_USER_ID, jwtInfo.getId()).claim(AuthConstants.JWT_KEY_NAME, jwtInfo.getName())
            .setExpiration(DateTime.now().plusSeconds(expire).toDate())
            .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath)).compact();
        return compactJws;
    }

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKey
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(IJWTInfo jwtInfo, byte priKey[], int expire) throws Exception {
        String compactJws = Jwts.builder().setSubject(jwtInfo.getUniqueName())
            .claim(AuthConstants.JWT_KEY_USER_ID, jwtInfo.getId()).claim(AuthConstants.JWT_KEY_NAME, jwtInfo.getName())
            .setExpiration(DateTime.now().plusSeconds(expire).toDate())
            .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey)).compact();
        return compactJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath))
            .parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), getObjectValue(body.get(AuthConstants.JWT_KEY_USER_ID)),
            getObjectValue(body.get(AuthConstants.JWT_KEY_NAME)));
    }

    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), getObjectValue(body.get(AuthConstants.JWT_KEY_USER_ID)),
            getObjectValue(body.get(AuthConstants.JWT_KEY_NAME)));
    }
}

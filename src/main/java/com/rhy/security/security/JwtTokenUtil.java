package com.rhy.security.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Herion_Rhy
 * @Description: 生成令牌，验证等等一些操作
 * @Date: Created in 2020/1/1 12:49
 * @Modified By:
 * @Version: 1.0.0
 */
@Component
public class JwtTokenUtil {
    /**
     * 密匙(应该定期更换，这样即使有人解密token不知道secret就无法破解)
     * 在我看来这个应该是十天或者半个月系统后台自动更换的
     * 可以放在创建用户的时候一起存储
     * 但是这样就得定期刷新数据库的secret
     * 不好说如何更好的应用  但是定期刷新是肯定的
     * 这里测试所以就在配置文件中设置
     */
    @Value("${jwt.token.secret}")
    private String secret;
    /**
     * 过期时间(毫秒)
     */
    @Value("${jwt.token.expiration}")
    private Long expiration;
    /**
     * 头部信息
     */
    private String header = "Authentication";
    /**
     * 从数据声明生成令牌
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String,Object> claims){
        Date expirationDate = new Date(System.currentTimeMillis()+expiration);
        return Jwts.builder() //返回JwtBuilder构建起器
                .setClaims(claims) //指定要包含在令牌中的声明的逗号分隔列表。
                .setExpiration(expirationDate)  //指定过期时间
                .signWith(SignatureAlgorithm.HS512, secret)//用什么加密方式签名
                .compact(); //执行加密并返回Token令牌
    }
    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser() //获得JwtParser转换器
                .setSigningKey(secret) //使用什么加密方式
                .parseClaimsJws(token) //需要解析的token令牌
                .getBody();
        }catch (Exception e){
            //有可能解析失败返回null
            claims = null;
        }
        return claims;
    }
    /**
     * 生成令牌
     * 标准中注册的声明（建议但不强制使用）
     *
     *     iss: jwt 签发者
     *     sub: jwt 所面向的用户
     *     aud: 接收 jwt 的一方
     *     exp: jwt 的过期时间，这个过期时间必须大于签发时间
     *     nbf: 定义在什么时间之前，该 jwt 都不可用
     *     iat: jwt 的签发时间
     *     jti: jwt 的唯一标识
     * @param userDetails 数据声明
     * @return 令牌
     */
    public String createToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT,userDetails.getUsername());  //jwt 所面向的用户
        claims.put(Claims.ISSUED_AT,new Date()); //jwt 的签发时间
        return createToken(claims);
    }
    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String userName;
        try{
            //解析token获得Claims对象
            Claims claims = getClaimsFromToken(token);
            userName = claims.getSubject(); //存储的用户名
        }catch (Exception e){
            //可能获取不到就会出现异常
            userName = null;
        }
        return userName;
    }
    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public boolean isTokenExpired(String token){
        try{
            Claims claims = getClaimsFromToken(token);
            //获得过期时间
            Date expiration = claims.getExpiration();
            //before：此日期是否在指定日期之前
            return expiration.before(new Date());
        }catch (Exception e){
            //解析失败直接返回过期
            return true;
        }
    }
    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token){
        String refreshToken;
        try{
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT,new Date()); //重新输入 签发时间
            refreshToken = createToken(claims);
        }catch (Exception e){
            refreshToken = null;
        }
        return refreshToken;
    }
    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token,UserDetails userDetails){
        JwtUserImpl jwtUser = (JwtUserImpl) userDetails;
        String userName = getUsernameFromToken(token);
        return (userName.equals(jwtUser.getUsername()) && !isTokenExpired(token));
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration =expiration;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}

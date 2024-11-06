package todolist.authinformation.service.impl;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import todolist.authinformation.domain.AuthInfo;
import todolist.authinformation.dto.AuthInfoDto;
import todolist.authinformation.repository.AuthInfoRepository;
import todolist.authinformation.service.AuthInfoService;

@Service
@RequiredArgsConstructor
public class AuthInfoServiceImpl implements AuthInfoService{

    private final Key secretKey = Keys.hmacShaKeyFor("This key will be changed to enviroment variable key".getBytes());
    private final AuthInfoRepository authInfoRepository;

    @Override
    public String createToken(AuthInfoDto authInfoDto)
    {
        // String token = tokenVerification(authInfoDto.getUser_id());

        // if(){}
        // 토큰 생성부
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime expireTime = nowTime.plusHours(1);
        String token =  Jwts.builder()
                    .subject(authInfoDto.getUser_id().toString())
                    .claim("id", authInfoDto.getId())
                    .claim("user_type", authInfoDto.getUser_type())
                    .issuedAt(java.sql.Timestamp.valueOf(nowTime))
                    .expiration(java.sql.Timestamp.valueOf(expireTime))
                    .signWith(secretKey)
                    .compact();

        // 토큰 저장부
        AuthInfo saveInfo = AuthInfo.builder()
                                    .token(token)
                                    .user_id(authInfoDto.getUser_id())
                                    .token_create_time(nowTime)
                                    .token_expire_time(expireTime)
                                    .build();

        authInfoRepository.save(saveInfo);
        
        // 토큰 반환부
        return token;
    }

    // 토큰 확인
    private String tokenVerification(Long user_id)
    {
        return "";
    };
}

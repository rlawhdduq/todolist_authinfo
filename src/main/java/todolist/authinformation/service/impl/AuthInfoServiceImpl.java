package todolist.authinformation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

import todolist.authinformation.api.AuthApi;
import todolist.authinformation.domain.AuthInfo;
import todolist.authinformation.dto.AuthInfoDto;
import todolist.authinformation.repository.AuthInfoRepository;
import todolist.authinformation.service.AuthInfoService;

@Service
@RequiredArgsConstructor
public class AuthInfoServiceImpl implements AuthInfoService{

    private static final Logger log = LoggerFactory.getLogger(AuthInfoServiceImpl.class);
    private final SecretKey secretKey = Keys.hmacShaKeyFor("This key will be changed to enviroment variable key".getBytes());
    @Autowired
    private final AuthInfoRepository authInfoRepository;

    @Override
    public String createToken(AuthInfoDto authInfoDto)
    {
        tokenVerification(authInfoDto.getUser_id());
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime expireTime = nowTime.plusHours(1);

        // 토큰 생성부
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
    private void tokenVerification(Long user_id)
    {
        log.info("토큰 확인 시작");
        if(authInfoRepository.existsByUserId(user_id))
        {
            authInfoRepository.deleteByUserId(user_id);
            log.info("토큰 삭제 완료");
        }
        log.info("토큰 확인 종료");
    };
}

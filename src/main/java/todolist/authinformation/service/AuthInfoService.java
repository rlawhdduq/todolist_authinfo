package todolist.authinformation.service;

import todolist.authinformation.dto.AuthInfoDto;

public interface AuthInfoService {
    String createToken(AuthInfoDto authInfoDto);   // 토큰 발급
}

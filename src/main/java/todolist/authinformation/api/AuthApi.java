package todolist.authinformation.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;
import todolist.authinformation.dto.AuthInfoDto;
import todolist.authinformation.service.AuthInfoService;

@RestController
@RequiredArgsConstructor
public class AuthApi {

    private final AuthInfoService authInfoService;
    private static final Logger log = LoggerFactory.getLogger(AuthApi.class);

    @PostMapping("/rest/token")
    public String restToken(@RequestBody AuthInfoDto authInfoDto)
    {
        String returnToken = authInfoService.createToken(authInfoDto);
        return returnToken;
    }

    // 이건 추후 사용할 mq방식,,, 이긴 한데 둘 다 위에꺼 써도 상관은 없을듯?
    @PostMapping("/api/token")
    public String token(@RequestBody AuthInfoDto authInfoDto)
    {
        String returnToken = authInfoService.createToken(authInfoDto);
        return returnToken;
    }
}

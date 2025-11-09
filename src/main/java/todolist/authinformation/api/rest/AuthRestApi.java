package todolist.authinformation.api.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;
import todolist.authinformation.dto.AuthInfoDto;
import todolist.authinformation.service.AuthInfoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class AuthRestApi {

    private final AuthInfoService authInfoService;
    private static final Logger log = LoggerFactory.getLogger(AuthRestApi.class);

    @PostMapping("/token")
    public String restToken(@RequestBody AuthInfoDto authInfoDto)
    {
        log.info("Dto Check :: ");
        String returnToken = authInfoService.createToken(authInfoDto);
        
        return returnToken;
    }
}

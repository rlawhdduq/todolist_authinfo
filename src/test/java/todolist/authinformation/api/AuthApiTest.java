package todolist.authinformation.api;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import todolist.authinformation.dto.AuthInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthApiTest {
    private static final Logger log = LoggerFactory.getLogger(AuthApiTest.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void testCreateToken() 
    throws Exception
    {
        log.info("token CreateStart");
        String createTokenRequest = objectMapper.writeValueAsString(new AuthInfoDto(49L, "test1238", "NC"));

        ResultActions resultActions = mockMvc.perform(post("/api/token")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(createTokenRequest))
                                            .andExpect(status().isOk());
        log.info("return json : " + resultActions.andReturn().getResponse().getContentAsString());
        log.info("token CreateEnd");
    }
    
}

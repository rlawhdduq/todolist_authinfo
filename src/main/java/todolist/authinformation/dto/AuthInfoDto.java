package todolist.authinformation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfoDto {
    private Long user_id;
    private String id;
    private String user_type;
}

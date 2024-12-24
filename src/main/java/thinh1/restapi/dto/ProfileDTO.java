package thinh1.restapi.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProfileDTO {
    private String profileId;

    private String email;

    private String name;

    private String password;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}

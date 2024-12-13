package thinh1.restapi.io;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorObject {
    private String StatusCode;
    private String Message;
    private Date timestamp;
    private String errorCode;
}

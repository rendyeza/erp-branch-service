package id.nexbyte.erp.branchservice.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(builderClassName = "ErrorBuilder")
public class Error implements Serializable {

    private static final long serialVersionUID = -4739420524064775345L;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime timestamp;

    private HttpStatus status;

    private String message;

    private String debugMessage;

    private List<SubError> subErrors;

    public static class ErrorBuilder {
        private OffsetDateTime timestamp = OffsetDateTime.now();
    }

    public Map<String, Object> toAttributeMap() {
        return Map.of(
                "timestamp", getTimestamp(),
                "status", getStatus(),
                "message", getMessage(),
                "subErrors", getSubErrors(),
                "debugMessage", getDebugMessage()
        );
    }
}

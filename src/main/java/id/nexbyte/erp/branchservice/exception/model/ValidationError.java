package id.nexbyte.erp.branchservice.exception.model;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "ValidationErrorBuilder")
public class ValidationError extends SubError implements Serializable {

    private static final long serialVersionUID = -861220827570029288L;

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public static class ValidationErrorBuilder {
        private String object;
        private String field;
        private Object rejectedValue;
        private String message;
    }

}

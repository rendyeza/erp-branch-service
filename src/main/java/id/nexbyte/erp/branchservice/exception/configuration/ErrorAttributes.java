package id.nexbyte.erp.branchservice.exception.configuration;

import id.nexbyte.erp.branchservice.exception.model.Error;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class ErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Error error = Error.builder().build();
        return error.toAttributeMap();
    }
}

package com.snowapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskVariable {

    String number;
    String sys_id;
    String variables_request_type;

    public String getNumber() {
        return number;
    }

    public String getSys_id() {
        return sys_id;
    }

    public String getVariables_request_type() {
        return variables_request_type;
    }

}


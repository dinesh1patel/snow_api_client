package com.snowapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
    String link;
    String value;

    public String getLink() {
        return link;
    }

    public String getValue() {
        return value;
    }
}


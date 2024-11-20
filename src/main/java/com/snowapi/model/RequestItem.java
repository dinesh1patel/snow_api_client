package com.snowapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestItem {
    String link;
    String value;

    public String getLink() {
        return link;
    }

    public String getValue() {
        return value;
    }
}


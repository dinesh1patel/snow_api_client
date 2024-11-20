package com.snowapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    String number;
    Request request;
    RequestItem request_item;

    public RequestItem getRequest_item() {
        return request_item;
    }

    public String getNumber() {
        return number;
    }

    public Request getRequest() {
        return request;
    }
}


package com.snowapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Result {
    Task [] result;

    public Task[] getResult() {
        return result;
    }
}

package com.snowapi.service;

import com.snowapi.model.ResultTask;
import com.snowapi.model.ResultTaskVariable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.google.gson.Gson;

import java.io.File;
import java.util.*;

@Service
public class ConsumeWebService {

    private final String username;
    private final String password;

    RestTemplate restTemplate = new RestTemplate();

    public ConsumeWebService() {
        this.username = "null";
        this.password = "null";
    }

    public ConsumeWebService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ResultTask getTasksAssignedToCFTL2()
    {
        String target = "https://mojcppprod.service-now.com/api/now/table/sc_task";

        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(target)
                .queryParam("sysparm_query","assigned_toISEMPTY")
                .queryParam("assignment_group","8b2cacecdbc8b8905729e25cd39619fa")
                .queryParam("state","1")
                .build();

        ResponseEntity<String> out = restTemplate.exchange(builder.toUriString(),HttpMethod.GET, requestEntity,
                String.class);
        String json =  out.getBody();

        Gson gson = new Gson();

        return gson.fromJson(json, ResultTask.class);
    }

    public ResultTaskVariable getAllVariablesForRITMs(String requestItemId)
    {
        String target = "https://mojcppprod.service-now.com/api/now/table/sc_req_item";

        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(target)
                .queryParam("sysparm_fields","number, sys_id, variables.request_type,variables.ccd_account_profile_add,variables.email,variables.ccd_account_amend_type,variables.var_set_user_to_mirror,variables.ccd_account_date_required_by,variables.ccd_account_jurisdiction,variable.firstname,variables.surname,variable.ccd_account_urgency,variables.ccd_account_new_first_name,variables.ccd_account_profile_remove_roles,variables.ccd_account_new_email_address,variables.ccd_account_new_surname")
                .queryParam("sysparm_query","sys_id="+requestItemId)
                .queryParam("sysparm_display_value","true")
                .build();

        ResponseEntity<String> out = restTemplate.exchange(builder.toUriString(),HttpMethod.GET, requestEntity,
                String.class);
        String json =  out.getBody();
        json = json.replaceAll("variables.","variables_");

        Gson gson = new Gson();

        return gson.fromJson(json, ResultTaskVariable.class);
    }

    public String uploadAttachment(String sys_id, String file) throws Exception {

        String target = "https://mojcppprod.service-now.com/api/now/attachment/upload";

        HttpHeaders headers = getHttpHeaders();

        // ContentType
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();

        // Common form parameters.
        multipartBodyBuilder.part("table_name", "incident");
        multipartBodyBuilder.part("table_sys_id", sys_id);

        // Load a file from disk.
        Resource file1 = new FileSystemResource(file);
        multipartBodyBuilder.part("uploadFile", file1, MediaType.TEXT_PLAIN);

        // multipart/form-data request body
        MultiValueMap<String, HttpEntity<?>> multipartBody = multipartBodyBuilder.build();

        // The complete http request body.
        HttpEntity<MultiValueMap<String, HttpEntity<?>>> httpEntity = new HttpEntity<>(multipartBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(target,
                httpEntity,
                String.class);

        System.out.println(responseEntity);

        return "" + responseEntity;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String authStr = this.username + ":" + this.password;

        String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

        headers.add("Authorization", "Basic " + base64Creds);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}

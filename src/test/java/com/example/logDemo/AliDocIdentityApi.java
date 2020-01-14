package com.example.logDemo;

import com.example.logDemo.vo.PicWordsIdentifyRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AliDocIdentityApi {

  private RestTemplate rt = new RestTemplate();
  private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
  private String API_URL = "https://ocrapi-document.taobao.com/ocrservice/document";
  private ObjectMapper objectMapper = new ObjectMapper();

  public AliDocIdentityApi() {
    headers.add("Authorization", "APPCODE 9de0a1737a3242f09f56ad8da29fe985");
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  public List<String> getWordsFromPic(String fileUrl) throws JsonProcessingException {
    PicWordsIdentifyRequest req = new PicWordsIdentifyRequest();
    req.setUrl(fileUrl);
    return doGetWordInPic(req);
  }

  public List<String> doGetWordInPic(PicWordsIdentifyRequest req) throws JsonProcessingException {
    String body = objectMapper.writeValueAsString(req);
    HttpEntity<String> reqEntity = new HttpEntity<>(body, headers);
    String picSentences = rt.postForObject(API_URL, reqEntity, String.class);
    List<String> sentences = JsonPath.read(picSentences, "$.prism_wordsInfo[*].word");
    return sentences;
  }


  @Test
  public void test() {

  }
}

package com.example.logDemo;

import com.example.logDemo.vo.PicWordsIdentifyRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@Slf4j
public class DownLoadTest {


  private void getWords() {


  }

  @Test
  public void test() throws IOException {
    AliDocIdentityApi api = new AliDocIdentityApi();
    List<String> urls = Files.readAllLines(Paths.get("d:/files.txt"));
//    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    int i = 0;
    for (String fileUrl: urls) {
      List<String> sentences = api.getWordsFromPic(fileUrl);
      i++;
      Files.write(Paths.get("C:\\Users\\Dev\\Desktop\\pic\\" + i + ".txt"), sentences);
      System.out.println("--------------------------------------------------");

//    int i = 0;
//    for (String fileUrl: urls) {
//      ResponseEntity<byte[]> entity = rt.getForEntity(fileUrl, byte[].class);
//      i++;
//      try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Dev\\Desktop\\pic\\" + i + ".png")) {
//        fos.write(entity.getBody());
//      }
//    }
    }

  }

  @Test
  public void testJson() throws IOException {
//    PicWordsIdentifyRequest req = new PicWordsIdentifyRequest();
//    req.setUrl("urls.get(0)");
//    log.info(objectMapper.writeValueAsString(req));

    byte[] bytes = Files.readAllBytes(Paths.get("d:/json.txt"));
    List<String> ret = JsonPath.read(new String(bytes), "$.prism_wordsInfo[*].word");
    ret.stream().forEach(r -> System.out.println(r));
  }
}

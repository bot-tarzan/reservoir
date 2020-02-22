package com.bothub.reservoir.biz.lark.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lark")
@Slf4j
public class LarkApi {

  private final ObjectMapper objectMapper;

  @Autowired
  public LarkApi(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @PostMapping("callback")
  public Object callback(@RequestBody LarkCallback callback) throws JsonProcessingException {
    if (callback.getType().equals("url_verification")) {
      Map<String, String> map = new HashMap<>();
      map.put("challenge", callback.getChallenge());
      return map;
    }
    String rawJson = objectMapper.writeValueAsString(callback);
    log.info(rawJson);
    return null;
  }
}

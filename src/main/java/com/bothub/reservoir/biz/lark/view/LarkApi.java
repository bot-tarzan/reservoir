package com.bothub.reservoir.biz.lark.view;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lark")
public class LarkApi {

  @PostMapping("callback")
  public Object callback(@RequestBody LarkCallback callback) {
    if (callback.getType().equals("url_verification")) {
      Map<String, String> map = new HashMap<>();
      map.put("challenge", callback.getChallenge());
      return map;
    }
    return null;
  }
}

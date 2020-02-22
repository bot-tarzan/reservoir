package com.bothub.reservoir.biz.lark.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lark")
@Slf4j
public class LarkApi {

  private final ObjectMapper objectMapper;

  private final ArrayBlockingQueue<String> pool;

  @Autowired
  public LarkApi(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.pool = new ArrayBlockingQueue<>(2000);
  }

  @PostMapping("{robot}/callback/")
  public Object callback(@RequestBody LarkCallback callback) throws JsonProcessingException {
    if (callback.getType().equals("url_verification")) {
      Map<String, String> map = new HashMap<>();
      map.put("challenge", callback.getChallenge());
      return map;
    }
    String rawJson = objectMapper.writeValueAsString(callback);
    try {
      boolean ret = pool.offer(rawJson, 800, TimeUnit.MICROSECONDS);
      if (!ret) {
        log.error("cache event failed, {}", rawJson);
      }
    } catch (InterruptedException ignore) {

    }
    return null;
  }

  @GetMapping("{robot}/stats")
  public Map<String, Object> stats(
      @PathVariable String robot
  ) {
    Map<String, Object> map = new HashMap<>();
    map.put("size", this.pool.size());
    return map;
  }

  @GetMapping("{robot}/getUpdates")
  public List<String> getUpdates(
      @PathVariable String robot
  ) {
    List<String> rets = IntStream.range(0, 5).mapToObj(it -> pool.poll())
        .filter(Objects::nonNull).collect(Collectors.toList());
    return rets;
  }
}

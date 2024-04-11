package com.b.webapp.web5b.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b.webapp.web5b.models.User;

@RestController
@RequestMapping("/users")
public class UserController {
  // 1 objeto de prueba
  private ArrayList<User> users = new ArrayList<User>();
  @Value("${config.defaultMessage}")
  private String defaultMessage;

  @Value("#{'${config.valueList}'.split(',')}")
  private List<String> valueList;

  @Value("#{${config.valuesMap}}")
  private Map<String, Object> valuesMap = new HashMap<>();

  @Autowired
  private Environment env;

  @GetMapping
  public List<User> getUsers() {
    return users;
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable(name = "id", required = false) Long id) {
    return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
  }

  @PostMapping
  public User create(@RequestBody User user) {
    user.setId((long) (users.size() + 1));
    users.add(user);
    return user;
  }

  @GetMapping("/message")
  public Map<String, Object> message() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", defaultMessage);
    response.put("message2", env.getProperty("config.defaultMessage"));
    response.put("valueList", valueList);
    response.put("valuesMap", valuesMap);
    response.put("message3", env.getProperty("config.valuesMap.product"));
    return response;
  }

  @GetMapping("/redirect")
  //forward, redirije la peticion al controlador que se le indique, no cambia la url
  public String redirect() {
    return "forward:/users";
  }
}

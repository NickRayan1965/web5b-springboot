package com.b.webapp.web5b.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private Long id;
  private String work_email;
  private String names;
  private String last_name;
  private String management;
  private String password;
  private String position;
  private Boolean everythingPresented;
  private Boolean everythingApproved;
  private Boolean isActive;
  private User responsible;
}

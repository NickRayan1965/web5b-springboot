package com.b.webapp.web5b.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
  private Long id;
  private String work_email;
  private String names;
  private String last_name;
  private String management;
  private String position;
  private Boolean everythingPresented;
  private Boolean everythingApproved;
  private Boolean isActive;
  private UserDto responsible;
}

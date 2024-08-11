package com.eric218110.project.zeta.domain.http.institution;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowInstitutionResponse {
  private UUID uuid;
  private String ispb;
  private String name;
  private double code;
  private String fullName;
}


package com.eric218110.project.zeta.infra.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BanksResponse {
  String ispb;
  String name;
  Integer code;
  String fullName;
}

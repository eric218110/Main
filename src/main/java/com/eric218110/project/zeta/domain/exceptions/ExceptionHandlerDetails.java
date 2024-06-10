package com.eric218110.project.zeta.domain.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionHandlerDetails {
  LocalDateTime timestamp;
  String status;
  String title;
  Map<String, String> details;
  String developMessage;
}

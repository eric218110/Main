package com.eric218110.project.zeta.domain.http.account;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import com.eric218110.project.zeta.domain.entities.account_type.AccountTypeEntity;
import com.eric218110.project.zeta.domain.entities.colors.ColorsEntity;
import com.eric218110.project.zeta.domain.entities.institutions.InstitutionsEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowAccountResponse {
  private UUID uuid;
  private String description;
  private InstitutionsEntity institution;
  private AccountTypeEntity type;
  private ColorsEntity color;
  private ColorsEntity background;
  private BigDecimal balance;
  private Instant creationTimestamp;
}


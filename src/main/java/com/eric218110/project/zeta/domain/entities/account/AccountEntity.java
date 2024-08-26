package com.eric218110.project.zeta.domain.entities.account;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import com.eric218110.project.zeta.domain.entities.account_type.AccountTypeEntity;
import com.eric218110.project.zeta.domain.entities.colors.ColorsEntity;
import com.eric218110.project.zeta.domain.entities.institutions.InstitutionsEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity()
@Table(name = "accounts")
public class AccountEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "account_id")
  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "institution_id")
  private InstitutionsEntity institution;

  @ManyToOne
  @JoinColumn(name = "account_type_id")
  private AccountTypeEntity type;

  @ManyToOne
  @JoinColumn(name = "color_id")
  private ColorsEntity color;

  @ManyToOne
  @JoinColumn(name = "background_id")
  private ColorsEntity background;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  private String description;
  private BigDecimal balance;

  @CreationTimestamp
  private Instant creationTimestamp;
}

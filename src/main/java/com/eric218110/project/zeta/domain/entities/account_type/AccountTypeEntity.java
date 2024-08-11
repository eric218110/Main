package com.eric218110.project.zeta.domain.entities.account_type;

import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "account_types")
public class AccountTypeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "account_type_id")
  private UUID uuid;

  private String name;

  private String key;

  @CreationTimestamp
  private Instant creationTimestamp;

}

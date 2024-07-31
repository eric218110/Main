package com.eric218110.project.zeta.domain.entities.institutions;

import java.time.Instant;
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
@Table(name = "institutions")
public class InstitutionsEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "institution_id")
  private String uuid;

  private String ispb;
  private String name;
  private double code;
  private String flag;
  private String fullName;

  @CreationTimestamp
  private Instant creationTimestamp;

}

package com.eric218110.project.zeta.domain.entities.colors;

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
@Table(name = "colors")
public class ColorsEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "color_id")
  private String uuid;

  private String name;

  @CreationTimestamp
  private Instant creationTimestamp;

}

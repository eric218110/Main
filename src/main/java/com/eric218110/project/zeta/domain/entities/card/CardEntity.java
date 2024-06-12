package com.eric218110.project.zeta.domain.entities.card;

import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
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
@Table(name = "cards")
public class CardEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String uuid;

  private String name;
  private String color;
  private double currentLimit;
  private String flag;
  private String dueDate;
  private String closeDate;
  private String type;

  @CreationTimestamp
  private Instant creationTimestamp;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

}

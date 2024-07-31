package com.eric218110.project.zeta.infra.repositories.database.institution;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eric218110.project.zeta.domain.entities.institutions.InstitutionsEntity;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionsEntity, UUID> {

  InstitutionsEntity findByIspb(String ispb);
}

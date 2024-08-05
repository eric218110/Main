package com.eric218110.project.zeta.data.usecases.institution;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.entities.institutions.InstitutionsEntity;
import com.eric218110.project.zeta.domain.usecases.institution.LoadInstitutionByUuid;
import com.eric218110.project.zeta.infra.repositories.database.institution.InstitutionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InstitutionService implements LoadInstitutionByUuid {
  private final InstitutionRepository institutionRepository;


  @Override
  public InstitutionsEntity loadInstitutionByUuid(String uuid) {

    var institution = this.institutionRepository.findById(UUID.fromString(uuid));

    if (institution.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found institution");
    }

    return institution.get();
  }
}

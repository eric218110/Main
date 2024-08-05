package com.eric218110.project.zeta.domain.usecases.institution;

import com.eric218110.project.zeta.domain.entities.institutions.InstitutionsEntity;

public interface LoadInstitutionByUuid {
  InstitutionsEntity loadInstitutionByUuid(String uuid);
}

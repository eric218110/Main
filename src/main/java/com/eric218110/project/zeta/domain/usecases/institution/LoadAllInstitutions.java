package com.eric218110.project.zeta.domain.usecases.institution;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eric218110.project.zeta.domain.http.institution.ShowInstitutionResponse;

public interface LoadAllInstitutions {
  Page<ShowInstitutionResponse> loadAllInstitutions(Pageable pageable);
}

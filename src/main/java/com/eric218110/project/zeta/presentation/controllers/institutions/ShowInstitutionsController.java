package com.eric218110.project.zeta.presentation.controllers.institutions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.institution.ShowInstitutionResponse;
import com.eric218110.project.zeta.domain.usecases.institution.LoadAllInstitutions;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("institutions")
public class ShowInstitutionsController {

  final LoadAllInstitutions loadAllInstitutions;

  @GetMapping("show")
  public Page<ShowInstitutionResponse> show(Pageable pageable) {

    return this.loadAllInstitutions.loadAllInstitutions(pageable);
  }
}

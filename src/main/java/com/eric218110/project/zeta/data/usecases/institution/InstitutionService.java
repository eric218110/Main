package com.eric218110.project.zeta.data.usecases.institution;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.entities.institutions.InstitutionsEntity;
import com.eric218110.project.zeta.domain.http.institution.ShowInstitutionResponse;
import com.eric218110.project.zeta.domain.usecases.institution.LoadAllInstitutions;
import com.eric218110.project.zeta.domain.usecases.institution.LoadInstitutionByUuid;
import com.eric218110.project.zeta.infra.repositories.database.institution.InstitutionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InstitutionService implements LoadInstitutionByUuid, LoadAllInstitutions {
  private final InstitutionRepository institutionRepository;


  @Override
  public InstitutionsEntity loadInstitutionByUuid(String uuid) {

    var institution = this.institutionRepository.findById(UUID.fromString(uuid));

    if (institution.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found institution");
    }

    return institution.get();
  }


  @Override
  public Page<ShowInstitutionResponse> loadAllInstitutions(Pageable pageable) {
    Pageable sortedByName = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
        Sort.by(Sort.Direction.ASC, "name"));

    var institutions = this.institutionRepository.findAll(sortedByName);

    return institutions.map(institution -> ShowInstitutionResponse.builder()
        .uuid(institution.getUuid()).code(institution.getCode()).ispb(institution.getIspb())
        .fullName(institution.getFullName()).name(institution.getName()).build());
  }
}

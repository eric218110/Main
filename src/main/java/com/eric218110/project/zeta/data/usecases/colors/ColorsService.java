package com.eric218110.project.zeta.data.usecases.colors;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.entities.colors.ColorsEntity;
import com.eric218110.project.zeta.domain.http.color.ShowColorsResponse;
import com.eric218110.project.zeta.domain.usecases.color.ListColors;
import com.eric218110.project.zeta.domain.usecases.color.LoadColorByUuid;
import com.eric218110.project.zeta.infra.repositories.database.color.ColorRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ColorsService implements LoadColorByUuid, ListColors {
  private final ColorRepository colorRepository;

  @Override
  public ColorsEntity loadColorByUuid(String uuid) {
    var color = this.colorRepository.findById(UUID.fromString(uuid));

    if (color.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found color");
    }

    return color.get();
  }

  @Override
  public Page<ShowColorsResponse> listColors(Pageable pageable) {
    var colors = this.colorRepository.findAll(pageable);

    return colors.map(this::entityToDto);
  }

  private ShowColorsResponse entityToDto(ColorsEntity color) {
    return ShowColorsResponse.builder().uuid(color.getUuid()).name(color.getName())
        .hex(color.getHex()).description(color.getDescription()).argb(color.getArgb()).build();
  }
}

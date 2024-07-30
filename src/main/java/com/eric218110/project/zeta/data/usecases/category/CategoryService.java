package com.eric218110.project.zeta.data.usecases.category;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.entities.category.CategoryEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.http.category.AddCategoryRequest;
import com.eric218110.project.zeta.domain.http.category.ShowCategoryResponse;
import com.eric218110.project.zeta.domain.usecases.category.AddOneCategory;
import com.eric218110.project.zeta.domain.usecases.category.LoadAllCategory;
import com.eric218110.project.zeta.infra.repositories.database.category.CategoryRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService implements LoadAllCategory, AddOneCategory {
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;

  @Override
  public Page<ShowCategoryResponse> listAllByUserUuid(UUID userIUuid, Pageable pageable) {
    UserEntity userEntity = this.loadUserByUuid(userIUuid);
    Page<CategoryEntity> categories = this.categoryRepository.findByUser(userEntity, pageable);

    return categories.map(this::entityToDto);
  }

  @Override
  public ShowCategoryResponse addCategory(AddCategoryRequest addCategoryDto, UUID userUuid) {
    var categoryAlreadyExists = this.categoryRepository.findByName(addCategoryDto.getName());

    if (categoryAlreadyExists != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists");
    }

    var userEntity = this.loadUserByUuid(userUuid);
    var cardEntity =
        CategoryEntity.builder().user(userEntity).name(addCategoryDto.getName()).build();
    var categorySave = this.categoryRepository.save(cardEntity);

    return ShowCategoryResponse.builder().uuid(categorySave.getUuid().toString())
        .name(categorySave.getName()).build();
  }

  private UserEntity loadUserByUuid(UUID userUuid) {
    Optional<UserEntity> userEntity = this.userRepository.findById(userUuid);

    if (userEntity.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found user");
    }

    return userEntity.get();
  }

  private ShowCategoryResponse entityToDto(CategoryEntity categoryEntity) {
    return ShowCategoryResponse.builder().uuid(categoryEntity.getUuid().toString())
        .name(categoryEntity.getName()).build();
  }

}

package com.eric218110.project.zeta.infra.http.providers;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.eric218110.project.zeta.infra.http.response.BanksResponse;

@FeignClient(value = "brasilapi", url = "https://brasilapi.com.br/api")
public interface LoadAllBanksProvider {

  @GetMapping(value = "/banks/v1")
  List<BanksResponse> getAllBanks();
}

package com.novomind.ecom.ichat.customisation.core.demandInfo;

import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemandInfo {
  
  private String name;
  private String example;
  private boolean isRequired;
  
  public static DemandInfo of(DemandInfoCreateDTO dto) {
    return DemandInfo.builder()
        .name(dto.getName())
        .example(dto.getExample())
        .isRequired(dto.isRequired())
        .build();
  }
  
}

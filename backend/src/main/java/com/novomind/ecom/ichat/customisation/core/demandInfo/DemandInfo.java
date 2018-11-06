package com.novomind.ecom.ichat.customisation.core.demandInfo;

import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;

import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemandInfo {

  private String id;
  private List<DemandInfoItem> demandInfoItems = new ArrayList<>();

}

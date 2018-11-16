package com.novomind.ecom.ichat.customisation.core.demandInfo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DemandInfo {

  private String id;
  private List<DemandInfoItem> demandInfoItems = new ArrayList<>();

}

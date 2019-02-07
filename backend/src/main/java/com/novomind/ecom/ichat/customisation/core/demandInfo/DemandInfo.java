package com.novomind.ecom.ichat.customisation.core.demandInfo;

import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DemandInfo {

  private String id;
  private List<DemandInfoItem> demandInfoList = new ArrayList<>();

}

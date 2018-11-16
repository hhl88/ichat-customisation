package com.novomind.ecom.ichat.customisation.core.demandInfo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoItemDTO;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemandInfoItem {
    private String name;
    private String example;
    private boolean isRequired;

    public static DemandInfoItem of(DemandInfoItemDTO dto) {
        return DemandInfoItem.builder()
                .name(dto.getName())
                .example(dto.getExample())
                .isRequired(dto.isRequired())
                .build();
    }

    public String getName() {
        return name;
    }

    public String getExample() {
        return example;
    }

    @JsonProperty("isRequired")
    public boolean isRequired() {
        return isRequired;
    }
}

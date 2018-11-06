package com.novomind.ecom.ichat.customisation.core.demandInfo;


import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoCreateDTO;
import com.novomind.ecom.ichat.customisation.domain.dtos.demandinfo.DemandInfoItemDTO;
import lombok.*;

@Getter
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
}

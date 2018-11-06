package com.novomind.ecom.ichat.customisation.domain.dtos.chat.frontend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FrontEndDTO {

    private String id;
    private String name;
}

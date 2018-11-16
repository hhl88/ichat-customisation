package com.novomind.ecom.ichat.customisation.domain.dtos;


import javax.validation.constraints.NotNull;



public class IdDTO {
    @NotNull
    private String id;

    public IdDTO(@NotNull String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

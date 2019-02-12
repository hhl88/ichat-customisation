package com.novomind.ecom.ichat.customisation.domain.datatypes;


public enum LayoutDisplay {
    BESIDE_PAGE(0),
    INTEGRATED(1),
    POPUP(2);
    private final int mask;

    private LayoutDisplay(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }

}

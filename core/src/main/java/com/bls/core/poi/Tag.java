package com.bls.core.poi;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Tag {
    @NotNull
    @Size(min=1, max=30)
    String tag;

    @Override
    public String toString() {
        return tag;
    }
}

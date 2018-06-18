package com.rabo.model;

import java.util.EnumSet;
import org.apache.commons.lang.StringUtils;

public enum FileExtensionFilter {
    CSV("csv"),
    XML("xml");

    private String code;

    FileExtensionFilter(String code) {
        this.code = new String(code);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static FileExtensionFilter fromString(String status) {
        return EnumSet.allOf(FileExtensionFilter.class)
                .stream()
                .filter(value -> StringUtils.equals(value.getCode(), status))
                .findFirst()
                .orElse(null);
    }
}

package com.qugenx.storage.model.enums;

public enum StatusCode {
    ARTICLE_FOUND("501"),
    ARTICLE_NOT_FOUND("502"),
    ARTICLE_ERROR_RETREIVE("503"),
    ARTICLE_UPDATED("504"),
    ARTICLE_DELETED("505"),
    ARTICLE_ADDED("506"),

    SKILLS_FOUND("601"),
    SKILLS_NOT_FOUND("602"),
    SKILLS_ERROR_RETREIVE("603"),
    SKILLS_UPDATED("604"),
    SKILLS_DELETED("605"),
    SKILLS_ADDED("606");

    private final String label;

    private StatusCode(String label) {
        this.label = label;
    }

    public static StatusCode valueOfLabel(String label) {
        for (StatusCode e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

    public String getLabel() {
        return label;
    }
}

package com.hmc.auth.support;

public enum RoleEnum {

    USER("USER"),

    ADMIN("ADMIN"),

    ;

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

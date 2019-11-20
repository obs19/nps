package com.cci.payments.security.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

public enum AppRoleEnum {
    ROLE_AUTHOR("appAuthorRole"),
    ROLE_TESTER("appTesterRole"),
    ROLE_PUBLISHER("appPublisherRole");

    private final String appRoleKey;
    private String entRoleKey;

    AppRoleEnum(String key) {
        this.appRoleKey = key;
    }

    public static Optional<AppRoleEnum> ofAppRole(String key) {
        return Optional.ofNullable(getInstanceByAppRole(key));
    }

    public static AppRoleEnum getInstanceByAppRole(String key) {
        for (AppRoleEnum en : AppRoleEnum.values()) {
            if (en.getAppRoleKey().equalsIgnoreCase(key)) {
                return en;
            }
        }
        return null;
    }

    public String getEntRoleKey() {
        return entRoleKey;
    }

    public String getAppRoleKey() {
        return appRoleKey;
    }

//    @Component
//    public static class CommonSettingsServiceInjector {
//        @Autowired
//        private CommonSettingsService commonSettingsService;
//
//        @PostConstruct
//        public void postConstruct() {
//            Map<AppRoleEnum, String> map = commonSettingsService.getEntRolesMapping();
//            map.forEach((k, v) -> {
//                k.entRoleKey = v;
//            });
//        }
//    }
}

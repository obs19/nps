package com.cci.payments.services;

import com.cci.payments.util.UTF8Control;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CommonSettingsService {
    private static final Logger logger = LoggerFactory.getLogger(CommonSettingsService.class);
    public static final String DEF_LOCALE_KEY = "ru";
    public static final Locale DEF_LOCALE = new Locale(DEF_LOCALE_KEY);

    private final Map<Locale, Map<String, String>> resources = new ConcurrentHashMap<>();

    @Value("${app.locales}")
    private String appLocales;

    public void initResources() {
        getLocales().forEach(
                a -> {
                    Map<String, String> resource = new ConcurrentHashMap<>();
//                    ResourceBundle bundle = ResourceBundle.getBundle("translations/translation", a);
                    ResourceBundle bundle = ResourceBundle.getBundle("translations/translation" + "_" + a, new UTF8Control());
                    Enumeration<String> keys = bundle.getKeys();
                    while (keys.hasMoreElements()) {
                        String key = keys.nextElement();
                        resource.put(key, HtmlUtils.htmlEscape((String) bundle.getObject(key)));
                    }
                    this.resources.put(a, resource);
                }
        );
    }

    public Set<Locale> getLocales() {
        return getLocalesKeys().stream().map(a -> new Locale(a)).collect(Collectors.toSet());
    }

    public Set<String> getLocalesKeys() {
        Set<String> locales = null;
        String localesStr = appLocales;
        if (localesStr != null) {
            locales = Arrays.stream(localesStr.split(","))
                    .filter(a -> a != null && !a.trim().isEmpty())
                    .map(a -> a.trim().toLowerCase()).collect(Collectors.toSet());
        }
        if (locales == null) {
            locales = new HashSet<>();
        }
        if (locales.isEmpty()) {
            locales.add(DEF_LOCALE_KEY);
        }
        return locales;
    }

    public Map<String, String> getTranslations(String localeStr) {
        if (localeStr == null || localeStr.isEmpty()) {
            localeStr = DEF_LOCALE_KEY;
        }
        return getTranslations(new Locale(localeStr));
    }

    public Map<String, String> getTranslations(Locale locale) {
        if (locale == null) {
            locale = DEF_LOCALE;
        }
        if (resources.isEmpty()) {
            initResources();
        }
        Map<String, String> map = resources.get(locale);
        if (map == null) {
            map = resources.get(DEF_LOCALE);
        }
        return map;
    }
}

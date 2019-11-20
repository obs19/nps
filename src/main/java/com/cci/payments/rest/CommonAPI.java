package com.cci.payments.rest;

import com.cci.payments.services.CommonSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/common")
public class CommonAPI {

    @Autowired
    private CommonSettingsService commonSettingsService;

    private final Map<Locale, String> translations = new ConcurrentHashMap<>();

    @Transactional
    @RequestMapping(value = "/translations.js", method = RequestMethod.GET, produces = "application/javascript")
    public @ResponseBody
    String getTranslations(HttpServletResponse response, Locale locale) {
        locale = locale == null ? CommonSettingsService.DEF_LOCALE : new Locale(locale.getLanguage());
        String js = translations.get(locale);
        if (js == null) {
            js = transformTranslationToJs(commonSettingsService.getTranslations(locale));
            translations.put(locale, js);
        }
        return js;
    }

    private String transformTranslationToJs(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("Translation = function () {};");
        map.forEach((k, v) -> sb.append("Translation.").append(k).append("='").append(v).append("';"));
        return sb.toString();
    }
}

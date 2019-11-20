package com.cci.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
public class AppMessageService {
    private final MessageSource messageSource;

    @Autowired
    public AppMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Object... params){
//        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
        return adaptString(messageSource.getMessage(key, params, LocaleContextHolder.getLocale()));
    }


    private String adaptString(String str){
        return str==null?null: HtmlUtils.htmlEscape(str);
    }
}

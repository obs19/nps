package com.cci.payments.util.converter;

import com.cci.payments.model.Dictionary;
import com.cci.payments.services.DictionaryService;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StringToDictionaryConverter implements CustomConverter {

    private final DictionaryService dictionaryService;

    @Autowired
    public StringToDictionaryConverter(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public Object convert(Object dest, Object src, Class<?> destinationClass, Class<?> sourceClass) {
        if (src == null) {
            return null;
        }
        if (src instanceof java.lang.String){
            String dictId = (String) src;
            if (dictId.trim().isEmpty()){
                return null;
            }
            return dictionaryService.findById(Long.valueOf(dictId));
        }
        throw new MappingException("StringToDictionaryConverter exception during mapping src = " + src);
    }
}

package com.cci.payments.services;

import com.cci.payments.dto.DictDTO;
import com.cci.payments.dto.PaymentDTO;
import com.cci.payments.dto.PaymentLastHistoryDTO;
import com.cci.payments.model.Dictionary;
import com.cci.payments.model.Payment;
import com.cci.payments.model.PaymentStatus;
import com.cci.payments.repository.DictionaryRepository;
import com.cci.payments.repository.PaymentRepository;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Service
public class DictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private Mapper beanMapper;


    public DictDTO findDictDTOById(Long id){
        Dictionary dictionary = dictionaryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Dictionary with id =" + String.valueOf(id) + ", does not exist"));
        return beanMapper.map(dictionary, DictDTO.class);
    }

    public Dictionary findById(Long id){
        return dictionaryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Dictionary with id =" + String.valueOf(id) + ", does not exist"));
    }

    public List<DictDTO> findByType(String dictType) {
        List<Dictionary> dictList = dictionaryRepository.findDictionaryByType(dictType);
        List<DictDTO> dtoList = new ArrayList<>();
        dictList.forEach(d -> {
            DictDTO dto = beanMapper.map(d, DictDTO.class);
            dtoList.add(dto);
        });
        return dtoList;
    }

}

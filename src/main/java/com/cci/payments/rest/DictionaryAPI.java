package com.cci.payments.rest;

import com.cci.payments.model.dictionaries.DictTypeEnum;
import com.cci.payments.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/dictionary")
public class DictionaryAPI {

    @Autowired
    private DictionaryService dictionaryService;


//    @GetMapping(value = "/get")
//    public ResponseEntity<?> getDictById(@RequestParam(name = "id") Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(dictionaryService.findById(id));
//    }


    @GetMapping
    public ResponseEntity<?> getByType(@RequestParam(name = "dictType") String dictType) {
        System.out.println("<---dictType = " + dictType);
        return ResponseEntity.status(HttpStatus.OK)
                .body(dictionaryService.findByType(DictTypeEnum.getInstanceByName(dictType).getType()));
    }



















//    ******************************* DELETE TEST Method ********************************************************

//    @GetMapping(value = "/getFirstDict")
//    public ResponseEntity<List<DummyDictionaryDTO>> getFirstDict() {
//        ArrayList<DummyDictionaryDTO> dictList = new ArrayList<>();
//        dictList.add(createDictDTO("1", "1.1", "1.1-Administrativniy-Sbor"));
//        dictList.add(createDictDTO("2", "1.2", "1.2-Plateji z reestru"));
//        dictList.add(createDictDTO("3", "1.3", "1.3-Podatkovi plateji"));
//        return ResponseEntity.status(HttpStatus.OK).body(dictList);
//    }
//
//    @GetMapping(value = "/getSecondDict")
//    public ResponseEntity<List<DummyDictionaryDTO>> getSecondDict(@RequestParam(name = "typeOfDict") String type) {
//        ArrayList<DummyDictionaryDTO> dictList = new ArrayList<>();
//        if(type.equalsIgnoreCase("1")) {
//            dictList.add(createDictDTO("1", "1.1", "1.1-PodatkoviyPlatij-1"));
//            dictList.add(createDictDTO("2", "1.2", "1.2-PodatkoviyPlatij-2"));
//            dictList.add(createDictDTO("3", "1.3", "1.3-PodatkoviyPlatij-3"));
//        }
//        if (type.equalsIgnoreCase("2")) {
//            dictList.add(createDictDTO("1", "2.1", "2.1-DejavniyReestr-1"));
//            dictList.add(createDictDTO("2", "2.2", "2.2-DejavniyReestr-2"));
//            dictList.add(createDictDTO("3", "2.3", "2.3-DejavniyReestr-3"));
//        }
//        if(type.equalsIgnoreCase("3")) {
//            dictList.add(createDictDTO("1", "3.1", "3.1-PodatkoviyPlatij-1"));
//            dictList.add(createDictDTO("2", "3.2", "3.2-PodatkoviyPlatij-2"));
//            dictList.add(createDictDTO("3", "3.3", "3.3-PodatkoviyPlatij-3"));
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(dictList);
//    }

//    private DummyDictionaryDTO createDictDTO(String id, String extId, String name){
//        DummyDictionaryDTO dto = new DummyDictionaryDTO();
//        dto.setId(id);
//        dto.setExtId(extId);
//        dto.setName(name);
//        return dto;
//    }

}

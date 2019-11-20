package com.cci.payments.repository;

import com.cci.payments.model.Dictionary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

    Optional<Dictionary> findById(Long id);

//    Optional<Dictionary> findByExtId(Long extId);

//    List<Dictionary> findAllByExtId(String extId);


    @Query(value = "SELECT * FROM dictionary d WHERE d.discriminator = ?1", nativeQuery = true)
    List<Dictionary> findDictionaryByType(String dictType);

    @Query(value = "SELECT * FROM dictionary d WHERE d.discriminator = ?1 AND d.ext_id = ?2", nativeQuery = true)
    Dictionary findDictionaryByTypeAndExtId(String dictType, String extId);
}

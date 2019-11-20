package com.cci.payments.model.dictionaries;

import com.cci.payments.model.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue(Dictionary.DICT_COUNTRY_REGION)
@DiscriminatorValue(DictTypeEnum.DICT_COUNTRY_REGION)
public class DictCountryRegion extends Dictionary {
}

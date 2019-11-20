package com.cci.payments.model.dictionaries;

import com.cci.payments.model.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(DictTypeEnum.DICT_TYPE_OF_PAYMENT)
public class DictTypeOfPayment extends Dictionary {
}

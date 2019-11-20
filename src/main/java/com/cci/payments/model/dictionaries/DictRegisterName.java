package com.cci.payments.model.dictionaries;

import com.cci.payments.model.Dictionary;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(DictTypeEnum.DICT_REGISTER_NAME)
public class DictRegisterName extends Dictionary{
}

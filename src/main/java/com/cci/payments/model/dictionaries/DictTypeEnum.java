package com.cci.payments.model.dictionaries;

public enum DictTypeEnum {
    DictTypeOfPayment(DictTypeEnum.DICT_TYPE_OF_PAYMENT, "DictTypeOfPayment"),
    DictRegisterName(DictTypeEnum.DICT_REGISTER_NAME, "DictRegisterName"),
    DictPaymentAction(DictTypeEnum.DICT_PAYMENT_ACTION, "DictPaymentAction"),
    DictCountryRegion(DictTypeEnum.DICT_COUNTRY_REGION, "DictCountryRegion");

    private final String type;
    private final String name;

    DictTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static DictTypeEnum getInstanceByName(String dictType){
        for (DictTypeEnum dictTypeEnum : DictTypeEnum.values()) {
            if (dictTypeEnum.getName().equalsIgnoreCase(dictType)) {
                return dictTypeEnum;
            }
        }
        throw new IllegalArgumentException("Dict with type =" + dictType + ", doesn't exist");
    }

    public final static String DICT_TYPE_OF_PAYMENT = "1";
    public final static String DICT_REGISTER_NAME = "2";
    public final static String DICT_PAYMENT_ACTION = "3";
    public final static String DICT_COUNTRY_REGION = "4";

}

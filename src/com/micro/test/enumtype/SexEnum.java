package com.micro.test.enumtype;

/**
 * Created by mycge on 2018/5/25.
 */
public enum SexEnum {
    MAN(1.0, "男"), WOMAN(2.0, "女");
    private Double code;
    private String value;

    public static String getValue(Double code){
        for(SexEnum sex : values()){
            if(sex.getCode().equals(code)){
                return sex.value;
            }
        }
        return null;
    }

    public static Double getCode(String value){
        for(SexEnum sex : values()){
            if(sex.getValue().equals(value)){
                return sex.code;
            }
        }
        return null;
    }


    SexEnum(Double code, String value) {
        this.code = code;
        this.value = value;
    }

    public Double getCode() {
        return code;
    }

    public void setCode(Double code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package com.example.plantapp.commons.data.enums;

public enum PeriodEnum {
    MORNING("Buổi sáng"),
    AFTERNOON("Buổi sáng"),
    EVENING("Buổi tối");
    private String period;

    PeriodEnum(String period){
         this.period = period;
    }
    public String getPeriod(){
        return period;
    }
}

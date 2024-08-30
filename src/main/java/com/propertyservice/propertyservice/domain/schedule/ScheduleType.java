package com.propertyservice.propertyservice.domain.schedule;

import lombok.Getter;


@Getter
public enum ScheduleType {
    BALANCE("잔금"),
    MEETING("미팅"),
    LEAVE("휴가"),
    MOVE_IN("입주"),
    MOVE_OUT("퇴주");

    private String label;
    ScheduleType(String label) {
        this.label = label;
    }
}
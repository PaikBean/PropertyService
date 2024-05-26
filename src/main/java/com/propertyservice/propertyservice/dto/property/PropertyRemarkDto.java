package com.propertyservice.propertyservice.dto.property;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class PropertyRemarkDto {
    private Long propertyRemarkId;
    private String propertyRemark;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public PropertyRemarkDto(Long propertyRemarkId, String propertyRemark, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.propertyRemarkId = propertyRemarkId;
        this.propertyRemark = propertyRemark;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

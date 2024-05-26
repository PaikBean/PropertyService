package com.propertyservice.propertyservice.repository.property;

import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateCondition;
import com.propertyservice.propertyservice.dto.client.ShowingPropertyCandidateDto;

import java.util.List;

public interface PropertyRepositoryCustom {
    List<ShowingPropertyCandidateDto> searchShowingPropertyCandidateList(ShowingPropertyCandidateCondition showingPropertyCandidateCondition);
}

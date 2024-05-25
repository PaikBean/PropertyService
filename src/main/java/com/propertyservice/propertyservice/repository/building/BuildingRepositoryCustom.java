package com.propertyservice.propertyservice.repository.building;

import com.propertyservice.propertyservice.dto.building.BuildingCondition;
import com.propertyservice.propertyservice.dto.building.BuildingDto;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingDto> searchBuildingList(BuildingCondition buildingCondition);
}

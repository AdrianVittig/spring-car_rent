package com.vitig.car_rent.service.contract;

import com.vitig.car_rent.data.dto.model_dto.ModelCreateDto;
import com.vitig.car_rent.data.dto.model_dto.ModelFetchDto;
import com.vitig.car_rent.data.dto.model_dto.ModelUpdateDto;

import java.util.List;

public interface ModelService {
    List<ModelFetchDto> getAllModels();
    ModelFetchDto getModelById(Long id);
    ModelFetchDto createModel(ModelCreateDto modelCreateDto);
    ModelFetchDto updateModel(Long id, ModelUpdateDto modelUpdateDto);
    void deleteModel(Long id);
}

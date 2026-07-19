package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.config.util.ModelMapperUtil;
import com.vitig.car_rent.data.dto.model_dto.ModelCreateDto;
import com.vitig.car_rent.data.dto.model_dto.ModelFetchDto;
import com.vitig.car_rent.data.dto.model_dto.ModelUpdateDto;
import com.vitig.car_rent.data.entity.Model;
import com.vitig.car_rent.data.exception.ObjectNotFoundException;
import com.vitig.car_rent.data.repository.ModelRepository;
import com.vitig.car_rent.service.contract.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    public List<ModelFetchDto> getAllModels() {
        return this.modelRepository.findAll().stream()
                .map(model -> modelMapperUtil.map(model, ModelFetchDto.class))
                .toList();
    }

    @Override
    public ModelFetchDto getModelById(Long id) {
        return modelMapperUtil.map(this.modelRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Model not found with id: " + id + "!")
        ), ModelFetchDto.class);
    }

    @Override
    public ModelFetchDto createModel(ModelCreateDto modelCreateDto) {
        Model model = modelMapperUtil.map(modelCreateDto, Model.class);
        return modelMapperUtil.map(this.modelRepository.save(model), ModelFetchDto.class);
    }

    @Override
    public ModelFetchDto updateModel(Long id, ModelUpdateDto modelUpdateDto) {
        Model model = this.modelRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Model not found with id: " + id + "!")
        );
        model.setModel(modelUpdateDto.getModel());
        model.setColor(modelUpdateDto.getColor());
        model.setMinimalAge(modelUpdateDto.getMinimalAge());
        return modelMapperUtil.map(this.modelRepository.save(model), ModelFetchDto.class);
    }

    @Override
    public void deleteModel(Long id) {
        Model model = this.modelRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Model not found with id: " + id + "!")
        );
        this.modelRepository.delete(model);
    }
}

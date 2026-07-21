package com.vitig.car_rent.service.impl;

import com.vitig.car_rent.config.util.ModelMapperUtil;
import com.vitig.car_rent.data.dto.model_dto.ModelCreateDto;
import com.vitig.car_rent.data.dto.model_dto.ModelFetchDto;
import com.vitig.car_rent.data.dto.model_dto.ModelUpdateDto;
import com.vitig.car_rent.data.entity.Model;
import com.vitig.car_rent.data.exception.ObjectNotFoundException;
import com.vitig.car_rent.data.repository.ModelRepository;
import com.vitig.car_rent.data.util.CarType;
import com.vitig.car_rent.service.contract.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Transactional
    public ModelFetchDto createModel(ModelCreateDto modelCreateDto) {
        Model model = modelMapperUtil.map(modelCreateDto, Model.class);
        BigDecimal basePrice = BigDecimal.valueOf(30);
        if(modelCreateDto.getBrandId().equals(1L)
                || modelCreateDto.getBrandId().equals(2L)
                || modelCreateDto.getBrandId().equals(3L)){
            basePrice = basePrice.multiply(BigDecimal.valueOf(1.30));
        }
        if(modelCreateDto.getYear() >= 2025){
            basePrice = basePrice.multiply(BigDecimal.valueOf(1.20));
        }
        else if(modelCreateDto.getYear() >= 2021){
            basePrice = basePrice.multiply(BigDecimal.valueOf(1.15));
        }
        else if (modelCreateDto.getYear() >= 2017){
            basePrice = basePrice.multiply(BigDecimal.valueOf(1.05));
        }
        if(modelCreateDto.getType().equals(CarType.SUV)){
            basePrice = basePrice.multiply(BigDecimal.valueOf(1.05));
        }
        model.setPricePerDay(basePrice);
        return modelMapperUtil.map(this.modelRepository.save(model), ModelFetchDto.class);
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteModel(Long id) {
        Model model = this.modelRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Model not found with id: " + id + "!")
        );
        this.modelRepository.delete(model);
    }
}

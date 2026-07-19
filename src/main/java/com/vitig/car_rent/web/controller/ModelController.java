package com.vitig.car_rent.web.controller;

import com.vitig.car_rent.data.dto.model_dto.ModelCreateDto;
import com.vitig.car_rent.data.dto.model_dto.ModelFetchDto;
import com.vitig.car_rent.data.dto.model_dto.ModelUpdateDto;
import com.vitig.car_rent.service.contract.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @GetMapping
    public List<ModelFetchDto> getAllModels() {
        return modelService.getAllModels();
    }

    @GetMapping("/{id}")
    public ModelFetchDto getModelById(@PathVariable Long id) {
        return modelService.getModelById(id);
    }

    @PostMapping
    public ModelFetchDto createModel(@Valid @RequestBody ModelCreateDto modelCreateDto) {
        return modelService.createModel(modelCreateDto);
    }

    @PutMapping("/{id}")
    public ModelFetchDto updateModel(@PathVariable Long id, @Valid @RequestBody ModelUpdateDto modelUpdateDto) {
        return modelService.updateModel(id, modelUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
    }
}

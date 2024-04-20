package com.example.plantapp.rest.controller.plantType;

import com.example.plantapp.commons.data.request.plantType.PlantTypeRequest;
import com.example.plantapp.service.plantType.PlantTypeAddNewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/plant-type")
@Tag(name = "plant-type",description = "quản lý loại cây trồng")
public class PlantTypeController {
    @Autowired
    private PlantTypeAddNewService plantTypeService;
    @PostMapping(value = "/addNew", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Thêm mới một loại cây trồng")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> addNew(@RequestBody @Valid PlantTypeRequest plantTypeRequest){
        if(ObjectUtils.isEmpty(plantTypeRequest)){
            return ResponseEntity.badRequest().build();
        }
        return plantTypeService.addPlantType(plantTypeRequest);
    }
}

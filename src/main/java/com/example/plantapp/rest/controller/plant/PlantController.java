package com.example.plantapp.rest.controller.plant;

import com.example.plantapp.commons.data.request.plant.PlantDrawChartRequest;
import com.example.plantapp.commons.data.request.plant.PlantRequest;
import com.example.plantapp.service.plant.PlantAddNewService;
import com.example.plantapp.service.plant.PlantDrawChartService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/plant")
@Tag(name = "plant",description = "quản lý cây trồng")
public class PlantController {

    @Autowired
    private PlantAddNewService plantService;
    @PostMapping(value = "/addNew", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Thêm mới một cây trồng")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> addNew(@RequestBody @Valid PlantRequest plantRequest){
        if(ObjectUtils.isEmpty(plantRequest)){
            return ResponseEntity.badRequest().build();
        }
        return plantService.addPlant(plantRequest);
    }


    @Autowired
    private PlantDrawChartService plantDrawChartService;
    @PostMapping(value = "/draw-chart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "vẽ biểu đồ cho một cây trồng")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> addNew(@PathVariable("id") Integer id, @RequestBody PlantDrawChartRequest request){
        if(ObjectUtils.isEmpty(request)){
            return ResponseEntity.badRequest().build();
        }
        return plantDrawChartService.drawChart(id, request);
    }

}

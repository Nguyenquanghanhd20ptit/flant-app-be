package com.example.plantapp.rest.controller.scheduler;

import com.example.plantapp.commons.data.request.scheduler.SchedulerRequest;
import com.example.plantapp.service.scheduler.*;
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

@RestController
@RequestMapping("/scheduler")
@Tag(name = "scheduler", description = "Quản lý lịch trình")
public class SchedulerController {
    @Autowired
    private SchedulerAddNewService schedulerService;
    @PostMapping(value = "/addNew", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Thêm mới một lịch trình")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> addNew(@RequestBody SchedulerRequest request){
        if(ObjectUtils.isEmpty(request)){
            return ResponseEntity.badRequest().build();
        }
        return schedulerService.addNew(request);
    }

    @Autowired
    private SchedulerAllService schedulerAllService;
    @GetMapping(value = "/all")
    @Operation(summary = "lấy danh sách tất cả lịch trình")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> getAllScheduler(@RequestParam(name = "userId") Integer userId){
        return schedulerAllService.getAllScheduler(userId);
    }

    @Autowired
    private SchedulerDetailService schedulerDetailService;
    @GetMapping(value = "/detail/{id}")
    @Operation(summary = "lấy chi tiết lịch trình")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> getDetail(@PathVariable("id") Integer id){
        return schedulerDetailService.getById(id);
    }

    @Autowired
    private SchedulerPlantUserService schedulerPlantUserService;
    @PostMapping(value = "/plant-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "lấy chi tiết lịch trình theo use and plant")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> getDetail(@RequestBody SchedulerRequest request){
       if(ObjectUtils.isEmpty(request)){
           return ResponseEntity.badRequest().build();
       }
       return schedulerPlantUserService.findByPlantAndUser(request.getPlantId(),request.getCreatedBy());
    }
    @Autowired
    private SchedulerDeleteService schedulerDeleteService;
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "xóa một lịch trình")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        return schedulerDeleteService.deleteById(id);
    }
}

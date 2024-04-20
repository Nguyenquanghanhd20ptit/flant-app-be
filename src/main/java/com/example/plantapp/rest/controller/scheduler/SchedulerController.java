package com.example.plantapp.rest.controller.scheduler;

import com.example.plantapp.commons.data.request.scheduler.SchedulerRequest;
import com.example.plantapp.service.scheduler.SchedulerAllService;
import com.example.plantapp.service.scheduler.SchedulerAddNewService;
import com.example.plantapp.service.scheduler.SchedulerDeleteService;
import com.example.plantapp.service.scheduler.SchedulerDetailService;
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
    @PostMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @PostMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "lấy chi tiết lịch trình")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> getDetail(@PathVariable("id") Integer id){
        return schedulerDetailService.getById(id);
    }

    @Autowired
    private SchedulerDeleteService schedulerDeleteService;
    @PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "xóa một lịch trình")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        return schedulerDeleteService.deleteById(id);
    }
}

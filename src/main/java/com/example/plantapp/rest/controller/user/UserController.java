package com.example.plantapp.rest.controller.user;

import com.example.plantapp.commons.data.response.user.MyInfoResponse;
import com.example.plantapp.service.user.MyInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MyInfoService myInfoService;
    @GetMapping(value = "/me/{id}")
    @Operation(summary = "Api lấy thông tin người dùng")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MyInfoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    public ResponseEntity<String> myInfo(@PathVariable("id") Integer id){
        if(ObjectUtils.isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }
        return myInfoService.getMyInfo(id);
    }
}

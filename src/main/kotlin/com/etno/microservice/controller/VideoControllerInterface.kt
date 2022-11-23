package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.ImageDTO
import com.etno.microservice.model.dto.VideoDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
interface VideoControllerInterface {

    @ApiOperation(
        value = "get a video list",
        nickname = "getVideos",
        notes = "this endpoint is to get video list",
        tags = ["Video"],
        response = VideoDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Video", response = VideoDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/videos"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getVideos(): ResponseEntity<List<VideoDTO>>?

    @ApiOperation(
        value = "save a video",
        nickname = "saveVideo",
        notes = "this endpoint is to save video",
        tags = ["Video"],
        response = VideoDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Video", response = VideoDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/videos"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveVideo(@RequestBody videoDTO: VideoDTO): ResponseEntity<VideoDTO>?
}
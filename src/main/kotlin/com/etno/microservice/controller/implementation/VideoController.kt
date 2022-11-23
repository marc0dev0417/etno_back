package com.etno.microservice.controller.implementation

import com.etno.microservice.controller.VideoControllerInterface
import com.etno.microservice.model.dto.VideoDTO
import com.etno.microservice.service.implementation.VideoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class VideoController(
    private val videoService: VideoService
): VideoControllerInterface {
    override fun getVideos(): ResponseEntity<List<VideoDTO>>? {
        return ResponseEntity.ok().body(videoService.getVideos())
    }

    override fun saveVideo(videoDTO: VideoDTO): ResponseEntity<VideoDTO>? {
        return ResponseEntity.ok().body(videoService.saveVideo(videoDTO))
    }
}
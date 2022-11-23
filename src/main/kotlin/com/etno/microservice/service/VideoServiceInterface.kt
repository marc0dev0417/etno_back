package com.etno.microservice.service

import com.etno.microservice.model.dto.VideoDTO
import org.springframework.stereotype.Service

@Service
interface VideoServiceInterface {
    fun saveVideo(videoDTO: VideoDTO): VideoDTO?
    fun getVideos(): List<VideoDTO>?
}
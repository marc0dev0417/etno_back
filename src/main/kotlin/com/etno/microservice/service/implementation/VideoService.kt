package com.etno.microservice.service.implementation

import com.etno.microservice.model.dto.VideoDTO
import com.etno.microservice.repository.VideoRepository
import com.etno.microservice.service.VideoServiceInterface
import com.etno.microservice.util.DataConverter
import org.springframework.stereotype.Service
import java.util.*

@Service
class VideoService(
    private val videoRepository: VideoRepository
): VideoServiceInterface {
    override fun saveVideo(videoDTO: VideoDTO): VideoDTO? {
        val itemVideo = DataConverter.videoFromDTO(VideoDTO())
        itemVideo.idVideo = UUID.randomUUID()
        val itemSaved = videoRepository.save(itemVideo)

        return DataConverter.videoToDTO(itemSaved)
    }

    override fun getVideos(): List<VideoDTO>? {
        return videoRepository.findAll().map { DataConverter.videoToDTO(it) }
    }
}
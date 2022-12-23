package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.EventDTO
import com.etno.microservice.model.dto.PharmacyDTO
import com.etno.microservice.model.dto.UserDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.apache.http.protocol.ResponseServer
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
interface UserControllerInterface {
    @ApiOperation(
        value = "Get all users",
        nickname = "getUsers",
        notes = "This endpoint you will see all users",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getUsers(): ResponseEntity<List<UserDTO>>?

    @ApiOperation(
        value = "Save a user",
        nickname = "saveUser",
        notes = "this endpoint is to save a user",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/register"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun saveUser(@RequestBody userDTO: UserDTO): ResponseEntity<UserDTO>?

    @ApiOperation(
        value = "Login a user",
        nickname = "login",
        notes = "Login a user to get a token",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/login"],
        produces = ["application/json"],
        method = [RequestMethod.GET],
        params = ["username", "password"]
    )
    fun login(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "password", required = true) password: String):ResponseEntity<*>?

    @ApiOperation(
        value = "add a event to User",
        nickname = "addEventToUser",
        notes = "Add event to User",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/update_credentials"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["name", "username", "password"]
    )
    fun updateUser(@RequestParam(name = "name", required = true) name: String, @RequestParam(name = "username", required = true) username: String, @RequestParam(name = "password", required = true) password: String) : ResponseEntity<UserDTO>?

    @ApiOperation(
        value = "add Event in User",
        nickname = "addEventInUser",
        notes = "You can add new event",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/add/event"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username"]
    )
    fun addEventInUser(@RequestParam(name = "username", required = true) username: String, @RequestBody eventDTO: EventDTO): ResponseEntity<UserDTO>?

    @ApiOperation(
        value = "Add image to Event in User",
        nickname = "addImageToEventInUser",
        notes = "You can add image to event in user",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/image/event"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "title", "image"]
    )
    fun addImageToEventInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image", required = true) image: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Delete a event in User",
        nickname = "deleteEventInUser",
        notes = "Will delete a event in user",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/delete/event"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title"]
    )
    fun deleteEventInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Delete image of event in user",
        nickname = "deleteImageToEventInUser",
        notes = "Will delete image of event in user",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/image/delete/event"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title", "image"]
    )
    fun deleteImageToEventInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam("title", required = true) title: String, @RequestParam("image") imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add pharmacy in User",
        nickname = "addPharmacyInUser",
        notes = "You can add new Pharmacy",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/add/pharmacy"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username"]
    )
    fun addPharmacyInUser(@RequestParam(name = "username", required = true) username: String, @RequestBody pharmacyDTO: PharmacyDTO): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Delete a pharmacy in User",
        nickname = "deletePharmacyInUser",
        notes = "Will delete a pharmacy in user",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/delete/pharmacy"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "name"]
    )
    fun deletePharmacyInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "name", required = true) name: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Add image to Pharmacy in User",
        nickname = "addImageToPharmacyInUser",
        notes = "You can add image to pharmacy in user",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/image/pharmacy"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "name", "image"]
    )
    fun addImageToPharmacyInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "name", required = true) name: String, @RequestParam(name = "image") imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Delete image of event in user",
        nickname = "deleteImageToEventInUser",
        notes = "Will delete image of event in user",
        tags = ["User"],
        response = UserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = UserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/image/delete/pharmacy"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "name", "image"]
    )
    fun deleteImageToPharmacyInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "name", required = true) name: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>
}
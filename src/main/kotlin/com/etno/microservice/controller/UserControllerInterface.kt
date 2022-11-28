package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.UserDTO
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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
        value = ["/users/update"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username"]
    )
    fun updateUser(@RequestParam(name = "username", required = true) username: String, @RequestBody userDTO: UserDTO) : ResponseEntity<UserDTO>?
}
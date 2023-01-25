package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import io.swagger.models.Response
import org.apache.http.protocol.ResponseServer
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

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
        value = "find user by username",
        nickname = "findUserByUsername",
        notes = "find a user by his username to show to villagers",
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
        value = ["/users/villagers"],
        produces = ["application/json"],
        method = [RequestMethod.GET],
        params = ["username"]
    )
    fun findUserByUsernameToVillager(@RequestParam(name = "username", required = true) username: String): ResponseEntity<UserVillagerDTO>

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
        value = "Add subscription to User",
        nickname = "AddSubscriptionToUser",
        notes = "Gonna add a subscription in user",
        tags = ["User"],
        response = SubscriptionUserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = SubscriptionUserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/add/event/subscription"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username", "title"]
    )
    fun addSubscriptionToUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "title", required = true) title: String,
        @RequestBody subscriptionUserDTO: SubscriptionUserDTO
    ): ResponseEntity<SubscriptionUserDTO>

    @ApiOperation(
        value = "Drop out subscription in User",
        nickname = "dropOutSubscription",
        notes = "Gonna drop out a subscription",
        tags = ["User"],
        response = SubscriptionUserDTO::class
    )
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "User", response = SubscriptionUserDTO::class),
            ApiResponse(code = 400, message = "Bad Request", response = HandleResponse::class),
            ApiResponse(code = 401, message = "Unauthorized", response = HandleResponse::class),
            ApiResponse(code = 403, message = "Forbidden", response = HandleResponse::class),
            ApiResponse(code = 500, message = "Server error", response = HandleResponse::class)
        ]
    )
    @RequestMapping(
        value = ["/users/dropout/event/subscription"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "title"]
    )
    fun dropOutSubscription(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "title", required = true) title: String,
        @RequestBody subscriptionUserDTO: SubscriptionUserDTO
    ): ResponseEntity<SubscriptionUserDTO>

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
        value = "Delete image of pharmacy in user",
        nickname = "deleteImageToPharmacyInUser",
        notes = "Will delete image of pharmacy in user",
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

    @ApiOperation(
        value = "add Tourism in User",
        nickname = "addTourismInUser",
        notes = "You can add new tourism",
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
        value = ["/users/add/tourism"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username"]
    )
    fun addTourismInUser(@RequestParam(name = "username", required = true) username: String, @RequestBody tourismDTO: TourismDTO): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add image of tourism in user",
        nickname = "addImageToTourismInUser",
        notes = "Will add image of tourism in user",
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
        value = ["/users/image/tourism"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "title", "image"]
    )
    fun addImageToTourismInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Delete tourism in user",
        nickname = "deleteTourismInUser",
        notes = "Will delete tourism in user",
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
        value = ["/users/delete/tourism"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title"]
    )
    fun deleteTourismInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Delete image of tourism in user",
        nickname = "deleteImageInUser",
        notes = "Will delete image of tourism in user",
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
        value = ["users/image/delete/tourism"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title", "image"]
    )
    fun deleteImageToTourismInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add death in User",
        nickname = "addDeathInUser",
        notes = "You can add new Death",
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
        value = ["/users/add/death"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username"]
    )
    fun addDeathInUser(@RequestParam(name = "username", required = true) username: String, @RequestBody deathDTO: DeathDTO): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete death in User",
        nickname = "deleteDeathInUser",
        notes = "Will delete a death in User",
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
        value = ["/users/delete/death"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "name"]
    )
    fun deleteDeathInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "name", required = true) name: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add image to death in User",
        nickname = "addImageToDeathInUser",
        notes = "Will add an image to Death in User",
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
        value = ["/users/image/death"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "name", "image"]
    )
    fun addImageToDeathInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "name", required = true) name: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete image to death in User",
        nickname = "deleteImageToDeathInUser",
        notes = "Will delete an image to Death in User",
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
        value = ["/users/image/delete/death"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "name", "image"]
    )
    fun deleteImageToDeathInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "name", required = true) name: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add phone in User",
        nickname = "addPhoneInUser",
        notes = "Will add a phone in user",
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
        value = ["/users/add/phone"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username"]
    )
    fun addPhoneInUser(@RequestParam(name = "username", required = true) username: String, @RequestBody phoneDTO: PhoneDTO): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete phone in user",
        nickname = "deletePhoneInUser",
        notes = "Will delete a phone in user",
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
        value = ["/users/delete/phone"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "owner"]
    )
    fun deletePhoneInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "owner", required = true) owner: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add image to phone in user",
        nickname = "addImageToPhoneInUser",
        notes = "Will add an image to phone in user",
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
        value = ["/users/image/phone"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "owner", "image"]
    )
    fun addImageToPhoneInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "owner", required = true) owner: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete image to phone in user",
        nickname = "deleteImageToPhoneInUser",
        notes = "Will delete an image to phone in User",
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
        value = ["/users/image/delete/phone"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "owner", "image"]
    )
    fun deleteImageToPhoneInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam("owner", required = true) owner: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add new in User",
        nickname = "addNewInUser",
        notes = "You can add new New",
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
        value = ["/users/add/new"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username"]
    )
    fun addNewInUser(@RequestParam(name = "username", required = true) username: String, @RequestBody newDTO: NewDTO): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete new in User",
        nickname = "deleteNewInUser",
        notes = "delete new in User",
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
        value = ["/users/delete/new"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title"]
    )
    fun deleteNewInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add image to new in User",
        nickname = "addImageToNewInUser",
        notes = "You can add an image to new in User",
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
        value = ["/users/image/new"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "title", "image"]
    )
    fun addImageToNewInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image") imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete image of new in User",
        nickname = "deleteImageToNewInUser",
        notes = "You can delete image to new in User",
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
        value = ["/users/image/delete/new"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title", "image"]
    )
    fun deleteImageToNewInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "title", required = true) title: String,
        @RequestParam(name = "image", required = true) imageName: String
    ): ResponseEntity<UserDTO>

    // -> --------------------------------------------------------------------------------------------------------------
    @ApiOperation(
        value = "add incident in User",
        nickname = "addIncidentInUser",
        notes = "You gonna add incident in User",
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
        value = ["/users/add/incident"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username"],
        method = [RequestMethod.POST]
    )
    fun addIncidentInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestBody incidentDTO: IncidentDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add bando in User",
        nickname = "addBandoInUser",
        notes = "Gonna add bando in User",
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
        value = ["/users/add/bando"],
        consumes = ["application/json"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.POST]
    )
    fun addBandoInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestBody bandoDTO: BandoDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add link in User",
        nickname = "addLinkInUser",
        notes = "Gonna add link in User",
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
        value = ["/users/add/link"],
        consumes = ["application/json"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.POST]
    )
    fun addLinkInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestBody linkDTO: LinkDTO
    ): ResponseEntity<UserDTO>


    @ApiOperation(
        value = "add sponsor in User",
        nickname = "addSponsorInUser",
        notes = "Gonna add sponsor in User",
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
        value = ["/users/add/sponsor"],
        consumes = ["application/json"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.POST]
    )
    fun addSponsorInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestBody sponsorDTO: SponsorDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add add in User",
        nickname = "addAdInUser",
        notes = "Gonna add ad in User",
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
        value = ["/users/add/ad"],
        consumes = ["application/json"],
        produces = ["application/json"],
        params = ["username"],
        method = [RequestMethod.POST]
    )
    fun addAdInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestBody adDTO: AdDTO
    ): ResponseEntity<UserDTO>
}
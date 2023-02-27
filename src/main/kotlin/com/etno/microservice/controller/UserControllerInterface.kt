package com.etno.microservice.controller

import com.etno.microservice.exception.HandleResponse
import com.etno.microservice.model.dto.*
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

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
        value = "Update a event in user",
        nickname = "updateEventInUser",
        notes = "You gonna update a event in user",
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
        value = ["/users/update/event"],
        produces = ["application/json"],
        consumes = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "eventId"]
    )
    fun updateEventInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "eventId", required = true) eventId: UUID,
        @RequestBody eventDTO: EventDTO
    ): ResponseEntity<UserDTO>

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
        params = ["username", "title", "fcmToken"]
    )
    fun dropOutSubscription(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "title", required = true) title: String,
        @RequestParam(name = "fcmToken", required = true) fcmToken: String
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
        value = "Add image to Pharmacy isn User",
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
        value = "add service in User",
        nickname = "addServiceInUser",
        notes = "Will add a service in user",
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
        value = ["/users/add/service"],
        consumes = ["application/json"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username"]
    )
    fun addServiceInUser(@RequestParam(name = "username", required = true) username: String, @RequestBody serviceDTO: ServiceDTO): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete service in user",
        nickname = "deleteServiceInUser",
        notes = "Will delete a service in user",
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
        value = ["/users/delete/service"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "owner"]
    )
    fun deleteServiceInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "owner", required = true) owner: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add image to service in user",
        nickname = "addImageToServiceInUser",
        notes = "Will add an image to service in user",
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
        value = ["/users/image/service"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "owner", "image"]
    )
    fun addImageToServiceInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "owner", required = true) owner: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete image to service in user",
        nickname = "deleteImageToServiceInUser",
        notes = "Will delete an image to service in User",
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
        value = ["/users/image/delete/service"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "owner", "image"]
    )
    fun deleteImageToServiceInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam("owner", required = true) owner: String, @RequestParam(name = "image", required = true) imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add news in User",
        nickname = "addNewsInUser",
        notes = "You can add new News",
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
        value = ["/users/add/news"],
        produces = ["application/json"],
        method = [RequestMethod.POST],
        params = ["username"]
    )
    fun addNewsInUser(@RequestParam(name = "username", required = true) username: String, @RequestBody newsDTO: NewsDTO): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete news in User",
        nickname = "deleteNewsInUser",
        notes = "delete news in User",
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
        value = ["/users/delete/news"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title"]
    )
    fun deleteNewsInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add image to news in User",
        nickname = "addImageToNewsInUser",
        notes = "You can add an image to news in User",
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
        value = ["/users/image/news"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "title", "image"]
    )
    fun addImageToNewsInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image") imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete image of news in User",
        nickname = "deleteImageToNewsInUser",
        notes = "You can delete image to news in User",
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
        value = ["/users/image/delete/news"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title", "image"]
    )
    fun deleteImageToNewsInUser(
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
        value = "delete bando in User",
        nickname = "deleteBandoInUser",
        notes = "Gonna delete bando in User",
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
        value = ["/users/delete/bando"],
        produces = ["application/json"],
        params = ["username", "title"],
        method = [RequestMethod.DELETE]
    )
    fun deleteBandoInUser(
        @RequestParam(name = "username") username: String,
        @RequestParam(name = "title") title: String
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add image to bando in User",
        nickname = "addImageToBandoInUser",
        notes = "You can add an image to bando in User",
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
        value = ["/users/image/bando"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "title", "image"]
    )
    fun addImageToBandoInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image") imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete image of bando in User",
        nickname = "deleteImageToBandoInUser",
        notes = "You can delete image to bando in User",
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
        value = ["/users/image/delete/bando"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title", "image"]
    )
    fun deleteImageToBandoInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "title", required = true) title: String,
        @RequestParam(name = "image", required = true) imageName: String
    ): ResponseEntity<UserDTO>

    // -> --------------------------------------------------------------------------------------------------------------

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
        value = "delete link in User",
        nickname = "deleteLinkInUser",
        notes = "Gonna delete link in User",
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
        value = ["/users/delete/link"],
        produces = ["application/json"],
        params = ["username", "title"],
        method = [RequestMethod.DELETE]
    )
    fun deleteLinkInUser(
        @RequestParam(name = "username") username: String,
        @RequestParam(name = "title") title: String
    ): ResponseEntity<UserDTO>

    // -> --------------------------------------------------------------------------------------------------------------
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
        value = "delete sponsor in User",
        nickname = "deleteSponsorInUser",
        notes = "Gonna delete sponsor in User",
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
        value = ["/users/delete/sponsor"],
        produces = ["application/json"],
        params = ["username", "title"],
        method = [RequestMethod.DELETE]
    )
    fun deleteSponsorInUser(
        @RequestParam(name = "username") username: String,
        @RequestParam(name = "title") title: String
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "add image to sponsor in User",
        nickname = "addImageToSponsorInUser",
        notes = "You can add an image to sponsor in User",
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
        value = ["/users/image/sponsor"],
        produces = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "title", "image"]
    )
    fun addImageToSponsorInUser(@RequestParam(name = "username", required = true) username: String, @RequestParam(name = "title", required = true) title: String, @RequestParam(name = "image") imageName: String): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "delete image of sponsor in User",
        nickname = "deleteImageToSponsorInUser",
        notes = "You can delete image to sponsor in User",
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
        value = ["/users/image/delete/sponsor"],
        produces = ["application/json"],
        method = [RequestMethod.DELETE],
        params = ["username", "title", "image"]
    )
    fun deleteImageToSponsorInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "title", required = true) title: String,
        @RequestParam(name = "image", required = true) imageName: String
    ): ResponseEntity<UserDTO>
    //>---------------------------------------------------------------------------------------------------------------

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


    @ApiOperation(
        value = "delete add in User",
        nickname = "deleteAdInUser",
        notes = "Gonna delete ad in User",
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
        value = ["/users/delete/ad"],
        produces = ["application/json"],
        params = ["username", "title"],
        method = [RequestMethod.DELETE]
    )
    fun deleteAdInUser(
        @RequestParam(name = "username") username: String,
        @RequestParam(name = "title") title: String
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a pharmacy in user",
        nickname = "updatePharmacyInUser",
        notes = "You gonna update a pharmacy in user",
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
        value = ["/users/update/pharmacy"],
        produces = ["application/json"],
        consumes = ["application/json"],
        method = [RequestMethod.PUT],
        params = ["username", "pharmacyId"]
    )
    fun updatePharmacyInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "pharmacyId", required = true) pharmacyId: UUID,
        @RequestBody pharmacyDTO: PharmacyDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a tourism in User",
        nickname = "updateTourismInUser",
        notes = "You gonna update a tourism in user",
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
        value = ["/users/update/tourism"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username", "tourismId"],
        method = [RequestMethod.PUT],
    )
    fun updateTourismInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "tourismId", required = true) tourismId: UUID,
        @RequestBody tourismDTO: TourismDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a bando in User",
        nickname = "updateBandoInUser",
        notes = "You will update a tourism in User",
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
        value = ["/users/update/bando"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username", "bandoId"],
        method = [RequestMethod.PUT]
    )
    fun updateBandoInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "bandoId", required = true) bandoId: UUID,
        @RequestBody bandoDTO: BandoDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a death in User",
        nickname = "updateDeathInUser",
        notes = "You gonna update a death in User",
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
        value = ["/users/update/death"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username", "deathId"],
        method = [RequestMethod.PUT]
    )
    fun updateDeathInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "deathId", required = true) deathId: UUID,
        @RequestBody deathDTO: DeathDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a service in User",
        nickname = "updateServiceInUser",
        notes = "You gonna update a service in User",
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
        value = ["/users/update/service"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username", "serviceId"],
        method = [RequestMethod.PUT]
    )
    fun updateServiceInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "serviceId", required = true) serviceId: UUID,
        @RequestBody serviceDTO: ServiceDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a news in User",
        nickname = "updateNewsInUser",
        notes = "You gonna update a news in User",
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
        value = ["/users/update/news"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username", "newsId"],
        method = [RequestMethod.PUT]
    )
    fun updateNewsInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "newsId", required = true) newsId: UUID,
        @RequestBody newsDTO: NewsDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a link in User",
        nickname = "updateLinkInUser",
        notes = "You gonna update a link in User",
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
        value = ["/users/update/link"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username", "linkId"],
        method = [RequestMethod.PUT]
    )
    fun updateLinkInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "linkId", required = true) linkId: UUID,
        @RequestBody linkDTO: LinkDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a sponsor in User",
        nickname = "updateSponsorInUser",
        notes = "You will update a sponsor in User",
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
        value = ["/users/update/sponsor"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username", "sponsorId"],
        method = [RequestMethod.PUT]
    )
    fun updateSponsorInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "sponsorId", required = true) sponsorId: UUID,
        @RequestBody sponsorDTO: SponsorDTO
    ): ResponseEntity<UserDTO>

    @ApiOperation(
        value = "Update a ad in User",
        nickname = "updateAdInUser",
        notes = "You will update an ad in User",
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
        value = ["/users/update/ad"],
        produces = ["application/json"],
        consumes = ["application/json"],
        params = ["username", "adId"],
        method = [RequestMethod.PUT]
    )
    fun updateAdInUser(
        @RequestParam(name = "username", required = true) username: String,
        @RequestParam(name = "adId", required = true) adId: UUID,
        @RequestBody adDTO: AdDTO
    ): ResponseEntity<UserDTO>
}
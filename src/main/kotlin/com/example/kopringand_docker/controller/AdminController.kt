package com.example.kopringand_docker.controller

import com.example.kopringand_docker.service.UserInfoService
import com.example.kopringand_docker.vo.FailResponseVO
import com.example.kopringand_docker.vo.ResponseVO
import com.example.kopringand_docker.vo.SuccessResponseVO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/admin")
class AdminController(private val userInfoService: UserInfoService) {

    @PostMapping("/deleteUser")
    fun deleteUser(@RequestBody user: HashMap<String, Object>): ResponseVO {
        val userId = user["userId"].toString()

        return try {
            SuccessResponseVO("Success delete $userId", userInfoService.deleteUser(userId))
        } catch (e: RuntimeException) {
            FailResponseVO("Fail delete $userId", e.message.toString())
        }
    }

    @PostMapping("/updateUsername")
    fun updateUsername(@RequestBody user: HashMap<String, Object>): ResponseVO {
        val userId = user["userId"].toString()
        val username = user["username"].toString()

        return try {
            SuccessResponseVO("Success change username of $userId", userInfoService.changeUsername(userId, username))
        } catch (e: RuntimeException) {
            FailResponseVO("Fail change username of $userId", e.message.toString())
        }
    }

    @PostMapping("/updateUserPassword")
    fun updateUserPassword(@RequestBody user: HashMap<String, Object>): ResponseVO {
        val userId = user["userId"].toString()
        val password = user["password"].toString()

        return try {
            SuccessResponseVO("Success change password of $userId", userInfoService.changeUserPassword(userId, password))
        } catch (e: RuntimeException) {
            FailResponseVO("Fail change user password of $userId", e.message.toString())
        }
    }

    @PostMapping("/updateUserEmail")
    fun updateUserEmail(@RequestBody user: HashMap<String, Object>): ResponseVO {
        val userId = user["userId"].toString()
        val email = user["email"].toString()

        return try {
            SuccessResponseVO("Success change userEmail of $userId", userInfoService.changeUserEmail(userId, email))
        } catch (e: RuntimeException) {
            FailResponseVO("Fail change userEmail of $userId", e.message.toString())
        }
    }
}

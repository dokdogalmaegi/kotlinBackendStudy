package com.example.kopringand_docker.controller

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.service.UserInfoService
import com.example.kopringand_docker.vo.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
class UserController(private val userInfoService: UserInfoService) {
    @GetMapping("/userInfoList")
    fun getUserInfoList(): List<UserInfo> {
        return userInfoService.getUserList()
    }

    @PostMapping("/isExistsUser")
    fun isExistsUser(@RequestBody user: HashMap<String, Object>): ResponseVO {
        val userId: String = user["userId"].toString()

        return try {
            SuccessResponseVO("Success search $userId", userInfoService.isExistsUserByUserId(userId))
        } catch (e: RuntimeException) {
            FailResponseVO("Fail search $userId", e.message.toString())
        }
    }

    @PostMapping("/signUp")
    fun signUp(@RequestBody userInfoSignUpVO: UserInfoSignUpVO): ResponseVO {
        val (userId: String) = userInfoSignUpVO

        return try {
            SuccessResponseVO("Success sign up $userId", userInfoService.signUp(userInfoSignUpVO))
        } catch (e: RuntimeException) {
            FailResponseVO("Fail sign up $userId", e.message.toString())
        }
    }
}

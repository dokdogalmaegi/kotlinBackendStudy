package com.example.kopringand_docker.controller

import com.example.kopringand_docker.service.UserInfoService
import com.example.kopringand_docker.vo.FailResponseVO
import com.example.kopringand_docker.vo.ResponseVO
import com.example.kopringand_docker.vo.SuccessResponseVO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping("/admin")
class AdminController(private val userInfoService: UserInfoService) {

    @PostMapping("/deleteUser")
    fun deleteUser(@RequestBody user: HashMap<String, Object>): ResponseVO {
        val userId = user["userId"].toString()

        return try {
            SuccessResponseVO("Success delete $userId", userInfoService.deleteUser(userId))
        } catch (e: Exception) {
            FailResponseVO("Fail delete $userId", e.message.toString())
        }
    }
}

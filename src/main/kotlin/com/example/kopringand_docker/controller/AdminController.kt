package com.example.kopringand_docker.controller

import com.example.kopringand_docker.service.UserInfoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(private val userInfoService: UserInfoService) {

    @PostMapping("/deleteUser")
    fun deleteUser(@RequestParam("userId") userId: String) {
        userInfoService.deleteUser(userId);
    }
}
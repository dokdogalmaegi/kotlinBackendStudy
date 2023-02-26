package com.example.kopringand_docker.controller

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.service.UserInfoService
import com.example.kopringand_docker.vo.UserInfoLoginVO
import com.example.kopringand_docker.vo.UserInfoSignUpVO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(private val userInfoService: UserInfoService) {
    @GetMapping("/userInfoList")
    fun getUserInfoList(): List<UserInfo> {
        return userInfoService.getUserList();
    }

    @PostMapping("/isExistsUser")
    fun isExistsUser(@RequestBody userInfoLoginVO: UserInfoLoginVO): Boolean {
        val ( userId: String, password: String ) = userInfoLoginVO;

        return userInfoService.loginUser(userId, password);
    }

    @PostMapping("/signUp")
    fun signUp(@RequestBody userInfoSignUpVO: UserInfoSignUpVO): UserInfo {
        val ( userId: String ) = userInfoSignUpVO;

        userInfoService.signUp(userInfoSignUpVO);

        return userInfoService.getUserByUserId(userId);
    }
}

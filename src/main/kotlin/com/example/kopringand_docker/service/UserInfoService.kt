package com.example.kopringand_docker.service

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.vo.UserInfoSignUpVO

interface UserInfoService {
    fun getUserList(): List<UserInfo>

    fun loginUser(userId: String, password: String): Boolean

    fun signUp(userInfoSignUpVO: UserInfoSignUpVO)

    fun getUserByUserId(userId: String): UserInfo
}
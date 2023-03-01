package com.example.kopringand_docker.service

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.vo.UserInfoSignUpVO

interface UserInfoService {
    fun getUserList(): List<UserInfo>

    fun isExistsUserByUserId(userId: String): Boolean

    fun signUp(userInfoSignUpVO: UserInfoSignUpVO): UserInfo

    fun deleteUser(userId: String)

    fun changeUsername(userId: String, changeUsername: String): HashMap<String, String>

    fun getUserByUserId(userId: String): UserInfo
}

package com.example.kopringand_docker.service

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.vo.UserInfoLoginVO
import com.example.kopringand_docker.vo.UserInfoSignUpVO

interface UserInfoService {
    fun getUserList(): List<UserInfo>

    fun isExistsUserByUserId(userId: String): Boolean

    fun signUp(userInfoSignUpVO: UserInfoSignUpVO): UserInfo

    fun signIn(userId: String, password: String): Boolean

    fun deleteUser(userId: String)

    fun changeUserInfo(userId: String, username: String, password: String, email: String): HashMap<String, String>

    fun changeUsername(userId: String, changeUsername: String): HashMap<String, String>

    fun changeUserEmail(userId: String, email: String): HashMap<String, String>

    fun getUserByUserId(userId: String): UserInfo
}

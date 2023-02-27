package com.example.kopringand_docker.serviceImpl

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.repository.UserInfoRepository
import com.example.kopringand_docker.service.UserInfoService
import com.example.kopringand_docker.vo.UserInfoSignUpVO
import org.springframework.stereotype.Service

@Service
class UserInfoServiceImpl(private val userInfoRepository: UserInfoRepository) : UserInfoService {
    override fun getUserList() = userInfoRepository.findAll()

    override fun loginUser(userId: String, password: String) = userInfoRepository.existsByUserIdAndPassword(userId, password)

    override fun signUp(userInfoSignUpVO: UserInfoSignUpVO): UserInfo {
        val (userId: String, username: String, password: String, email: String) = userInfoSignUpVO

        if (userInfoRepository.existsByUserId(userId)) {
            throw RuntimeException("Exists user id")
        }

        val userInfoEntity: UserInfo = UserInfo(userId, username, password, email)

        return userInfoRepository.save(userInfoEntity)
    }

    override fun deleteUser(userId: String) {
        if (!userInfoRepository.existsByUserId(userId)) {
            throw RuntimeException("Not exists user id")
        }

        val selectedUserInfo: UserInfo = getUserByUserId(userId)
        userInfoRepository.delete(selectedUserInfo)
    }

    override fun getUserByUserId(userId: String) = userInfoRepository.findByUserId(userId)
}

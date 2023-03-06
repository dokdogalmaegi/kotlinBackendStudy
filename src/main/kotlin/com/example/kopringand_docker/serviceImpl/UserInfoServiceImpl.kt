package com.example.kopringand_docker.serviceImpl

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.entity.getEncryptionOf
import com.example.kopringand_docker.repository.UserInfoRepository
import com.example.kopringand_docker.service.UserInfoService
import com.example.kopringand_docker.vo.UserInfoSignUpVO
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class UserInfoServiceImpl(private val userInfoRepository: UserInfoRepository) : UserInfoService {
    override fun getUserList() = userInfoRepository.findAll()

    override fun isExistsUserByUserId(userId: String) = userInfoRepository.existsByUserId(userId)

    override fun signUp(userInfoSignUpVO: UserInfoSignUpVO): UserInfo {
        val (userId: String, username: String, password: String, email: String) = userInfoSignUpVO

        if (userInfoRepository.existsByUserId(userId)) {
            throw RuntimeException("Exists user id")
        }

        val encryptedPassword = getEncryptionOf(password)
        val userInfoEntity: UserInfo = UserInfo(userId, username, encryptedPassword, email)

        return userInfoRepository.save(userInfoEntity)
    }

    override fun signIn(userId: String, password: String): Boolean {
        return try {
            val encryptedPassword: String = getEncryptionOf(password)

            userInfoRepository.existsByUserIdAndPassword(userId, encryptedPassword)
        } catch (e: Exception) {
            false
        }
    }

    override fun deleteUser(userId: String) {
        if (!userInfoRepository.existsByUserId(userId)) {
            throw RuntimeException("Not exists user id")
        }

        val selectedUserInfo: UserInfo = getUserByUserId(userId)
        userInfoRepository.delete(selectedUserInfo)
    }

    override fun changeUserInfo(userId: String, username: String?, password: String?, email: String?): HashMap<String, String> {
        val returnHashMap: HashMap<String, String> = HashMap()

        return returnHashMap.apply {
            val beforeUser = getUserByUserId(userId)

            if (username != null) {
                put("beforeUsername", beforeUser.username)
                beforeUser.changeUsername(username)
                put("afterUsername", username)
            }
            
            if (password != null) {
                put("beforePassword", beforeUser.password)
                beforeUser.changePassword(password)
                put("afterPassword", password)
            }

            if (email != null) {
                put("beforeEmail", beforeUser.email)
                beforeUser.changeUserEmail(email)
                put("afterEmail", email)
            }

            userInfoRepository.save(beforeUser)
        }
    }

    override fun getUserByUserId(userId: String) = userInfoRepository.findByUserId(userId)
}

package com.example.kopringand_docker.serviceImpl

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.entity.getEncryptionOf
import com.example.kopringand_docker.vo.UserInfoSignUpVO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.RuntimeException

@TestMethodOrder(value = MethodOrderer.OrderAnnotation::class)
@SpringBootTest
class UserInfoServiceImplTest() {

    @Autowired
    private lateinit var userInfoServiceImpl: UserInfoServiceImpl

    private val userInfo: UserInfoSignUpVO = UserInfoSignUpVO("testUser", "testNewUser", "testPassword", "test@gamil.com")

    private val beChangedUsername: String = "testUpdateUser"

    @Test
    fun `check exists data in USER_INFO Table`() {
        val isExistsUserList = userInfoServiceImpl.getUserList().size > 0

        assertEquals(isExistsUserList, true)
    }

    @Test
    @Order(0)
    fun `Success sign up new user`() {
        assertDoesNotThrow {
            userInfoServiceImpl.signUp(userInfo)
        }
    }

    @Order(1)
    @Test
    fun `Success sign in new user`() {
        assertEquals(userInfoServiceImpl.signIn(userInfo.userId, getEncryptionOf(userInfo.password)), true)
    }

    @Order(2)
    @Test
    fun `Fail sign up same userId User`() {
        try {
            userInfoServiceImpl.signUp(userInfo)
        } catch (e: RuntimeException) {
            assertEquals("Exists user id", e.message.toString())
        }
    }

    @Order(3)
    @Test
    fun `success getUserById from new user`() {
        val (userId, username, password, email) = userInfo

        val savedNewUserInfo: UserInfo = userInfoServiceImpl.getUserByUserId(userInfo.userId)

        assertEquals(userId, savedNewUserInfo.userId)
        assertEquals(username, savedNewUserInfo.username)
        assertEquals(password, savedNewUserInfo.password)
        assertEquals(email, savedNewUserInfo.email)
    }

    @Order(4)
    @Test
    fun `Success changeUsername`() {
        assertDoesNotThrow {
            val changeUsernameResult = userInfoServiceImpl.changeUsername(userInfo.userId, beChangedUsername)

            assertEquals(userInfo.username, changeUsernameResult["beforeUsername"])
            assertEquals(beChangedUsername, changeUsernameResult["afterUsername"])
        }
    }

    @Order(5)
    @Test
    fun `Fail changeUsername from same username`() {
        try {
            userInfoServiceImpl.changeUsername(userInfo.userId, beChangedUsername)
        } catch (e: RuntimeException) {
            assertEquals(e.message.toString(), "Must be different param and instance username")
        }
    }

    @Order(6)
    @Test
    fun `Success delete testUser`() {
        val savedTestUserInfo: UserInfo = userInfoServiceImpl.getUserByUserId(userInfo.userId)

        assertDoesNotThrow {
            userInfoServiceImpl.deleteUser(savedTestUserInfo.userId)
        }
    }
}

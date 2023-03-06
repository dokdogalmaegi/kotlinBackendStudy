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

    private val beChangedUserEmail: String = "testUpdateUser@gmail.com"

    private val beChangedUserPassword: String = "test2023"

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
        assertEquals(userInfoServiceImpl.signIn(userInfo.userId, userInfo.password), true)
    }

    @Order(2)
    @Test
    fun `Fail sign up same userId User`() {
        var errorMessage: String = ""

        try {
            userInfoServiceImpl.signUp(userInfo)
        } catch (e: RuntimeException) {
            errorMessage = e.message.toString()
        }

        assertEquals(errorMessage, "Exists user id")
    }

    @Order(3)
    @Test
    fun `success getUserById from new user`() {
        val (userId, username, password, email) = userInfo

        val savedNewUserInfo: UserInfo = userInfoServiceImpl.getUserByUserId(userInfo.userId)

        assertEquals(userId, savedNewUserInfo.userId)
        assertEquals(username, savedNewUserInfo.username)
        assertEquals(getEncryptionOf(password), savedNewUserInfo.password)
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
        var errorMessage: String = ""

        try {
            userInfoServiceImpl.changeUsername(userInfo.userId, beChangedUsername)
        } catch (e: RuntimeException) {
            errorMessage = e.message.toString()
            assertEquals(e.message.toString(), "Must be different param and instance username")
        }

        assertEquals(errorMessage, "Must be different param and instance username")
    }

    @Order(6)
    @Test
    fun `Success changeUserPassword`() {
        assertDoesNotThrow {
            val changeUsernameResult = userInfoServiceImpl.changeUserPassword(userInfo.userId, beChangedUserPassword)

            assertEquals(getEncryptionOf(userInfo.password), changeUsernameResult["beforePassword"])
            assertEquals(beChangedUserPassword, changeUsernameResult["afterPassword"])
        }
    }

    @Order(7)
    @Test
    fun `Fail changeUserEmail from same user password`() {
        var errorMessage: String = ""

        try {
            userInfoServiceImpl.changeUserPassword(userInfo.userId, beChangedUserPassword)
        } catch (e: RuntimeException) {
            errorMessage = e.message.toString()
        }

        assertEquals(errorMessage, "Must be different param and instance password")
    }

    @Order(8)
    @Test
    fun `Success changeUserEmail`() {
        assertDoesNotThrow {
            val changeUsernameResult = userInfoServiceImpl.changeUserEmail(userInfo.userId, beChangedUserEmail)

            assertEquals(userInfo.email, changeUsernameResult["beforeEmail"])
            assertEquals(beChangedUserEmail, changeUsernameResult["afterEmail"])
        }
    }

    @Order(9)
    @Test
    fun `Fail changeUserEmail from same user email`() {
        var errorMessage: String = ""

        try {
            userInfoServiceImpl.changeUserEmail(userInfo.userId, beChangedUserEmail)
        } catch (e: RuntimeException) {
            errorMessage = e.message.toString()
        }

        assertEquals(errorMessage, "Must be different param and instance email")
    }

    @Order(10)
    @Test
    fun `Success delete testUser`() {
        assertDoesNotThrow {
            userInfoServiceImpl.deleteUser(userInfo.userId)
        }
    }
}

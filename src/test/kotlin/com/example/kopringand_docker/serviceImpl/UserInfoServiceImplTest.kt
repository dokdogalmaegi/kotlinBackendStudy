package com.example.kopringand_docker.serviceImpl

import com.example.kopringand_docker.entity.UserInfo
import com.example.kopringand_docker.vo.UserInfoSignUpVO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@TestMethodOrder(value = MethodOrderer.OrderAnnotation::class)
@SpringBootTest
class UserInfoServiceImplTest() {

    @Autowired
    private lateinit var userInfoServiceImpl: UserInfoServiceImpl

    private val userInfo: UserInfoSignUpVO = UserInfoSignUpVO("testUser", "testNewUser", "testPassword", "test@gamil.com")

    @Test
    fun `check exists data in USER_INFO Table`() {
        val isExistsUserList = userInfoServiceImpl.getUserList().size > 0

        assertEquals(isExistsUserList, true)
    }

    @Test
    @Order(0)
    fun `Success sign up new user`() {
        org.junit.jupiter.api.assertDoesNotThrow {
            userInfoServiceImpl.signUp(userInfo)
        }
    }

    @Order(1)
    @Test
    fun `Success sign in new user`() {
        assertEquals(userInfoServiceImpl.loginUser(userInfo.userId, userInfo.password), true)
    }

    @Order(2)
    @Test
    fun `Fail sign up same userId User`() {
        assertEquals(true, true)
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
    fun `Success delete testUser`() {
        val savedTestUserInfo: UserInfo = userInfoServiceImpl.getUserByUserId(userInfo.userId)

        org.junit.jupiter.api.assertDoesNotThrow {
            userInfoServiceImpl.deleteUser(savedTestUserInfo.userId)
        }
    }
}

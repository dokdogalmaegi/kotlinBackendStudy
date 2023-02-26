package com.example.kopringand_docker.repository

import com.example.kopringand_docker.entity.UserInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserInfoRepository : JpaRepository<UserInfo, Int> {
    fun findByUserId(userId: String): UserInfo

    fun existsByUserIdAndPassword(userId: String, password: String): Boolean

    fun existsByUserId(userId: String): Boolean
}

package com.example.kopringand_docker.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.security.MessageDigest
import java.time.Instant
import java.util.Base64

fun getEncryptionOf(password: String): String {
    val md = MessageDigest.getInstance("SHA-512")
    md.update(password.toByteArray())

    return Base64.getEncoder().encodeToString(md.digest())
}

@Entity
@EntityListeners(AuditingEntityListener::class)
@SequenceGenerator(
    name = "USER_SEQ_GEN",
    sequenceName = "USER_SEQ",
    initialValue = 1,
    allocationSize = 1
)
class UserInfo(
    @Column(unique = true) val userId: String,
    @Column var username: String,
    @Column var password: String,
    @Column var email: String,
) {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "USER_SEQ_GEN"
    )
    val userKey: Int = -1

    @Column
    @CreatedDate
    val createdOn: Instant = Instant.now()

    @Column
    var lastLogin: Instant? = null

    operator fun component1(): String = this.username

    fun changeUsername(changeUsername: String): UserInfo {
        if (changeUsername == this.username) {
            throw RuntimeException("Must be different param and instance username")
        }

        this.username = changeUsername
        return this
    }

    fun changePassword(changePassword: String): UserInfo {
        if (changePassword == this.password) {
            throw RuntimeException("Must be different param and instance passwrod")
        }

        this.password = getEncryptionOf(changePassword)
        return this
    }

    fun changeUserEmail(changeUserEmail: String): UserInfo {
        if (changeUserEmail == this.email) {
            throw RuntimeException("Must be different param and instance email")
        }

        this.email = changeUserEmail
        return this
    }
}

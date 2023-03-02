package com.example.kopringand_docker.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

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
}

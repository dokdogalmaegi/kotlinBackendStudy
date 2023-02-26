package com.example.kopringand_docker.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@EntityListeners(AuditingEntityListener::class)
class UserInfo(
    @Column(unique = true) val userId: String,
    @Column val username: String,
    @Column val password: String,
    @Column val email: String,
) {
    @Id
    val userKey: Int = -1

    @Column
    @CreatedDate
    val createdOn: Instant = Instant.now()

    @Column
    var lastLogin: Instant? = null
}

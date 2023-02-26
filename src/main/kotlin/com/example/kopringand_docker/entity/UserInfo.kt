package com.example.kopringand_docker.entity

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy
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
    @Column val username: String,
    @Column val password: String,
    @Column val email: String,
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
}

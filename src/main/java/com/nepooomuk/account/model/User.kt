package com.nepooomuk.account.model

import lombok.Data
import org.hibernate.annotations.GenericGenerator
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.*
import javax.validation.constraints.NotNull

@Data
@Entity
@Table(name = "user")
open class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String? = null
        set(id) {
            field = this.id
        }

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    internal var email: String = ""

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @NotNull(message = "*Password is required")
    internal var password: String = ""

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")))
    var roles: List<Role>? = null
        set(roles) {
            field = this.roles
        }

    constructor() {
        //ASK JPA :-(
    }

    constructor(user: User)
}



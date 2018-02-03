package com.nepooomuk.account.model

import lombok.Data
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

@Data
@Entity
@Table(name = "role")
class Role {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String? = null
        set(id) {
            field = this.id
        }

    @Column(name = "role_name")
    var roleName: String? = null
        set(roleName) {
            field = this.roleName
        }

    @Column(name = "description")
    var description: String? = null
        set(description) {
            field = this.description
        }
}

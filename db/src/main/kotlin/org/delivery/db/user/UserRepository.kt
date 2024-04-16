package org.delivery.db.user

import org.delivery.db.user.enums.UserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity,Long> {

    //SELECT * FROM USER WHERE ID = ? AND STATUS = ? ORDER BY ID DESC LIMIT 1;
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: UserStatus?): UserEntity?

    //SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ? AND STATUS = ? ORDER BY ID DESC LIMIT 1;
    fun findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
        email: String?,
        password: String?,
        status: UserStatus?
    ): UserEntity?

}
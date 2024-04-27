package org.delivery.db.userordermenu

import org.delivery.db.storemenu.StoreMenuEntity
import org.delivery.db.userorder.UserOrderEntity
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus
import javax.persistence.*

@Entity
@Table(name = "user_order_menu")
class UserOrderMenuEntity (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?=null,

    @field:JoinColumn(nullable = false, name = "user_order_id")
    @field:ManyToOne
    var userOrder : UserOrderEntity?=null,

    @field:JoinColumn(nullable = false)
    @field:ManyToOne
    var storeMenu: StoreMenuEntity?=null,

    @field:Enumerated(EnumType.STRING)
    @field:Column(length = 50, nullable = false)
    var status: UserOrderMenuStatus?=null,
){
    override fun toString(): String {
        return "UserOrderMenuEntity(id=$id, userOrder=$userOrder, storeMenu=$storeMenu, status=$status)"
    }
}
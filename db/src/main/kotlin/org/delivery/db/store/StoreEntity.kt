package org.delivery.db.store

import org.delivery.db.store.enums.StoreCategory
import org.delivery.db.store.enums.StoreStatus
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "store")
class StoreEntity (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?=null,

    @field:Column(length = 100, nullable = false)
    var name: String?=null,

    @field:Column(length = 150, nullable = false)
    var address: String?=null,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: StoreStatus?=null,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var category:StoreCategory?=null,

    var star: Double?=null,

    @field:Column(length = 200, nullable = false)
    var thumbnailUrl: String?=null,

    @field:Column(precision = 11,scale = 4,nullable = false)
    var minimumAmount: BigDecimal?=null,

    @field:Column(precision = 11,scale = 4,nullable = false)
    var minimumDeliveryAmount: BigDecimal?=null,

    @field:Column(length = 20)
    var phoneNumber: String?=null,

){

    override fun toString(): String {
        return "StoreEntity(id=$id, name=$name, address=$address, status=$status, category=$category, star=$star, thumbnailUrl=$thumbnailUrl, minimumAmount=$minimumAmount, minimumDeliveryAmount=$minimumDeliveryAmount, phoneNumber=$phoneNumber)"
    }
}
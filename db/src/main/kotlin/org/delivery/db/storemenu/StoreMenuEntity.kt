package org.delivery.db.storemenu

import org.delivery.db.store.StoreEntity
import org.delivery.db.storemenu.enums.StoreMenuStatus
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "store_menu")
@Entity
class StoreMenuEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:JoinColumn(nullable = false, name= "store_id")
    @field:ManyToOne
    var store: StoreEntity?= null,

    @field:Column(nullable = false, length = 100)
    var name: String? = null,

    @field:Column(nullable = false, precision = 11, scale = 2)
    var amount:BigDecimal? = null,

    @field:Column(nullable = false, length = 50)
    @field:Enumerated(EnumType.STRING)
    var status:StoreMenuStatus? = null,

    @field:Column(nullable = false,length = 200)
    var thumbnailUrl: String? = null,

    var likeCount:Int = 0,

    var sequence:Int = 0
) {
    override fun toString(): String {
        return "StoreMenuEntity(id=$id, store=$store, name=$name, amount=$amount, status=$status, thumbnailUrl=$thumbnailUrl, likeCount=$likeCount, sequence=$sequence)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StoreMenuEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }


}
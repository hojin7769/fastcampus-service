package org.delivery.db.storemenu

import org.delivery.db.storemenu.enums.StoreMenuStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface StoreMenuRepository : JpaRepository<StoreMenuEntity, Long> {
    // 유효한 메뉴를 가져오기
    // select * from store_menu where id = ? status = ? order by id desc
    fun findFirstByIdAndStatusOrderByIdDesc(id: Long?, status: StoreMenuStatus?): StoreMenuEntity?

    // 특정 가게의 메뉴 가져오기
    // select * from store_menu where store_id = ? and status = ? order by sequence desc
    fun findAllByStoreIdAndStatusOrderBySequenceDesc(storeId: Long?, status: StoreMenuStatus?): List<StoreMenuEntity>
}

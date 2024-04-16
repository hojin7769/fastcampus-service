//package org.delivery.db.store;
//
//import org.delivery.db.store.enums.StoreCategory;
//import org.delivery.db.store.enums.StoreStatus;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
//
//
//    //특정 유효한 스토어
//    // select * FROM store WHERE id = ? STATUS = ? order by id desc limit 1
//    Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);
//
//    // 유효한 스토어 리스트
//    // select * FROM store WHERE STATUS = ? order by id desc
//    List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus status);
//
//    //유효한 특정 카테고리의 스토어 리스트
//    // select * FROM store WHERE category = ? AND STATUS = ? order by id desc
//    List<StoreEntity> findAllByStatusAndCategoryOrderByStarDesc( StoreStatus status,StoreCategory category);
//
//    // select * from store where name = ? and status = ? order by id desc limit 1
//    Optional<StoreEntity> findFirstByNameAndStatusOrderByIdDesc(String name, StoreStatus status);
//}

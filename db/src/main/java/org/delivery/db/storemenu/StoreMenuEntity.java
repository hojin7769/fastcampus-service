package org.delivery.db.storemenu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "store_menu")
public class StoreMenuEntity extends BaseEntity {

    @JoinColumn(nullable = false,name="store_id")
    @ManyToOne
    private StoreEntity store;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,precision = 11,scale = 2)
    private BigDecimal amount;

    @Column(nullable = false,length = 50)
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    @Column(nullable = false,length = 200)
    private String thumbnailUrl;

    private int likeCount;

    private int sequence;
}

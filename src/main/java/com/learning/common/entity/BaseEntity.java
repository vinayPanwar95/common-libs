package com.learning.common.entity;

import com.learning.common.model.ArchivingCode;
import com.learning.common.model.OrderStatusCode;
import com.learning.common.useractions.security.ContextUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
// this make sure class is not assigned to any table but its mapping and properties can be used by its sub classes
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    public static final String NOT_DELETED = "DELETED = false";
    public static final String DELETED = "DELETED = true";

    public static final Integer NEW_ORDER_VERSION = 1;
    private static final String DETAILS_FORMAT = "[Order Id : %s, Version : %s , Status : %s]";


    @Id
    private UUID id;

    @Version
    @Column(name="VERSION")
    private Integer version;

    @Column(name= "ORDER_ID")
    private UUID orderId;

    @Column(name= "ORDER_VERSION")
    private Integer orderVersion;

    @Column(name ="STATUS")
    @Enumerated(EnumType.STRING)
    @NonNull
    private OrderStatusCode status;



    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Column(name="CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name="LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;

    @Column(name="DELETED")
    @Builder.Default
    private Boolean deleted = false;

    @Column(name="ARCHIVING_CODE")
    @Builder.Default
    private String archivingCode = ArchivingCode.NOT_ARCHIVED.name();

    public String getDetailsAsString(){
        return String.format(DETAILS_FORMAT, orderId , version , status);
    }

    public void populateTechnicalFields(ContextUser contextUser, LocalDateTime nowInUTCTime, UUID uuid) {
        setId(uuid);
        setLastModifiedDate(nowInUTCTime);
        setCreatedDate(nowInUTCTime);
        setCreatedBy(contextUser.getUsername());
        setModifiedBy(contextUser.getUsername());
    }
}

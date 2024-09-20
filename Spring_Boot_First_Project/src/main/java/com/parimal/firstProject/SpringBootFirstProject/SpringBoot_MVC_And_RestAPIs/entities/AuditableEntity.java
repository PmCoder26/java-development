package com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass      // JPA will not map the fields of this class to the database if we don't use this annotation.
@EntityListeners(AuditingEntityListener.class)      // auditing concept, here, we need to use @EnableJpaAuditing to enable the auditing in the configurations class
@Data
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)        // for more advanced auditing, it creates separate table in database for maintaining the records of auditing.
public class AuditableEntity {

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedBy
    private String modifiedBy;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}

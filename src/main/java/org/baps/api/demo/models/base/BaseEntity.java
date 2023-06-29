package org.baps.api.demo.models.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @CreatedDate
    @Column(name = "`createdAt`", columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "`lastModifiedAt`", columnDefinition = "timestamp")
    private LocalDateTime lastModifiedAt;

    @CreatedBy
    @Column(name = "`createdBy`")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "`lastModifiedBy`")
    private String lastModifiedBy;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "`status`")
    private Status status = Status.ACTIVE;

}

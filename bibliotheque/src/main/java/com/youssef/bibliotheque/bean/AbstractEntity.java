package com.youssef.bibliotheque.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {




    @CreatedDate
    @Column(
            name = "createdDate",
            nullable = false,
            updatable = false
    )
    private LocalDateTime creationDate;


    @LastModifiedDate
    @Column(name="LastModifiedDate")
    private LocalDateTime lastModifiedDate;










}

package org.example.core;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.OffsetDateTime;

@Data
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_seq_gen")
    @SequenceGenerator(name = "base_seq_gen", sequenceName = "product_seq", allocationSize = 10)
    private Long id;

    //Audit Fields
    @Column(name = "created_timestamp", nullable = false)
    @CreatedDate
    private OffsetDateTime createdTimestamp = OffsetDateTime.now();

    @Column(name = "updated_timestamp", nullable = false)
    @LastModifiedBy
    private OffsetDateTime updatedTimestamp = OffsetDateTime.now();

    @Column(name = "created_by", nullable = false)
    private Long createdBy = 1L;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy = 1L;
}

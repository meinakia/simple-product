package org.example.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@Table(name="product")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    @Column(name="name", nullable = false)
    private String name;
}

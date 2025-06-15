package com.moduletwo.innopolis.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "test")
@Data
public class TestEntity {
    @Id
    private String id;
}

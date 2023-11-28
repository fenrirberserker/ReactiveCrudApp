package com.crud.crudapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("Orders")
public class Order {
    @Id
    @Column("id")
    private Long id;
    @Column("amount")
    private Double amount;
    @Column("date")
    private LocalDateTime date;

}

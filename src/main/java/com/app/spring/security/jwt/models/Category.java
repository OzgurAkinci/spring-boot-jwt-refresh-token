package com.app.spring.security.jwt.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name= "category")

public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String slug;
}

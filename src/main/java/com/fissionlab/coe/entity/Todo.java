package com.fissionlab.coe.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "completed")
    private Boolean completed = false;

    @Column(name = "order_number")
    private Integer order;


    public String getUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/todos/" + this.getId();
    }
}

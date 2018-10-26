package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @Column(name="seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seq;

    @Column(name="team_name")
    private String teamName;

    @Column(name="created_time", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createdTime;



}

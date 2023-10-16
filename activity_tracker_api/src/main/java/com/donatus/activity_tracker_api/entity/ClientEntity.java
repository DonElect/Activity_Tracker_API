package com.donatus.activity_tracker_api.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", length = 25)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 68)
    private String password;

    @Column(name = "occupation",nullable = false , length = 100)
    private String occupation;

    @Column(name = "address", length = 100)
    private String address;

    private String role = "USER";

    @Column(name = "active", nullable = false)
    private byte active;

    @OneToMany(mappedBy = "clientEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskEntity> tasksList;


    public void addTask(TaskEntity task){
        if (tasksList == null){
            tasksList = new ArrayList<>();
        }

        tasksList.add(task);

        task.setClientEntity(this);

    }
}

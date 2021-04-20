package com.example.dendiemsite.Entity;


import com.example.dendiemsite.Model.TaskDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "task_table")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String taskType;
    private String taskStartDate;
    private String taskEndDate;
    private String isActive;
    private String respUser;
    private  String taskDrug;
    private String taskDetails;

    private  String taskTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_table_id")
    private UserEntity author;


    public TaskEntity(String taskType, String taskStartDate, String taskEndDate, UserEntity author, String isActive, String respUser) {
        this.taskType = taskType;
        this.taskStartDate = taskStartDate;
        this.taskEndDate = taskEndDate;
        this.author = author;
        this.isActive = isActive;
        this.respUser = respUser;
    }

    public TaskEntity(TaskEntity t) {
        this.taskType = t.taskType;
        this.taskStartDate = t.taskStartDate;
        this.taskEndDate = t.taskEndDate;
        this.author = t.author;
        this.isActive = t.isActive;
        this.respUser = t.respUser;
    }
    public TaskEntity(TaskDTO t,UserEntity user){
        this.taskType = t.getTaskType();
        this.taskStartDate = t.getTaskStartDate();
        this.taskEndDate = t.getTaskEndDate();
        this.author = user;
        this.isActive = "1";
        this.respUser ="";
        this.taskDetails = t.getTaskDetails();
        this.taskDrug = t.getTaskDrug();
        this.taskTime = t.getTaskTime();
    }
}

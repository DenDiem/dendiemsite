package com.example.dendiemsite.Model;

import com.example.dendiemsite.Entity.TaskEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DateDTO {
    private String dayName;
    private String Date;
    private List<TaskEntity> taskEntity;

    public DateDTO(String dayName, String date, List<TaskEntity> taskEntity) {
        this.dayName = dayName;
        Date = date;
        this.taskEntity = taskEntity;
    }
}

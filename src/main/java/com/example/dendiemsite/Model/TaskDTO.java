package com.example.dendiemsite.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@ToString
public class TaskDTO {
    private String taskStartDate;
    private String taskEndDate;
    private String taskDrug;
    private String taskType;
    private String taskDetails;
    private Integer taskUserId;
    private String taskTime;
}

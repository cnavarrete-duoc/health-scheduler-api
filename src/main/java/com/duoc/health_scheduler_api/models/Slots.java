package com.duoc.health_scheduler_api.models;

import java.util.Date;

import com.duoc.health_scheduler_api.utils.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slots {
    private int id;
    private Date date;
    private String time;
    private Status status;
}

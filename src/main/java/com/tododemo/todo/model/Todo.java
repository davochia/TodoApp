package com.tododemo.todo.model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name="Todo")
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes="Unique auto generated identifier for the system")
    private UUID id;

    @NotNull
    @ApiModelProperty(notes="Todo name")
    private String TodoName;

    @NotNull
    @ApiModelProperty(notes="Todo description")
    private String description;

    @ApiModelProperty(notes="End date ")
    private LocalDateTime endDate;

    @ApiModelProperty(notes="Return true or false if todo dateTime equals to current dateTime ")
    private Boolean complete;

}

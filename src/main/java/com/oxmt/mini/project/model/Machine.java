package com.oxmt.mini.project.model;

import java.beans.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Represents a Machine object.
 *
 */
@Data
@ToString
@Builder
public class Machine
{
    private String id;
    private String model;
    private String age;

    @JsonIgnore
    private Integer ageInDays;
}

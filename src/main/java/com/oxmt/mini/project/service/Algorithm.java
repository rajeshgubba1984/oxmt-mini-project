package com.oxmt.mini.project.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.oxmt.mini.project.model.Machine;

import lombok.extern.slf4j.Slf4j;

/**
 * Please add your description here.
 *
 */
@Slf4j
public class Algorithm
{

    public static List<Integer> interQuartileMethodIntegers(List<Integer> input) {

        List<Integer> sorted =  input.stream().sorted().collect( Collectors.toList());

        var q1 = sorted.get( ( int ) Math.floor((sorted.size() / 4)) );
        var q3 = sorted.get( ( int ) Math.ceil((sorted.size() * (3 / 4.0))) );
        var iqr = q3 - q1;

        var maxValue = q3 + iqr*1.5;
        var minValue = q1 - iqr*1.5;

        var filteredValues = sorted.stream().filter(x -> (x >= maxValue) || (x <= minValue)).collect( Collectors.toList());

        return filteredValues;
    }

    public static List<Machine> interQuartileMethod(List<Machine> input) {

        List<Machine> sorted =  input.stream().sorted( Comparator.comparing( Machine::getAgeInDays ) ).collect( Collectors.toList());

        var q1 = sorted.get( ( int ) Math.floor((sorted.size() / 4.0)) ).getAgeInDays();
        var q3 = sorted.get( ( int ) Math.ceil((sorted.size() * (3 / 4.0))) ).getAgeInDays();
        var iqr = q3 - q1;

        log.info( " -- " + iqr );

        var maxValue = q3 + iqr * 1.5;
        var minValue = q1 - iqr * 1.5;

        log.info( "maxValue: " + maxValue );
        log.info( "minValue: " + minValue );

        return sorted.stream().filter(x -> (x.getAgeInDays() >= maxValue) || (x.getAgeInDays() <= minValue)).collect( Collectors.toList());
    }

    public static List<Machine> zScoreMethod(List<Machine> input) {
        //TODO
        return null;
    }
}

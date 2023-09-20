package com.oxmt.mini.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oxmt.mini.project.exceptions.InvalidInputException;
import com.oxmt.mini.project.model.Machine;
import com.oxmt.mini.project.utils.AgeUtil;

import lombok.extern.log4j.Log4j2;

/**
 * Implementation of machine service.
 *
 */

@Service
@Log4j2
public class MachineValidatorImpl implements MachineValidator
{

    @Override
    public List<Machine> runOutlierAnalysis( final List<Machine> input )
    {
        if(input == null || input.isEmpty()) {
            throw new InvalidInputException("Input must be json array of machines!");
        }

        // Convert Age to Days
        input.forEach( e -> e.setAgeInDays(AgeUtil.getDays( e.getAge() )));
        log.info( input );
        System.out.println(Algorithm.interQuartileMethodIntegers( List.of(1,2,3,500,400,450,350,100000) ));
        List<Machine> outliers = Algorithm.interQuartileMethod( input );
        log.info(outliers);
        return outliers;
    }
}

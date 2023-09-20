package com.oxmt.mini.project.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oxmt.mini.project.exceptions.InvalidInputException;
import com.oxmt.mini.project.model.Machine;

/**
 * Please add your description here.
 *
 */
@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class)
public class MachineValidatorTest
{

    @Autowired
    MachineValidator machineValidator;

    @Test
    @Order(1)
    void testEmpty() {
        Assertions.assertThrows( InvalidInputException.class, () ->  machineValidator.runOutlierAnalysis( List.of()));
    }

    @Test
    @Order(2)
    void testNull() {
        Assertions.assertThrows( InvalidInputException.class, () ->  machineValidator.runOutlierAnalysis( null ));
    }

    @Test
    @Order(3)
    void testDataSet1() {

        List<Machine> list = new ArrayList<>();
        list.add( Machine.builder().id( "1" ).age( "1 day" ).model( "ABC4" ).build() );
        list.add( Machine.builder().id( "2" ).age( "2 day" ).model( "ABC1" ).build() );
        list.add( Machine.builder().id( "3" ).age( "4 days" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "3" ).age( "400 days" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "3" ).age( "500 days" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "3" ).age( "400 days" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "3" ).age( "350 days" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "4" ).age( "100000 days" ).model( "ABC3" ).build() );

        List<Machine> result = machineValidator.runOutlierAnalysis( list );
        Assertions.assertNotNull( result );
        Assertions.assertEquals( 1, result.size() );
        Assertions.assertEquals( "ABC3", result.get( 0 ).getModel() );
    }

    @Test
    @Order(4)
    void testDataSet2() {

        List<Machine> list = new ArrayList<>();
        list.add( Machine.builder().id( "1" ).age( "1 day" ).model( "ABC4" ).build() );
        list.add( Machine.builder().id( "2" ).age( "2 day" ).model( "ABC1" ).build() );
        list.add( Machine.builder().id( "3" ).age( "4 days" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "3" ).age( "400 days" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "3" ).age( "1 year, 8 months" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "3" ).age( "1 year, 3 months" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "3" ).age( "1 year" ).model( "ABC2" ).build() );
        list.add( Machine.builder().id( "4" ).age( "300 years" ).model( "ABC3" ).build() );

        List<Machine> result = machineValidator.runOutlierAnalysis( list );
        Assertions.assertNotNull( result );
        Assertions.assertEquals( 1, result.size() );
        Assertions.assertEquals( "ABC3", result.get( 0 ).getModel() );
    }


}

package com.oxmt.mini.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oxmt.mini.project.model.Machine;
import com.oxmt.mini.project.service.MachineValidator;

/**
 * Controller for machine validation requests.
 *
 */
@RestController
@RequestMapping("/api")
public class MachineOutlierController
{

    private MachineValidator machineValidator;

    public MachineOutlierController( MachineValidator machineValidator ) {
        this.machineValidator = machineValidator;
    }

    @PostMapping("/machine/validate")
    public @ResponseBody ResponseEntity<List<Machine>> detect(@RequestBody final List<Machine> input) {
        return new ResponseEntity<>(machineValidator.runOutlierAnalysis(input), HttpStatus.OK);
    }
}

package com.oxmt.mini.project.service;

import java.util.List;

import com.oxmt.mini.project.model.Machine;

/**
 * Interface for Validation Service.
 *
 */

public interface MachineValidator
{

    List<Machine> runOutlierAnalysis(List<Machine> input);

}

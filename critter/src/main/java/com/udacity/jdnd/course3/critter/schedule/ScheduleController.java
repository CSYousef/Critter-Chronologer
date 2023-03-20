package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.Service.scheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    scheduleService scheduleSer;
    @PutMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
       return scheduleSer.createSchedule(scheduleDTO);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

       return scheduleSer.getAllSchedules();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        return scheduleSer.getScheduleForPet(petId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleSer.getScheduleForEmployee(employeeId);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleSer.getScheduleForCustomer(customerId);
    }
}

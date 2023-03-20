package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Service.customerService;
import com.udacity.jdnd.course3.critter.Service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private customerService customerSer;
    @Autowired
    private employeeService employeeSer;
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        return customerSer.SaveCustomer(customerDTO);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

      return customerSer.GetAllCustomers();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return customerSer.GetOwnerByPet(petId);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        return employeeSer.saveEmployee(employeeDTO);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

       return employeeSer.getEmployee(employeeId);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
      employeeSer.setAvailability(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return employeeSer.findEmployeesForService(employeeDTO);
    }

}

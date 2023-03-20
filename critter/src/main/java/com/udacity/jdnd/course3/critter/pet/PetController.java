package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Service.customerService;
import com.udacity.jdnd.course3.critter.Service.petService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private petService petSer;
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        return petSer.savePet(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        return petSer.getPet(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){

        return petSer.getPets();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return petSer.getPetsByOwner(ownerId);
    }
}

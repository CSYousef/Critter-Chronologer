package com.udacity.jdnd.course3.critter.Repository;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface petRepo extends JpaRepository<PetEntity, Long> {

}

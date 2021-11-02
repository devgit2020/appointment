package org.test.car.appointment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.test.car.appointment.entity.AppointmentEntity;
 
@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long>{
	
	@Query("FROM AppointmentEntity appointment WHERE appointment.name = ?1 and date=?2 and time=?3 and model=?4")
	Optional<AppointmentEntity> findByAppointment(String name ,String date ,Double time , String model);
	
	 
}

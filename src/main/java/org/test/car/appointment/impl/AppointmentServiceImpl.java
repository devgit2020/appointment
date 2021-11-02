package org.test.car.appointment.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.car.appointment.entity.AppointmentEntity;
import org.test.car.appointment.model.Appointment;
import org.test.car.appointment.repository.AppointmentRepository;
import org.test.car.appointment.service.AppointmentService;

/**
 * @author Santosh Behera
 * @param <appointmentRepository>
 *
 */
@Service
public class AppointmentServiceImpl<appointmentRepository> implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	/**
	 * This method do a new booking.
	 * 
	 * Prior to do a new booking it checks below condition
	 * 
	 * The booking date should be in between Monday to Saturday
	 * 
	 * The booking time should be in between 9 AM to 7 PM
	 * 
	 * There is should not be any prior booking on the same model on same date slot
	 * 
	 *  
	 * 
	 */
	
	public Appointment doAppointment(Appointment appointment) throws Exception{
		
		System.out.println("Date "+appointment.getDate());
		System.out.println("Time "+appointment.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(appointment.getDate());
		
		
		Calendar calender = new GregorianCalendar();
		calender.setTime(date);
		
		if( calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			appointment.setConfirmMsg("There will be no appointment in Sunday!! Please try in Monday to  Saturday.");
			return appointment;
		}
		double appointmentTime =Double.valueOf(appointment.getTime().replace(":", "."));
                
        if(appointmentTime < 9 || appointmentTime > 19) {
        	appointment.setConfirmMsg("There will be no appointment at this momement!! Please try from 9 AM to 7 PM. ");
			return appointment;
       }
		

		Optional<AppointmentEntity> existingAppointment = appointmentRepository.findByAppointment(appointment.getName(),
				appointment.getDate(), appointmentTime, appointment.getModel());

		if (existingAppointment.isPresent()) {
			appointment.setConfirmMsg("There is an appointment already created for this slot and model.");
		} else {

			AppointmentEntity entity = new AppointmentEntity();
			entity.setName(appointment.getName());
			entity.setDate(appointment.getDate());
			entity.setTime(appointmentTime);
			entity.setModel(appointment.getModel());

			AppointmentEntity confirmApp = appointmentRepository.saveAndFlush(entity);

			if (confirmApp != null && confirmApp.getId() > 0) {
				appointment.setConfirmMsg("Appointment Confirmed !!");
			}

		}

		return appointment;
	}

}

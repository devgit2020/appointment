package org.test.car.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.test.car.appointment.model.Appointment;
import org.test.car.appointment.service.AppointmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Santosh Behera
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class AppointmentRestController {

	@Autowired
	private AppointmentService appointmentService;

	@ApiOperation(value = "New Booking", notes = "This method used to call from client to do a appointment.", nickname = "doAppointment")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Appointment Confirmed !!", response = String.class, responseContainer = "New Appointment") })
	
	
	@RequestMapping(value = "/api/appointment", method = RequestMethod.POST)
    @ResponseBody
	public String doAppointment(@RequestBody Appointment appointment) throws Exception {
		
		appointment = appointmentService.doAppointment(appointment);
		return appointment.getConfirmMsg();

	}
}

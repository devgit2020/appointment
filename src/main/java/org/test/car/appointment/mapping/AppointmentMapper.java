package org.test.car.appointment.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.test.car.appointment.model.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
	
	AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);
	
	public abstract Appointment objectToLiceseInfo(Object obj);

}

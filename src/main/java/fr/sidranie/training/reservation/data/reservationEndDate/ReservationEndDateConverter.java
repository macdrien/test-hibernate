package fr.sidranie.training.reservation.data.reservationEndDate;

import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ReservationEndDateConverter implements AttributeConverter<ReservationEndDate, LocalDate> {
    @Override
    public LocalDate convertToDatabaseColumn(ReservationEndDate attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public ReservationEndDate convertToEntityAttribute(LocalDate dbData) {
        if (dbData == null) {
            return null;
        }
        return new ReservationEndDate(dbData);
    }
}

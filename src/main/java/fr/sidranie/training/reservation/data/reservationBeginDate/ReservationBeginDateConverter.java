package fr.sidranie.training.reservation.data.reservationBeginDate;

import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ReservationBeginDateConverter implements AttributeConverter<ReservationBeginDate, LocalDate> {
    @Override
    public LocalDate convertToDatabaseColumn(ReservationBeginDate attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public ReservationBeginDate convertToEntityAttribute(LocalDate dbData) {
        if (dbData == null) {
            return null;
        }
        return new ReservationBeginDate(dbData);
    }
}

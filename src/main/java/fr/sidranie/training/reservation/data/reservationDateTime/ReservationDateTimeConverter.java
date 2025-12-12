package fr.sidranie.training.reservation.data.reservationDateTime;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ReservationDateTimeConverter implements AttributeConverter<ReservationDateTime, LocalDateTime> {
    @Override
    public LocalDateTime convertToDatabaseColumn(ReservationDateTime attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public ReservationDateTime convertToEntityAttribute(LocalDateTime dbData) {
        if (dbData == null) {
            return null;
        }
        return new ReservationDateTime(dbData);
    }
}

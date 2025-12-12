package fr.sidranie.training.room.data.roomName;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoomNameConverter implements AttributeConverter<RoomName, String> {
    @Override
    public String convertToDatabaseColumn(RoomName attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public RoomName convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return new RoomName(dbData);
    }
}

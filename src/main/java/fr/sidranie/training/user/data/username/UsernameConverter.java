package fr.sidranie.training.user.data.username;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UsernameConverter implements AttributeConverter<Username, String> {
    @Override
    public String convertToDatabaseColumn(Username username) {
        if (username == null) {
            return null;
        }
        return username.getValue();
    }

    @Override
    public Username convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return new Username(dbData);
    }
}

package fr.sidranie.training.user.data.firstName;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FirstNameConverter implements AttributeConverter<FirstName, String> {
    @Override
    public String convertToDatabaseColumn(FirstName firstName) {
        if (firstName == null) {
            return null;
        }
        return firstName.getValue();
    }

    @Override
    public FirstName convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return new FirstName(dbData);
    }
}

package fr.sidranie.training.user.data.lastName;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LastNameConverter implements AttributeConverter<LastName, String> {
    @Override
    public String convertToDatabaseColumn(LastName lastName) {
        if (lastName == null) {
            return null;
        }
        return lastName.getValue();
    }

    @Override
    public LastName convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return new LastName(dbData);
    }
}

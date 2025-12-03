package fr.sidranie.training.user.data.password;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PasswordConverter implements AttributeConverter<Password, String> {
    @Override
    public String convertToDatabaseColumn(Password password) {
        if (password == null) {
            return null;
        }
        return password.getValue();
    }

    @Override
    public Password convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return new Password(dbData);
    }
}

package Model.Validator;

public class IncognitoSuitValidator implements SuitValidator {
    @Override
    public boolean isDurable(int durability) {
        int lastDigit = durability % 10;
        return lastDigit != 3 && lastDigit != 7;
    }
}

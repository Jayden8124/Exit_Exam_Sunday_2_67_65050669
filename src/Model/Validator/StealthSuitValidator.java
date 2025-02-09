package Model.Validator;

public class StealthSuitValidator implements SuitValidator {
    @Override
    public boolean isDurable(int durability) {
        return durability >= 50;
    }
}

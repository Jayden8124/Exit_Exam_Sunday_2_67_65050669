package Model.Validator;

public class PowerfulSuitValidator implements SuitValidator {
    @Override
    public boolean isDurable(int durability) {
        return durability >= 70;
    }
}

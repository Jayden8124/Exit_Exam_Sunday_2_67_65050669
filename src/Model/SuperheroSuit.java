package Model;

import Model.Validator.SuitValidator;
import Model.Validator.PowerfulSuitValidator;
import Model.Validator.StealthSuitValidator;
import Model.Validator.IncognitoSuitValidator;

public class SuperheroSuit {
    private String code;       
    private String type;       
    private int durability;    
    private SuitValidator validator; 

    public SuperheroSuit(String code, String type, int durability) {
        this.code = code;
        this.type = type;
        this.durability = durability;
        this.validator = createValidator(type);
    }
    
    private SuitValidator createValidator(String type) {
        switch (type) {
            case "ชุดทรงพลัง":
                return new PowerfulSuitValidator();
            case "ชุดลอบเร้น":
                return new StealthSuitValidator();
            case "ชุดปกปิดตัวตน":
                return new IncognitoSuitValidator();
            default:
                throw new IllegalArgumentException("Unknown suit type: " + type);
        }
    }
    
    // Getter & Setter
    public String getCode() {
        return code;
    }
    
    public String getType() {
        return type;
    }
    
    public int getDurability() {
        return durability;
    }
    
    public void setDurability(int durability) {
        this.durability = durability;
    }
    
    // Add Durability
    public void repair() {
        this.durability = Math.min(100, this.durability + 25);
    }
    
    // Check True for Durability
    public boolean isDurabilityValid() {
        return validator.isDurable(durability);
    }
    
    // Format CSV
    @Override
    public String toString() {
        return code + "," + type + "," + durability;
    }
}

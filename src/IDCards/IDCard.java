package IDCards;

/**
 * interface, ktory implementuju triedy id preukazov
 * triedy id preukazov predstavuju druhu hierarchiu
 */

public interface IDCard {
    void setIDCardNumber(String IDCardNumber);
    String getIDCardNumber();
    Boolean getIdValidity();
    void setIdValidity(Boolean idValidity);
}

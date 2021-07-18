package IDCards;

/**
 * trieda, ktora predstavuje ztp preukaz
 */
public class ZTPCard implements IDCard{
    private String IDCardNumber;
    private Boolean idValidity;

    /**
     * nastavi cislo preukazu
     * @param IDCardNumber cislo preukazu
     */
    @Override
    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
    }

    /**
     * metoda, ktora vrati cislo preukazu
     * @return vrati cislo preukazu
     */
    @Override
    public String getIDCardNumber() {
        return IDCardNumber;
    }

    /**
     * metoda vrati overenost preukazu
     * @return vrati overenost preukazu
     */
    @Override
    public Boolean getIdValidity() {
        return idValidity;
    }

    /**
     * metoda nastavi overenost preukzau
     * @param idValidity overenost preukazu
     */
    @Override
    public void setIdValidity(Boolean idValidity) {
        this.idValidity = idValidity;
    }
}

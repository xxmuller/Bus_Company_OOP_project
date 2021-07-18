package IDCards;

/**
 * tato trieda predstavuje id preukaz typu ISIC, ktory maju studenti
 */

public class ISIC implements IDCard{
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

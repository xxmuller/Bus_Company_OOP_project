package Users;

import IDCards.*;

/**
 * Tato trieda reprezentuje uzivatela typu "dieta"
 */

public class Child implements User
{
    private double ticketPrice;
    private double cardCredit;
    private IDCard idCard;

    /**
     * kedze uziatel je dieta, tak nastavi type preukazu na null
     */
    public void setTypeofIDCard(){
        idCard = null;
    }

    /**
     * @return metoda vrati typ id preukazu
     */
    public IDCard getTypeofIDCard(){
        return idCard;
    }

    /**
     * nastavi cenu listka pre pouzivatela
     * @param price cena listka
     */
    @Override
    public void setTicketPrice(double price) {
        this.ticketPrice = price;
    }

    /**
     * @return metoda vrati cenu listka
     */
    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * nastavi kredit na karte uzivatela tak,
     * ze ku kreditu, ktory uz uzivatel ma na karte pricita dalsi,
     * ktory metoda dostane ako parameter
     * @param cardCredit kredit, ktory pripocita k aktualnemu kreditu
     */
    @Override
    public void setCardCredit(double cardCredit) {
        this.cardCredit += cardCredit;
    }

    /**
     * @return metoda vrati kredit uzivatela
     */
    @Override
    public double getCardCredit() {
        return cardCredit;
    }

    /**
     *metoda, ktora je sucastou design patternu visitor
     * pomocou tejto metody visitor visitne tuto triedu
     * @param visitor objekt typu Discount
     */
    @Override
    public void accept(Discount visitor) {
        visitor.visit(this);
    }
}

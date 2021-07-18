package Users;
import IDCards.IDCard;

/**
 * interface, ktory implementuju triedy uzivatelov
 * uzivatelia predstavuju hlavnu hierarchiu
 */

public interface User
{
    void setTicketPrice(double ticketPrice);
    double getTicketPrice();
    void setCardCredit(double cardCredit);
    void setTypeofIDCard();
    IDCard getTypeofIDCard();
    double getCardCredit();
    void accept(Discount visitor);
}

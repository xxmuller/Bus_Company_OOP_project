package Users;

import gui.BuyTicketsController;

import java.sql.SQLException;

/**
 * trieda, ktora vyuziva design pattern - visitor
 * zakazdym, ako "navstivi" daneho uzivatela tak mu nastavi zlavu
 * podla toho aky je to typ uzivatela
 */
public class TicketDiscount implements Discount
{
    double discount;

    /**
     * ked visitor navstivi triedu Adult, tak nastavi zlavu na 10%
     * @param adult objekt typu Adult
     */
    @Override
    public void visit(Adult adult) {
        discount = 0.9;
    }

    /**
     * ked visitor navstivi triedu Child, tak nastavi zlavu na 100%
     * @param child objekt typu Child
     */
    @Override
    public void visit(Child child) {
        discount = 0;
    }

    /**
     * ked visitor navstivi triedu Pensioner, tak nastavi zlavu na 30%
     * @param pensioner objekt typu Pensioner
     */
    @Override
    public void visit(Pensioner pensioner) {
        discount = 0.7;
    }

    /**
     * ked visitor navstivi triedu Student, tak nastavi zlavu na 50%
     * @param student objekt typu Student
     */
    @Override
    public void visit(Student student) {
        discount = 0.5;
    }

    /**
     * ked visitor navstivi triedu ZTP, tak nastavi zlavu na 40%
     * @param ztp objekt typu ZTP
     */
    @Override
    public void visit(ZTP ztp) {
        discount = 0.6;
    }

    //ak visitor visitne BuyTicketsController, tak v nom nastavi zlavu, ktoru ziskalo z visitnutia nejakeho usera

    /**
     * ak visitor visitne triedu BuyTicketsController, tak v nom nastavi zlavu,
     * ktoru ziskalo z visitnutia nejakeho usera
     * @param controller objekt typu BuyTicketsController
     * @throws SQLException vynimka, ktora je potrebna pri praci s sql databazou
     */
    @Override
    public void visit(BuyTicketsController controller) throws SQLException {
        controller.setDiscount(discount);
    }

}

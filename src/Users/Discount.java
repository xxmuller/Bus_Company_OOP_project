package Users;

import gui.BuyTicketsController;
import gui.UserHomePageController;

import java.sql.SQLException;

public interface Discount
{
    void visit(Adult adult);
    void visit(Child child);
    void visit(Pensioner pensioner);
    void visit(Student student);
    void visit(ZTP ztp);
    void visit(BuyTicketsController buyTicketsController) throws SQLException;
}

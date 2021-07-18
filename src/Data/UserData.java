package Data;
import java.sql.*;

/**
 * trieda v ktorej pracujem s databazou
 */
public class UserData {
    /**
     * metoda na pridavanie uzivatelov do databazy pri registracii
     * @param fname krstne meno uzivatela
     * @param lname priezvisko uzivatela
     * @param email email uzivatela
     * @param dateOfBirth datum narodenia uzivatela
     * @param category kategoria uzivatela
     * @param username prihlasovacie meno uzivatela
     * @param password heslo uzivatela
     * @param idCard cislo is preukazu uzivatela
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public void addUser(String fname, String lname, String email, String dateOfBirth,
                        String category, String username, String password, String idCard) throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();

        statement.executeUpdate("INSERT INTO users (fName, lName, email, dateOfBirth, category, username, password, credit, idCard, idCardValidity)" +
                "VALUES ('"+fname+"', '"+lname+"', '"+email+"', '"+dateOfBirth+"'" +
                ", '"+category+"', '"+username+"', '"+password+"', '0', '"+idCard+"', 0)");

        statement.close();
        conn.close();
    }

    /**
     * metoda, ktora vrati true ked je prihlasovacie meno a heslo z tabulky userov v db
     * @param username prihlasovacie meno uzivatela
     * @param password heslo uzivatela
     * @return metoda vracia true alebo false
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public boolean checkUser(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.execute("SELECT * FROM users");
        ResultSet results = statement.getResultSet();
        boolean value = false;

        while(results.next()){
            if((results.getString("username").equals(username)))
            {
                if((results.getString("password").equals(password)))
                    value = true;
            }
        }
        statement.close();
        conn.close();
        return value;
    }

    /**
     * metoda, ktora vrati true ked je pouzivatelske meno a heslo z tabulky adminov v db
     * @param username prihlasovacie meno admina
     * @param password heslo admina
     * @return metoda vracia true alebo false
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public boolean checkAdmin(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.execute("SELECT * FROM admins");
        ResultSet results = statement.getResultSet();
        boolean value = false;

        while(results.next()){
            if((results.getString("username").equals(username)))
            {
                if((results.getString("password").equals(password)))
                    value = true;
            }
        }
        results.close();
        statement.close();
        conn.close();
        return value;
    }

    /**
     * metoda, ktorej zadam prihlasovacie meno uzivatela a informaciu, ktoru chcem
     * a vrati mi danu informaciu z tabulky userov v db ako string
     * @param username prihlasovacie meno uzivatela
     * @param info informacia o pouzivatelovi, ktoru chcem dostat z tabulky
     * @return metoda vrati info o puzivatelovi ako string
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public String getUserInfo(String username, String info) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.execute("SELECT * FROM users");
        ResultSet results = statement.getResultSet();
        String userInfo = null;

        while(results.next()){
            if((results.getString("username").equals(username)))
            {
                switch(info){
                    case "name":
                        userInfo = results.getString("fName") + " " + results.getString("lName");
                        break;
                    case "email":
                        userInfo = results.getString("email");
                        break;
                    case "birthdate":
                        userInfo = results.getString("dateOfBirth");
                        break;
                    case "category":
                        userInfo = results.getString("category");
                        break;
                    case "idCard":
                        userInfo = results.getString("idCard");
                        break;
                    case "idCardValidity":
                        userInfo = results.getString("idCardValidity");
                        break;
                }
            }
        }

        statement.close();
        conn.close();
        return userInfo;
    }

    /**
     * metoda, ktora mi vrati informaciu, ktoru chcem z tabulky adminov v db
     * @param username prihlasovacie meno admina
     * @param info informacia o adminovi, ktoru chcem dostat z tabulky
     * @return metoda vrati info o adminovi ako string
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public String getAdminInfo(String username, String info) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.execute("SELECT * FROM admins");
        ResultSet results = statement.getResultSet();
        String adminInfo = null;

        while(results.next()){
            if((results.getString("username").equals(username)))
            {
                switch(info){
                    case "name":
                        adminInfo = results.getString("fName") + " " + results.getString("lName");
                        break;
                    case "email":
                        adminInfo = results.getString("email");
                        break;
                    case "birthdate":
                        adminInfo = results.getString("dateOfBirth");
                        break;
                    case "category":
                        adminInfo = results.getString("category");
                        break;
                }
            }
        }

        statement.close();
        conn.close();
        return adminInfo;
    }

    /**
     * metoda, ktora prida listok do tabulky ticekts v db spolu s menom uzivatela aj s jeho zlavou
     * @param username prihalsovacie meno uzivatela
     * @param ticket typ listka
     * @param discount zlava dana uzivatelovi
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public void addTicket(String username, String ticket, double discount) throws SQLException {
        String name;
        name = getUserInfo(username, "name");

        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO tickets (name, ticketType, discount)" +
                "VALUES ('"+name+"', '"+ticket+"', '"+discount+"%')");
        statement.close();
        conn.close();
    }

    /**
     * metoda, ktora zisti pocet listkov jedneho typu patriacich danemu uzivatelovi
     * @param ticketType typ listka
     * @param username prihlasovacie meno uzivatela
     * @return metoda vrati pocet listkov
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public int getTicketAmount(String ticketType, String username) throws SQLException {
        int amount = 0;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.execute("SELECT * FROM tickets");
        ResultSet results = statement.getResultSet();

        while(results.next()){
            if(results.getString("ticketType").equals(ticketType))
                if(results.getString("name").equals(username))
                    amount++;
        }
        statement.close();
        conn.close();
        return amount;
    }

    /**
     * metoda, ktora vrati kredit daneho pouzivatela
     * @param username prihlasovacie meno uzivatela
     * @return metoda vrati kredit ako double
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public double getCredit(String username) throws SQLException {
        double credit = 0;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();
        statement.execute("SELECT * FROM users");
        ResultSet results = statement.getResultSet();

        while(results.next()){
            if(results.getString("username").equals(username))
                    credit = results.getDouble("credit");
        }
        statement.close();
        conn.close();
        return credit;
    }

    /**
     * metoda, ktora aktualizuje pouzivatelov kredit v db
     * @param credit novy kredit uzivatela, ktory sa zapise do db
     * @param username prihalsovacie meno uzivatela
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public void updateCredit(double credit, String username) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();

        statement.executeUpdate("UPDATE users SET credit = '"+credit+"' WHERE username = '"+username+"'");

        statement.close();
        conn.close();
    }

    /**
     * metoda, ktora aktualizuje potvrdenie preukazu pouzivatela v db
     * @param username prihalsovacie meno uzivatela
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public void updateIDCard(String username) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();

        statement.executeUpdate("UPDATE users SET idCardValidity = '1' WHERE username = '"+username+"'");

        statement.close();
        conn.close();
    }

    /**
     * metoda, ktora vymaze pouzivatela z db
     * @param username prihalsovacei meno uzivatela
     * @param table tabulka v databaze, z ktorej chcem zmazat uzivatela
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public void deleteUser(String username, String table) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();

        statement.executeUpdate("DELETE FROM '"+table+"' WHERE username = '"+username+"'");

        statement.close();
        conn.close();
    }

    /**
     * metoda, ktora zmeni danu pouzivatelovu informaciu na novu v db
     * @param username prihlasovacie meno uzivatela
     * @param infoToChange informacia, ktoru chcem uzivatelovi zmenit
     * @param newValue nova hodnota danej informacie
     * @param table tabulka do ktorej chcem zmeny zapisat
     * @throws SQLException vynimka, ktora je potrebna pri praci s databazou
     */
    public void updateUserInfo(String username, String infoToChange, String newValue, String table) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:BestBusData.db");
        Statement statement = conn.createStatement();

        statement.executeUpdate("UPDATE '"+table+"' SET '"+infoToChange+"' = '"+newValue+"' WHERE username = '"+username+"'");

        statement.close();
        conn.close();
    }
}
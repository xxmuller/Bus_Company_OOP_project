package Users;

/**
 * Trieda, z ktorej ziskavam udaje o uzivateloch do tabulky
 */
public class UserInfo {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String category;
    private String userName;
    private String password;
    private String idCard;

    /**
     * konstruktor triedy
     * @param firstName string, ktory predstavuje krstne meno uzivatela
     * @param lastName string, ktory predstavuje priezvisko uzivatela
     * @param dateOfBirth string, ktory predstavuje datum narodenia uzivatela
     * @param category string, ktory predstavuje kategoriu uzivatela
     * @param userName string, ktory predstavuje pouzivatelske meno uzivatela
     * @param password string, ktory predstavuje heslo uzivatela
     * @param idCard string, ktory predstavuje cislo id preukazu uzivatela
     */
    public UserInfo(String firstName, String lastName, String dateOfBirth,
                    String category, String userName, String password, String idCard)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.category = category;
        this.userName = userName;
        this.password = password;
        this.idCard = idCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCategory() {
        return category;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getIdCard() {
        return idCard;
    }
}

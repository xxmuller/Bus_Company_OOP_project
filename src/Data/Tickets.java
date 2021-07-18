package Data;

/**
 *trieda, z ktorej ziskavam udaje do tabulky zakupenych cestovnych listkov
 */
public class Tickets {
    private String name;
    private String ticketType;
    private String discount;

    public Tickets(String name, String ticketType, String discount)
    {
        this.name = name;
        this.ticketType = ticketType;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getDiscount() {
        return discount;
    }
}

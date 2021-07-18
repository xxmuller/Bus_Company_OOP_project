package Admins;

/**
 * trieda, ktora predstavuje admina typu "sekretarka"
 */
public class Secretary extends Admin{
    /**
     * metoda, kde zistujem typ preukazu pouzivatela podla formatu stringu, ktory dostane ako parameter
     * @param idCard cislo preukazu uzivatela vo forme stringu
     * @param category kategoria uzivatela
     * @return metoda vrati typ id preukazu uzivatela
     */
    public String getTypeofIDCard(String idCard, String category){
        int cardLength = idCard.length();

        //obciansky ma najprv dve pismena a potom 6 cisel
        if(cardLength == 8 && (category.equals("adult") || category.equals("pension")))
            for(int i = 2; i<cardLength; i++){
                if(idCard.charAt(0) >= 'A' && idCard.charAt(0) <= 'Z'){
                    if(idCard.charAt(1) >= 'A' && idCard.charAt(1) <= 'Z')
                        if(idCard.charAt(i) >= '0' && idCard.charAt(i) <= '9'){
                            return "ID Card";
                        }
                }
            }

        //isic ma 5 cisel
        if(cardLength == 5 && category.equals("student"))
            for(int i = 0; i<cardLength; i++){
                if(idCard.charAt(i) >= '0' && idCard.charAt(i) <= '9'){
                    return "ISIC";
                }
            }

        //ztp preukaz ma najprv ZTP a potom dve cisla
        if(cardLength == 5 && (category.equals("ztp")))
            for(int i = 3; i<cardLength; i++){
                if((idCard.charAt(0) == 'Z') && (idCard.charAt(1) == 'T') && (idCard.charAt(2) == 'P')){
                    if(idCard.charAt(i) >= '0' && idCard.charAt(i) <= '9')
                        return "ZTP license";
                }
            }

        return "None";
    }

}

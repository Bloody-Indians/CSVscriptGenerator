import java.sql.Date;

public class AddressBook {

    private String ID;
    private String NAME;
    private String IBAN;
    private String USER_ID;
    private String CREATED_AT;
    private String UPDATED_AT;


    public AddressBook() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getCREATED_AT() {
        return CREATED_AT;
    }

    public void setCREATED_AT(String CREATED_AT) {
        this.CREATED_AT = CREATED_AT;
    }

    public String getUPDATED_AT() {
        return UPDATED_AT;
    }

    public void setUPDATED_AT(String UPDATED_AT) {
        this.UPDATED_AT = UPDATED_AT;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "ID='" + ID + '\'' +
                ", NAME='" + NAME + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", USER_ID='" + USER_ID + '\'' +
                ", CREATED_AT=" + CREATED_AT +
                ", UPDATED_AT='" + UPDATED_AT + '\'' +
                '}';
    }
}

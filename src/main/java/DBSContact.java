public class DBSContact {

    private String id;
    private String bb_id;
    private String name;
    private String created_at;
    private String status;
    private String uuid;
    private String iban;


    public DBSContact() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBb_id() {
        return bb_id;
    }

    public void setBb_id(String bb_id) {
        this.bb_id = bb_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "DBSContact{" +
                "id='" + id + '\'' +
                ", bb_id='" + bb_id + '\'' +
                ", name='" + name + '\'' +
                ", created_at='" + created_at + '\'' +
                ", status='" + status + '\'' +
                ", uuid='" + uuid + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}

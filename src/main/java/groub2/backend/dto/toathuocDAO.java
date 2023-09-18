package groub2.backend.dto;

public class toathuocDAO {
    private int thuocID;
    private int quantity;
    private String sang;
    private String chieu;
    private String trua;
    private String toi;


    public toathuocDAO(int thuocID, int quantity, String sang, String trua, String chieu, String toi) {
        this.thuocID = thuocID;
        this.quantity = quantity;
        this.sang = sang;
        this.trua = trua;
        this.chieu = chieu;
        this.toi = toi;
    }
    public int getThuocID() {
        return thuocID;
    }

    public void setThuocID(int thuocID) {
        this.thuocID = thuocID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSang() {
        return sang;
    }

    public void setSang(String sang) {
        this.sang = sang;
    }

    public String getChieu() {
        return chieu;
    }

    public void setChieu(String chieu) {
        this.chieu = chieu;
    }

    public String getTrua() {
        return trua;
    }

    public void setTrua(String trua) {
        this.trua = trua;
    }

    public String getToi() {
        return toi;
    }

    public void setToi(String toi) {
        this.toi = toi;
    }
}


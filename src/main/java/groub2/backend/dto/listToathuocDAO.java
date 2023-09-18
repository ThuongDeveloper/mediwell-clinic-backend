package groub2.backend.dto;

import java.util.List;

public class listToathuocDAO {
    private String name;
    private String phone;
    private String address;
    private String symptom;
    private String description;
    private boolean state;
    private List<toathuocDAO> listTT;

    public listToathuocDAO() {
        // Hàm tạo mặc định, không cần truyền tham số
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<toathuocDAO> getListTT() {
        return listTT;
    }

    public void setListTT(List<toathuocDAO> listTT) {
        this.listTT = listTT;
    }
}

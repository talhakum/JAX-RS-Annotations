package restService;


import javax.ws.rs.FormParam;

public class Person {
    @FormParam("name")
    private String name;

    @FormParam("surname")
    private String surname;

    @FormParam("phoneNumber")
    private String phoneNumber;

    @FormParam("adress")
    private String adress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
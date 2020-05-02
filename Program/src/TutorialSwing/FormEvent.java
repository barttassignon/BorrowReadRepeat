/**
 * @Author Bart Tassignon
 */

package TutorialSwing;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private String name;
    private String voornaam;
    private String occupation;
    private int ageCategory;
    private String empCat;
    private String taxId;
    private boolean usCitizen;
    private String gender;


    // constructor
    public FormEvent(Object source) { // source is formpanel
        super(source);
    }

    public FormEvent(Object source, String name, String voornaam, String occupation, int ageCat, String empCat, String taxId, boolean usCitizen, String gender) {
        super(source);
        this.name = name;
        this.voornaam = voornaam;
        this.occupation = occupation;
        this.ageCategory = ageCat;
        this.empCat = empCat;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmpCat() {
        return empCat;
    }

    public void setEmpCat(String empCat) {
        this.empCat = empCat;
    }
}



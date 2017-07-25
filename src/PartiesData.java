/**
 * Created by nandigam on 4/6/17.
 */
public class PartiesData {

    private String nameOfTheParty;
    private String suffixOfTheParty;
    private String address;
    private String cell;
    private String pan;

    public PartiesData(String nameOfTheParty, String suffixOfTheParty, String address, String cell, String pan) {
        this.nameOfTheParty = nameOfTheParty;
        this.suffixOfTheParty = suffixOfTheParty;
        this.address = address;
        this.cell = cell;
        this.pan = pan;
    }

    public String getNameOfTheParty() {

        return nameOfTheParty;
    }

    public void setNameOfTheParty(String nameOfTheParty) {
        this.nameOfTheParty = nameOfTheParty;
    }

    public String getSuffixOfTheParty() {
        return suffixOfTheParty;
    }

    public void setSuffixOfTheParty(String suffixOfTheParty) {
        this.suffixOfTheParty = suffixOfTheParty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}

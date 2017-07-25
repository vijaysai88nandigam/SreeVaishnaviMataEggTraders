import java.util.HashMap;
import java.util.Map;

/**
 * Created by nandigam on 4/6/17.
 */
public class Parties {

    private Map partiesData = new HashMap<Integer,HashMap>();
    private Map details = new HashMap<String,String>();

    public Map getPartiesData() {
        return partiesData;
    }

    public void setPartiesData(Map partiesData) {
        this.partiesData = partiesData;
    }

    public Map getDetails() {
        return details;
    }

    public void setDetails(Map details) {
        this.details = details;
    }

    PartiesData sriRamenGhosh = new PartiesData("Sri Ramen Ghosh", "Egg marchant",
            "purna bazar, Dhanbad, Jharkhand state.","+91 9006777850",null);
    PartiesData kismetEggCentre = new PartiesData("KISMET EGG CENTRE","PROP.SK KISMET ALI",
            "SRIDHARPUR CHOWMOR.NORTH NANDAKUMAR,W.B STATE","+91 9732115800",null);
    PartiesData daniyalEggcentre = new PartiesData("DANIYAL EGG CENTER","prop.Amim Khan",
            "KARGALI BAZAR,p.o-BERMO-829104,PUSURO-BOKARO Dist.JHARKAND","+91 7870375773", null);
    PartiesData universalEggcentre = new PartiesData("UNIVERSAL EGG CENTRE","prop.Muhammad Masoom Ali",
            "REGULATED MARKET,SILIGURI,WEST BENGAL","+919933944770", null);
    PartiesData sufiaEggCentre = new PartiesData("SUFIA EGG CENTRE","PROP.SUFIA SULTANA",
            "SRIDHARPUR CHOWMOR.NORTH NANDAKUMAR,W.B STATE","+91 9732115800", null);

    public void setSriRamenGhoshToHashMap(PartiesData sriRamenGhosh){
        details.clear();
        details.put("nameOfTheParty",sriRamenGhosh.getNameOfTheParty());
        details.put("suffixOfTheParty",sriRamenGhosh.getSuffixOfTheParty());
        details.put("address", sriRamenGhosh.getAddress());
        details.put("cell",sriRamenGhosh.getCell());

        partiesData.put("sriRamenGhosh", details);
    }

    public void setKismetEggCentreToMap(PartiesData kismetEggCentre){
        details.clear();
        details.put("nameOfTheParty",kismetEggCentre.getNameOfTheParty());
        details.put("suffixOfTheParty",kismetEggCentre.getSuffixOfTheParty());
        details.put("address", kismetEggCentre.getAddress());
        details.put("cell",kismetEggCentre.getCell());

        partiesData.put("kismetEggCentre", details);
    }

    public void setDaniyalEggcentreToMap(PartiesData daniyalEggcentre){
        details.clear();
        details.put("nameOfTheParty",daniyalEggcentre.getNameOfTheParty());
        details.put("suffixOfTheParty",daniyalEggcentre.getSuffixOfTheParty());
        details.put("address", daniyalEggcentre.getAddress());
        details.put("cell",daniyalEggcentre.getCell());

        partiesData.put("daniyalEggcentre", details);
    }

    public void setUniversalEggcentreToMap(PartiesData universalEggcentre){
        details.clear();
        details.put("nameOfTheParty",universalEggcentre.getNameOfTheParty());
        details.put("suffixOfTheParty",universalEggcentre.getSuffixOfTheParty());
        details.put("address", universalEggcentre.getAddress());
        details.put("cell",universalEggcentre.getCell());

        partiesData.put("universalEggcentre", details);
    }

    public void setSufiaEggCentreToMap(PartiesData sufiaEggCentre){
        details.clear();
        details.put("nameOfTheParty",sufiaEggCentre.getNameOfTheParty());
        details.put("suffixOfTheParty",sufiaEggCentre.getSuffixOfTheParty());
        details.put("address", sufiaEggCentre.getAddress());
        details.put("cell",sufiaEggCentre.getCell());

        partiesData.put("sufiaEggCentre", details);
    }

    public Parties() {
        this.setDaniyalEggcentreToMap(this.daniyalEggcentre);
        this.setKismetEggCentreToMap(this.kismetEggCentre);
        this.setSriRamenGhoshToHashMap(this.sriRamenGhosh);
        this.setUniversalEggcentreToMap(this.universalEggcentre);
        this.setSufiaEggCentreToMap(this.sufiaEggCentre);
    }
}

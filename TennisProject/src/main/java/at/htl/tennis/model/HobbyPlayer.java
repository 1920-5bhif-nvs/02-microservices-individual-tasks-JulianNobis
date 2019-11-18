package at.htl.tennis.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class HobbyPlayer extends Tennisplayer{
    private Boolean playsHobbycup;

    // region Constructor
    public HobbyPlayer(){}
    public HobbyPlayer(String name, double itn, int year_born, GenderPlayers.Gender sex, int wins, int losses, Boolean playsHobbycup){
        super(name, itn, year_born, sex, wins, losses);
        this.playsHobbycup = playsHobbycup;
    }
    // endregion

    // region Getter & Setter
    public Boolean isPlaysHobbycup() {
        return playsHobbycup;
    }

    public void setPlaysHobbycup(Boolean playsHobbycup) {
        if (this.playsHobbycup != null){
            this.playsHobbycup = playsHobbycup;
        } else{
            this.playsHobbycup = false;
        }
    }
    // endregion
}

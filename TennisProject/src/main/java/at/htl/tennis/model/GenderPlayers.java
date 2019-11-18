package at.htl.tennis.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GenderPlayers {
    public enum Gender{ // muss so heißen, ansonsten gibt es einen error
        MALE,
        FEMALE
    }
}

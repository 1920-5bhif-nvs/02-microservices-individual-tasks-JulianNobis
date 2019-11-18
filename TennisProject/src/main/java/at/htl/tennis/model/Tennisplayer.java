package at.htl.tennis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NamedQueries({
        @NamedQuery(name = "Tennisplayer.findAll", query = "select t from Tennisplayer t"),
        @NamedQuery(name = "Tennisplayer.findByName", query = "select t from Tennisplayer t where t.name like ?1"),
        @NamedQuery(name = "Tennisplayer.findById", query = "select t from Tennisplayer t where t.id = ?1"),
        @NamedQuery(name = "Tennisplayer.getBestPlayer", query = "select t from Tennisplayer t where t.itn = (select min(t2.itn) from Tennisplayer t2)")
})
public class Tennisplayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name; // firstName <blank> lastName
    protected double itn; //ITN = international tennis number(Indikator für die ungefähre Spielstärke des jew. Spielers)
    protected int year_born;
    protected GenderPlayers.Gender sex;
    protected int wins;
    protected int losses;
    @JsonIgnore
    @XmlTransient
    @Column(name="DTYPE", insertable = false, updatable = false)
    private String dType;

    /*@ManyToMany(mappedBy = "tennisplayers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @XmlTransient
    protected List<Tennismatch> tennismatches;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    protected Team team; // um die aufgabe "1:n bidirektional" zu lösen, kann
    // ein spieler in nur 1 mannschaft spielen

    // region Constructor
    public Tennisplayer(){}
    public Tennisplayer(String name, double itn, int year_born, GenderPlayers.Gender sex, int wins, int losses){
        this.name = name;
        this.itn = itn;
        this.year_born = year_born;
        this.sex = sex;
        this.wins = wins;
        this.losses = losses;
    }
    // endregion

    //region Getter & Setter

    public void setId(Long id) {
        this.id = id;
    }

    /*public List<Tennismatch> getTennismatches() {
        return tennismatches;
    }

    public void setTennismatches(List<Tennismatch> tennismatches) {
        this.tennismatches = tennismatches;
    }*/

    public void setTeam(Team newTeam){
        if (this.team != null){
            this.team.removeTeamMember(this);
        }
        this.team = newTeam;
    }

    public Team getTeam(){
        return this.team;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getItn() {
        return itn;
    }

    public void setItn(double itn) {
        this.itn = itn;
    }

    public int getYear_born() {
        return year_born;
    }

    public void setYear_born(int year_born) {
        this.year_born = year_born;
    }

    public GenderPlayers.Gender getSex() {
        return sex;
    }

    public void setSex(GenderPlayers.Gender sex) {
        this.sex = sex;
    }

    public void setWins(int wins) { this.wins = wins; }

    public int getWins() { return this.wins; }

    public int getLosses() { return losses; }

    public void setLosses(int losses) {this.losses = losses; }

    //endregion


    /*public void addTennismatch(Tennismatch tennismatch){
        if (tennismatches == null){
            tennismatches = new ArrayList<>();
        }
        if (!tennismatches.contains(tennismatch)){
            tennismatches.add(tennismatch);
        }
        if (tennismatch.getPlayers().contains(this)){
            tennismatch.addPlayer(this);
        }
    }

    public void removeTennismatch(Tennismatch tennismatch){
        if (tennismatches.contains(tennismatch)){
            tennismatches.remove(tennismatch);
        }
        if (tennismatch.getPlayers().contains(this)){
            tennismatch.getPlayers().remove(this);
        }
    }*/

}

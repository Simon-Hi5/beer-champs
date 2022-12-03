package at.ac.uibk.beerchamps.persistence;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
public class Player implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public Player(String name) {
        this.name = name;
    }
    public Player() {}


    @Override
    @Transient
    public boolean isNew() {
        return false;
    }
    @Override
    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
}

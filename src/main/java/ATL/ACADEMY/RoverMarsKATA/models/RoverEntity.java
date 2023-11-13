package ATL.ACADEMY.RoverMarsKATA.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "rover")
public class RoverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int x;
    private int y;
    private char direction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "map_id")
    private MapEntity map;

    public RoverEntity() {}

    public RoverEntity(int x, int y, char direction, MapEntity map) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.map = map;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
    @JsonIgnore
    public MapEntity getMap() {
        return map;
    }
    @JsonIgnore
    public void setMap(MapEntity map) {
        this.map = map;
    }
}

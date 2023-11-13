package ATL.ACADEMY.RoverMarsKATA.models;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "map")
public class MapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "width", nullable = false)
    private int width;

    @Column(name = "height", nullable = false)
    private int height;

    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL)
    private List<ObstacleEntity> obstacles;

    public MapEntity() {}

    public MapEntity(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int wrapX(int x) {
        if (x < 0) {
            return width + x;
        } else if (x >= width) {
            return x - width;
        }
        return x;
    }

    public int wrapY(int y) {
        if (y < 0) {
            return height + y;
        } else if (y >= height) {
            return y - height;
        }
        return y;
    }

    @JsonIgnore
    public List<ObstacleEntity> getObstacles() {
        return obstacles;
    }
    @JsonIgnore
    public void setObstacles(List<ObstacleEntity> obstacles) {
        this.obstacles = obstacles;
    }
}

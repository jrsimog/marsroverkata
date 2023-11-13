package ATL.ACADEMY.RoverMarsKATA.services;

import ATL.ACADEMY.RoverMarsKATA.models.MapEntity;
import ATL.ACADEMY.RoverMarsKATA.models.ObstacleEntity;
import ATL.ACADEMY.RoverMarsKATA.repositories.IObstacleRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ObstacleService {
    @Autowired
    private IObstacleRepository obstacleRepository;

    @Autowired
    private MapService mapService;

    public List<ObstacleEntity> findByMapId(Long mapId) {
        return obstacleRepository.findByMapId(mapId);
    }

    public Optional<ObstacleEntity> existingObstacle(int x, int y, Long mapId) {
        return obstacleRepository.findByXAndYAndMap_Id(x, y, mapId);
    }
    public ObstacleEntity save(int x, int y, Long mapId) {

        Optional<MapEntity> mapExist = mapService.findById(mapId);
        if (!mapExist.isPresent()) {
            throw new NoSuchElementException("Map is not ready or not exists, please create one");
        }

        if (x == 1 && y == 1) {
            throw new IllegalArgumentException("Obstacle cannot be placed at the starting position of the rover (1,1).");
        }

        MapEntity map = mapExist.get();

        if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) {
            throw new IllegalArgumentException("Obstacle coordinates are out of map bounds");
        }

        Optional<ObstacleEntity> existingObstacle = this.existingObstacle(x, y, mapId);
        if (existingObstacle.isPresent()) {
            throw new EntityExistsException("An obstacle at position already exists for this map.");
        }

        ObstacleEntity obstacle = new ObstacleEntity(x,y, map);
        return obstacleRepository.save(obstacle);
    }
}

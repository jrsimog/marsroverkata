package ATL.ACADEMY.RoverMarsKATA.repositories;

import ATL.ACADEMY.RoverMarsKATA.models.ObstacleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IObstacleRepository extends JpaRepository<ObstacleEntity, Long> {
    Optional<ObstacleEntity> findByXAndYAndMap_Id(int x, int y, Long mapId);
    List<ObstacleEntity> findByMapId(Long mapId);
}

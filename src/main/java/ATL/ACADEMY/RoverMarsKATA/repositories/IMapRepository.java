package ATL.ACADEMY.RoverMarsKATA.repositories;

import ATL.ACADEMY.RoverMarsKATA.models.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMapRepository extends JpaRepository<MapEntity, Long> {
    MapEntity findByWidthAndHeight(int width, int height);
    Optional<MapEntity> findFirstByOrderByIdAsc();
}

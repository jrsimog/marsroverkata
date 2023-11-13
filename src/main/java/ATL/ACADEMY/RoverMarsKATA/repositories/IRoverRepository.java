package ATL.ACADEMY.RoverMarsKATA.repositories;

import ATL.ACADEMY.RoverMarsKATA.models.RoverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoverRepository extends JpaRepository<RoverEntity, Long> {
    Optional<RoverEntity> findFirstByOrderByIdDesc();
}

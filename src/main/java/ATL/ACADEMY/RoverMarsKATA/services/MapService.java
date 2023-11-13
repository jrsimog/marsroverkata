package ATL.ACADEMY.RoverMarsKATA.services;

import ATL.ACADEMY.RoverMarsKATA.models.MapEntity;
import ATL.ACADEMY.RoverMarsKATA.repositories.IMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MapService {

    private static final int DEFAULT_MAP_WIDTH = 10;
    private static final int DEFAULT_MAP_HEIGHT = 10;
    @Autowired
    private IMapRepository mapRepository;

    public MapEntity createDefaultMap() {
        MapEntity map;
        if (mapRepository.count() == 0) {
            map = new MapEntity(DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT);
            map = mapRepository.save(map);
        } else {
            map = mapRepository.findAll().get(0);
        }
        return map;
    }

    public Optional <MapEntity> getLastCreatedMap() {
        return mapRepository.findFirstByOrderByIdAsc();
    }

    public Optional<MapEntity> findById(Long id) {
        return mapRepository.findById(id);
    }
    public MapEntity save(int width, int height) {

        MapEntity existingMap = mapRepository.findByWidthAndHeight(width, height);
        if (existingMap != null) {
            throw new NoSuchElementException("Map is ready");
        }
        MapEntity map = new MapEntity(width,height);
        return mapRepository.save(map);
    }



}

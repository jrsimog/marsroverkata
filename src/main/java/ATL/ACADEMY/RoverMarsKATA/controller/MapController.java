package ATL.ACADEMY.RoverMarsKATA.controller;

import ATL.ACADEMY.RoverMarsKATA.dto.MapDto;
import ATL.ACADEMY.RoverMarsKATA.models.MapEntity;
import ATL.ACADEMY.RoverMarsKATA.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/map")

public class MapController {
    @Autowired
    private MapService mapService;

    @GetMapping
    public Optional<MapEntity> getMap() {

        return mapService.getLastCreatedMap();
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MapDto payload) {
        try {
            MapEntity mapEntity = mapService.save(payload.getWidth(), payload.getHeight());
            return ResponseEntity.ok(mapEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}

package ATL.ACADEMY.RoverMarsKATA.controller;

import ATL.ACADEMY.RoverMarsKATA.dto.ObstacleDto;
import ATL.ACADEMY.RoverMarsKATA.models.ObstacleEntity;
import ATL.ACADEMY.RoverMarsKATA.services.ObstacleService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/obstacle")
public class ObstacleController {
    @Autowired
    private ObstacleService obstacleService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ObstacleDto payload) {
        try {
            ObstacleEntity obstacleEntity = obstacleService.save(payload.getX(), payload.getY(), payload.getMapId());
            return ResponseEntity.ok(obstacleEntity);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (EntityExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{mapId}")
    public ResponseEntity<List<ObstacleEntity>> findObstaclesByMapId(@PathVariable Long mapId) {
        List<ObstacleEntity> obstacles = obstacleService.findByMapId(mapId);
        return ResponseEntity.ok(obstacles);
    }
}

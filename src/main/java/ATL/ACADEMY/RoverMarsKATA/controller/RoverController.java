package ATL.ACADEMY.RoverMarsKATA.controller;

import ATL.ACADEMY.RoverMarsKATA.dto.CommandDto;
import ATL.ACADEMY.RoverMarsKATA.exceptions.ErrorResponse;
import ATL.ACADEMY.RoverMarsKATA.models.RoverEntity;
import ATL.ACADEMY.RoverMarsKATA.services.RoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rover")
public class RoverController {
    @Autowired
    private RoverService roverService;

    @GetMapping
    public RoverEntity getCurrentRover() {
        return roverService.getCurrentRover();
    }

    @PostMapping
    public ResponseEntity<?> moveRover(@RequestBody CommandDto payload) {
        try {
            return ResponseEntity.ok(roverService.processCommand(payload.getCommand()));
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(errorResponse);
        }
    }
}

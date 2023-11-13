package ATL.ACADEMY.RoverMarsKATA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ATL.ACADEMY.RoverMarsKATA.models.MapEntity;
import ATL.ACADEMY.RoverMarsKATA.services.RoverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ATL.ACADEMY.RoverMarsKATA.models.RoverEntity;

@SpringBootTest
class RoverMarsTests {

    @Autowired
    private RoverService roverService;

    @Test
    public void shouldExistRoverClass() {
      RoverEntity rover = new RoverEntity();
        assertNotNull(rover, "Rover class should exist and be instantiable.");
    }

    @Test
    public void shouldHaveGetXMethod() {
        RoverEntity rover = new RoverEntity();
        rover.setX(5);
        assertEquals(5, rover.getX(), "Rover should have a getX method that returns the x coordinate.");
    }

    @Test
    public void shouldHaveSetXMethod() {
        RoverEntity rover = new RoverEntity();
        rover.setX(10);
        assertEquals(10, rover.getX(), "Rover should have a setX method that sets the x coordinate.");
    }

    @Test
    public void shouldHaveSetDirectionMethod() {
        RoverEntity rover = new RoverEntity();
        rover.setDirection('N');
        assertEquals('N', rover.getDirection(), "Rover should have a setDirection method that sets the Direction.");
    }
    @Test
    public void shouldMoveForward() {
        MapEntity testMap = new MapEntity(16,16);
        RoverEntity rover = new RoverEntity(0, 0, 'N', testMap);
        roverService.moveForward(rover);
        assertEquals(1, rover.getY(), "Rover should move forward in the North direction.");
    }

    @Test
    public void shouldMoveBackward() {
        MapEntity testMap = new MapEntity(16,16);
        RoverEntity rover = new RoverEntity(0, 1, 'N', testMap);
        roverService.moveBackward(rover);
        assertEquals(0, rover.getY(), "Rover should move backward in the North direction.");
    }
    @Test
    public void shouldTurnLeft() {
        RoverEntity rover = new RoverEntity();
        rover.setDirection('N');
        roverService.turnLeft(rover);
        assertEquals('W', rover.getDirection(), "Rover should turn left from North to West.");
    }
    @Test
    public void shouldTurnRight() {
        RoverEntity rover = new RoverEntity();
        rover.setDirection('N');
        roverService.turnRight(rover);
        assertEquals('E', rover.getDirection(), "Rover should turn right from North to East.");
    }

    @Test
    public void shouldProcessForwardCommand() {
        char command = 'F';
        RoverEntity updatedRover = roverService.processCommand(command);
        assertNotNull(updatedRover, "Rover should be returned after processing command.");
    }
}

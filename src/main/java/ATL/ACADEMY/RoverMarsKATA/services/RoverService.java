package ATL.ACADEMY.RoverMarsKATA.services;

import ATL.ACADEMY.RoverMarsKATA.models.MapEntity;
import ATL.ACADEMY.RoverMarsKATA.models.ObstacleEntity;
import ATL.ACADEMY.RoverMarsKATA.models.RoverEntity;
import ATL.ACADEMY.RoverMarsKATA.repositories.IRoverRepository;
import ATL.ACADEMY.RoverMarsKATA.validator.CommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoverService {
    @Autowired
    private IRoverRepository roverRepository;
    @Autowired
    private MapService mapService;

    @Autowired
    private ObstacleService obstacleService;
    public RoverEntity getCurrentRover() {

        RoverEntity rover = roverRepository.findFirstByOrderByIdDesc().orElse(null);
        if (rover == null) {
            rover = new RoverEntity();
            rover.setX(1);
            rover.setY(1);
            rover.setDirection('N');
            MapEntity defaultMap = mapService.createDefaultMap();
            rover.setMap(defaultMap);
            rover = roverRepository.save(rover);
        }
        return rover;

    }

    public void moveForward(RoverEntity rover) {
        MapEntity map = rover.getMap();
        switch (rover.getDirection()) {
            case 'N':
                rover.setY(map.wrapY(rover.getY() + 1));
                break;
            case 'E':
                rover.setX(map.wrapX(rover.getX() + 1));
                break;
            case 'S':
                rover.setY(map.wrapY(rover.getY() - 1));
                break;
            case 'W':
                rover.setX(map.wrapX(rover.getX() - 1));
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + rover.getDirection());
        }
    }

    public void moveBackward(RoverEntity rover) {
        MapEntity map = rover.getMap();
        switch (rover.getDirection()) {
            case 'N':
                rover.setY(map.wrapY(rover.getY() - 1));
                break;
            case 'E':
                rover.setX(map.wrapX(rover.getX() - 1));
                break;
            case 'S':
                rover.setY(map.wrapY(rover.getY() + 1));
                break;
            case 'W':
                rover.setX(map.wrapX(rover.getX() + 1));
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + rover.getDirection());
        }
    }

    public void turnLeft(RoverEntity rover) {
        switch (rover.getDirection()) {
            case 'N':
                rover.setDirection('W');
                break;
            case 'E':
                rover.setDirection('N');
                break;
            case 'S':
                rover.setDirection('E');
                break;
            case 'W':
                rover.setDirection('S');
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + rover.getDirection());
        }
    }

    public void turnRight(RoverEntity rover) {
        switch (rover.getDirection()) {
            case 'N':
                rover.setDirection('E');
                break;
            case 'E':
                rover.setDirection('S');
                break;
            case 'S':
                rover.setDirection('W');
                break;
            case 'W':
                rover.setDirection('N');
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + rover.getDirection());
        }
    }

    public RoverEntity processCommand(char command) {
        RoverEntity rover = this.getCurrentRover();
        CommandValidator.validate(command);
        switch (command) {
            case 'F':
                this.moveForward(rover);
                break;
            case 'B':
                this.moveBackward(rover);
                break;
            case 'L':
                this.turnLeft(rover);
                break;
            case 'R':
                this.turnRight(rover);
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + command);
        }

        if(!this.canMoveRover(rover)){
            System.out.println(rover);
            throw new IllegalArgumentException("Please choose a different direction to move.");
        }
        return roverRepository.save(rover);
    }


    public boolean canMoveRover (RoverEntity rover){
        Boolean canMove = true;
        Optional<ObstacleEntity> existingObstacle = obstacleService.existingObstacle (rover.getX(), rover.getY(), rover.getMap().getId());
        if (existingObstacle.isPresent()) {
            canMove = false;
        }
        return canMove;
    }
}

package ATL.ACADEMY.RoverMarsKATA;

import static org.junit.jupiter.api.Assertions.*;

import ATL.ACADEMY.RoverMarsKATA.models.MapEntity;
import ATL.ACADEMY.RoverMarsKATA.services.MapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MapServiceTests {

    private static final int DEFAULT_MAP_WIDTH = 10;
    private static final int DEFAULT_MAP_HEIGHT = 10;

    @Autowired
    private MapService mapService;

    @Test
    public void createDefaultMapShouldCreateNewMap() {
        MapEntity map = mapService.createDefaultMap();
        assertNotNull(map, "createDefaultMap should create a new map.");
        assertEquals(DEFAULT_MAP_WIDTH, map.getWidth(), "Map should have the default width.");
        assertEquals(DEFAULT_MAP_HEIGHT, map.getHeight(), "Map should have the default height.");
    }

    @Test
    public void saveShouldCreateNewMap() {
        int width = 20;
        int height = 20;
        MapEntity map = mapService.save(width, height);
        assertNotNull(map, "save should create a new map with specified width and height.");
        assertEquals(width, map.getWidth(), "Map should have the specified width.");
        assertEquals(height, map.getHeight(), "Map should have the specified height.");
    }
}
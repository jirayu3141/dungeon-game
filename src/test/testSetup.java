package test;
import org.junit.jupiter.api.AfterEach;

import unsw.dungeon.*;

public class testSetup {
    Dungeon dungeon = null;
    Player player = null;
    GameController gameController = null;
    DungeonController dungeonController = null;
    
    
    public void setup(int width, int height, int playerX, int playerY) {
        //create new dungeon and controller
        gameController = new GameController();
        dungeon = new Dungeon(width, height, gameController);
        // setup a player
        player = new Player(dungeon, playerX, playerY, gameController);
        Sword playerSword1 = new Sword(0, 0);
        Sword playerSword2 = new Sword(0, 0);
        Sword playerSword3 = new Sword(0, 0);
        Sword playerSword4 = new Sword(0, 0);
        Entity[] swords = { (Entity) playerSword1, (Entity) playerSword2, (Entity) playerSword3,
            (Entity) playerSword4 };
        player.setPlayerSwords(swords);
        dungeon.setPlayer(player);
        // setup dungeon controller responsible for timed funcnctions
        dungeonController  = new DungeonController(dungeon);
    }

    @AfterEach
	void teatDown() {
		dungeon = null;
		player = null;
	}
}
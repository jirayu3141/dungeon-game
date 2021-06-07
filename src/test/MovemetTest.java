package test;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * MovemetTest
 */
public class MovemetTest extends testSetup {
	@Test
	void testFreeMovement() {
		//setup
        setup(3, 3, 1, 1);
		
		int startX = player.getX();
		int startY = player.getY();

		player.moveDown();
		assertEquals(startX, player.getX());
		assertEquals(startY + 1, player.getY());

		player.moveUp();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());

		player.moveLeft();
		assertEquals(startX - 1, player.getX());
		assertEquals(startY, player.getY());

		player.moveRight();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
	}

	@Test
	void testObstacleMovement() {
		setup(4, 4, 1, 1);

		// // create four walls around the enemy
		 dungeon.addMapElement(new Wall(1, 0));	// add a wall up
		 dungeon.addMapElement(new Wall(1, 2));	// add a wall down
		 dungeon.addMapElement(new Door(0, 1, 0));	// add a closed door in the left
		 dungeon.addMapElement(new Boulder(2, 1)); // add 2 boulder in the right
		 dungeon.addMapElement(new Boulder(3, 1));

		int startX = player.getX();
		int startY = player.getY();

		// player should not move
		player.moveDown();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());

		// player should not move
		player.moveUp();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());

		// player should not move
		player.moveLeft();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());

		// player should not move
		player.moveRight();
		assertEquals(startX, player.getX());
		assertEquals(startY, player.getY());
	}
}

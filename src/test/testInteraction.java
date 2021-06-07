package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

public class testInteraction extends testSetup {

	/*
	 * Player can collect any number of treasures
	 */
	@Test
	void testPickUpSword() {
		setup(5, 3, 1, 1);

		dungeon.addWeapon(new Sword(2, 1));
		dungeon.addWeapon(new Sword(3, 1));

		// since the player has no sword, the number of sword is 0
		assertEquals(0, player.getWeaponUses());

		// collect weapon
		player.moveRight();
		assertEquals(5, player.getWeaponUses());

		// player.useWeapon();
		// assertEquals(4, player.getWeaponUses()); // after using a sword, the
		// durability decreases

		// dungeon.movePlayer(Direction.RIGHT);
		// assertEquals(5, dungeon.getInventory().getSwordDurability()); // the
		// durability would be refreshed
		// assertEquals(0, dungeon.getEntities(EntityType.SWORD).size());
	}

	/*
	 * Player picking potion will set invincibility step to 5. Player is invincible
	 * for 5 time units dependant on a scheduler
	 * 
	 */
	@Test
	void testPickUpPotion() {
		setup(5, 3, 1, 1);

		// adding goal to not end in victory state
		dungeon.addItem(new Treasure(4, 2));
		gameController.setGameGoals("treasure");

		dungeon.addItem(new InvincibilityPotion(2, 1));
		dungeon.addItem(new InvincibilityPotion(3, 1));
		dungeon.addItem(new InvincibilityPotion(4, 1));

		assertEquals("invincibility", dungeon.getItem(2, 1).getName());
		assertEquals("invincibility", dungeon.getItem(3, 1).getName());
		assertEquals("invincibility", dungeon.getItem(4, 1).getName());

		assertEquals(false, player.getInvincibilityState());

		player.moveRight();

		assertEquals(2, player.getX());
		assertEquals(1, player.getY());

		assertEquals(true, player.getInvincibilityState());

		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(false, player.getInvincibilityState());

		assertEquals(null, dungeon.getItem(2, 1));
		assertEquals("invincibility", dungeon.getItem(3, 1).getName());
		assertEquals("invincibility", dungeon.getItem(4, 1).getName());

		dungeonController.timedFunctions();

		player.moveRight();
		assertEquals(3, player.getX());
		assertEquals(1, player.getY());
		player.moveRight();
		assertEquals(4, player.getX());
		assertEquals(1, player.getY());

		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(true, player.getInvincibilityState());
		dungeonController.timedFunctions();
		assertEquals(false, player.getInvincibilityState());

	}

	@Test
	void testTreasure() {
		setup(15, 1, 0, 0);

		dungeon.addItem(new Treasure(1, 0));
		dungeon.addItem(new Treasure(2, 0));
		dungeon.addItem(new Treasure(3, 0));
		dungeon.addItem(new Treasure(4, 0));
		dungeon.addItem(new Treasure(5, 0));
		dungeon.addItem(new Treasure(6, 0));
		dungeon.addItem(new Treasure(7, 0));
		dungeon.addItem(new Treasure(8, 0));
		dungeon.addItem(new Treasure(9, 0));
		dungeon.addItem(new Treasure(10, 0));
		dungeon.addItem(new Treasure(11, 0));
		dungeon.addItem(new Treasure(12, 0));
		dungeon.addItem(new Treasure(13, 0));
		dungeon.addItem(new Treasure(14, 0));
		gameController.setGameGoals("treasure");

		// 1
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());

		// 2
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 3
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 4
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 5
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 6
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 7
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 8
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 9
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());

		// 10
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 11
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 12
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 13
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		// 14
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Victory", gameController.getGameState());

	}

	@Test
	void testkeyAndDoor() {
		setup(17, 1, 0, 0);

		dungeon.addItem(new Key(1, 0, 1));
		dungeon.addMapElement(new Door(2, 0, 1));
		dungeon.addItem(new Key(3, 0, 2));
		dungeon.addMapElement(new Door(4, 0, 2));
		dungeon.addItem(new Key(5, 0, 3));
		dungeon.addMapElement(new Door(6, 0, 3));
		dungeon.addItem(new Key(7, 0, 4));
		dungeon.addMapElement(new Door(8, 0, 4));
		dungeon.addItem(new Key(9, 0, 5));
		dungeon.addMapElement(new Door(10, 0, 5));
		dungeon.addItem(new Key(11, 0, 6));
		dungeon.addMapElement(new Door(12, 0, 6));
		dungeon.addMapElement(new Door(13, 0, 6));
		dungeon.addMapElement(new Door(14, 0, 7));
		dungeon.addItem(new Key(15, 0, 7));
		dungeon.addItem(new Treasure(16, 0));
		gameController.setGameGoals("treasure");

		// 1
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(1, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 2
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(2, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 3
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(3, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 4
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(4, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 5
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(5, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 6
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(6, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 7
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(7, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 8
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(8, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 9
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(9, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 10
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(10, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 11
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(11, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 12
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(12, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 13
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(13, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 14 Can't move any further
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(13, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());
		// 15
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals(13, player.getX());
		assertEquals(0, player.getY());
		assertEquals("Normal", gameController.getGameState());

	}

	@Test
	void testBoulder() {
		setup(1, 11, 0, 0);
		dungeon.addMapElement(new Boulder(0, 1));
		dungeon.addMapElement(new FloorSwitch(0, 2));
		dungeon.addMapElement(new FloorSwitch(0, 3));
		dungeon.addMapElement(new FloorSwitch(0, 4));
		dungeon.addMapElement(new FloorSwitch(0, 5));
		dungeon.addMapElement(new FloorSwitch(0, 6));
		dungeon.addMapElement(new FloorSwitch(0, 7));
		dungeon.addMapElement(new Boulder(0, 8));
		dungeon.addItem(new Treasure(0, 10));
		gameController.setGameGoals("treasure");

		// 1
		player.moveDown();
		dungeonController.timedFunctions();
		assertEquals(0, player.getX());
		assertEquals(1, player.getY());
		assertEquals("Normal", gameController.getGameState());
		assertEquals("boulder", dungeon.getMapElements(0, 2).getName());
		// 2
		player.moveDown();
		dungeonController.timedFunctions();
		assertEquals(0, player.getX());
		assertEquals(2, player.getY());
		assertEquals("Normal", gameController.getGameState());
		assertEquals("boulder", dungeon.getMapElements(0, 3).getName());
		// 3
		player.moveDown();
		dungeonController.timedFunctions();
		assertEquals(0, player.getX());
		assertEquals(3, player.getY());
		assertEquals("Normal", gameController.getGameState());
		assertEquals("boulder", dungeon.getMapElements(0, 4).getName());
		// 4
		player.moveDown();
		dungeonController.timedFunctions();
		assertEquals(0, player.getX());
		assertEquals(4, player.getY());
		assertEquals("Normal", gameController.getGameState());
		assertEquals("boulder", dungeon.getMapElements(0, 5).getName());
		// 5
		player.moveDown();
		dungeonController.timedFunctions();
		assertEquals(0, player.getX());
		assertEquals(5, player.getY());
		assertEquals("Normal", gameController.getGameState());
		assertEquals("boulder", dungeon.getMapElements(0, 6).getName());
		// 6
		player.moveDown();
		dungeonController.timedFunctions();
		assertEquals(0, player.getX());
		assertEquals(6, player.getY());
		assertEquals("Normal", gameController.getGameState());
		assertEquals("boulder", dungeon.getMapElements(0, 7).getName());
		// 7 Can't move due to boulder
		player.moveDown();
		dungeonController.timedFunctions();
		assertEquals(0, player.getX());
		assertEquals(6, player.getY());
		assertEquals("Normal", gameController.getGameState());
		assertEquals("boulder", dungeon.getMapElements(0, 7).getName());
	}

	@Test
	void testFloorSwitch() {
		setup(5, 5, 2, 2);
		dungeon.addMapElement(new Boulder(1, 2));
		dungeon.addMapElement(new Boulder(2, 1));
		dungeon.addMapElement(new Boulder(2, 3));
		dungeon.addMapElement(new Boulder(3, 2));
		dungeon.addMapElement(new FloorSwitch(2, 0));
		dungeon.addMapElement(new FloorSwitch(0, 2));
		dungeon.addMapElement(new FloorSwitch(4, 2));
		dungeon.addMapElement(new FloorSwitch(2, 4));

		gameController.setGameGoals("boulders");

		//
		player.moveUp();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());

		//
		player.moveDown();
		player.moveDown();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		//
		player.moveUp();
		player.moveRight();
		dungeonController.timedFunctions();
		assertEquals("Normal", gameController.getGameState());
		//
		player.moveLeft();
		player.moveLeft();
		dungeonController.timedFunctions();
		assertEquals("Victory", gameController.getGameState());

	}

	@Test
	void testPortal() {
		setup(200, 1, 199, 0);
		dungeon.addMapElement(new Portal(198, 0, 1));
		dungeon.addMapElement(new Portal(100, 0, 2));
		dungeon.addMapElement(new Portal(99, 0, 3));
		dungeon.addMapElement(new Portal(1, 0, 4));
		dungeon.addItem(new Treasure(0, 0));

		gameController.setGameGoals("treasure");

		assertEquals(199, player.getX());
		assertEquals(0, player.getY());

		player.moveLeft();
		assertEquals(100, player.getX());
		assertEquals(0, player.getY());

		player.moveLeft();
		assertEquals(1, player.getX());
		assertEquals(0, player.getY());

		player.moveLeft();
		assertEquals(0, player.getX());
		assertEquals(0, player.getY());

		dungeonController.timedFunctions();

	}

	@Test
	void testEnemy() {
		setup(20, 1, 10, 0);
		Enemy e1 = new RegularEnemy(0, 0);
		Enemy e2 = new RegularEnemy(19, 0);
		dungeon.addEnemy(e1);
		dungeon.addEnemy(e2);
		dungeon.addItem(new InvincibilityPotion(9, 0));
		dungeon.addItem(new Treasure(11, 0));

		gameController.setGameGoals("treasure");

		// assertEquals(0, player.getX());
		// assertEquals(0, player.getY());

		assertEquals(0, e1.getX());
		assertEquals(0, e1.getY());
		assertEquals(19, e2.getX());
		assertEquals(0, e2.getY());

		dungeonController.timedFunctions();

		assertEquals(1, e1.getX());
		assertEquals(0, e1.getY());
		assertEquals(18, e2.getX());
		assertEquals(0, e2.getY());

		dungeonController.timedFunctions();

		assertEquals(2, e1.getX());
		assertEquals(0, e1.getY());
		assertEquals(17, e2.getX());
		assertEquals(0, e2.getY());

		dungeonController.timedFunctions();

		assertEquals(3, e1.getX());
		assertEquals(0, e1.getY());
		assertEquals(16, e2.getX());
		assertEquals(0, e2.getY());

		player.moveLeft();

		dungeonController.timedFunctions();

		assertEquals(2, e1.getX());
		assertEquals(0, e1.getY());
		assertEquals(17, e2.getX());
		assertEquals(0, e2.getY());

		dungeonController.timedFunctions();

		assertEquals(1, e1.getX());
		assertEquals(0, e1.getY());
		assertEquals(18, e2.getX());
		assertEquals(0, e2.getY());

	}

}

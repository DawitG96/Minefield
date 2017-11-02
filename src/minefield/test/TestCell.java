package minefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import minefield.Cell;

public class TestCell {
	
	private Cell cell;
	
	/**
	 * Test per controllare il primo parametro costruttore
	 */
	@Test
	public void test01() {
		cell = new Cell(true, 0);
		assertTrue(cell.isMine());
		
		cell = new Cell(false, 0);
		assertFalse(cell.isMine());
	}
	
	/**
	 * Test per controllare il secondo parametro del costruttore
	 */
	@Test
	public void test02() {
		cell = new Cell(true, 0);
		assertEquals(cell.getNumMineNear(), 0);
		
		cell = new Cell(true, 3);
		assertEquals(cell.getNumMineNear(), 3);
		
		cell = new Cell(true, 8);
		assertEquals(cell.getNumMineNear(), 8);
	}
	
	/**
	 * Test per controllare che il costruttore sia giusto
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test03() {
		cell = new Cell(true, -1);
	}
	
	/**
	 * Test per controllare che le mine intorno non siano maggiori di 8
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test04() {
		cell = new Cell(true, 9);
	}
	
	/**
	 * Test per controllare che il processo di scoperta cella sia giusto
	 */
	@Test
	public void test05() {
		cell = new Cell(true, 0);
		
		assertTrue(cell.isCovered());
		assertTrue(cell.uncover());
		
		assertFalse(cell.uncover());
		assertFalse(cell.uncover());
		
		assertFalse(cell.isCovered());
		assertFalse(cell.isCovered());
	}
	
	/**
	 * Test per controllare che il processo di "mettere in stato pericoloso" di una cella sia giusto
	 */
	@Test
	public void test06() {
		cell = new Cell(true, 0);
		
		assertFalse(cell.isDangerous());
		assertFalse(cell.unSetDangerous());
		
		assertTrue(cell.setDangerous());
		
		assertFalse(cell.setDangerous());
		assertFalse(cell.setDangerous());
		assertTrue(cell.isDangerous());
		assertTrue(cell.isDangerous());
		
		assertTrue(cell.unSetDangerous());
		
		assertFalse(cell.isDangerous());
	}
	
	/**
	 * Test riassuntivo, che mette tutte le funzioni possibili insieme
	 */
	@Test
	public void test07() {
		cell = new Cell(true, 4);
		
		assertFalse(cell.isDangerous());
		assertTrue(cell.isMine());
		assertEquals(cell.getNumMineNear(), 4);
		assertTrue(cell.isCovered());
		
		assertTrue(cell.setDangerous());
		assertFalse(cell.uncover());
		assertTrue(cell.unSetDangerous());
		
		assertTrue(cell.uncover());
		assertFalse(cell.setDangerous());
		assertFalse(cell.isCovered());
		assertTrue(cell.isMine());
		assertEquals(cell.getNumMineNear(), 4);
	}
}
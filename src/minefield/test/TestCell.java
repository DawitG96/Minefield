package minefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import minefield.obj.Cell;

public class TestCell {
	
	Cell cell;
	
	@Test
	public void test01() {
		cell = new Cell(true, 0);
		assertTrue(cell.isMine());
		
		cell = new Cell(false, 0);
		assertFalse(cell.isMine());
	}
	
	@Test
	public void test02() {
		cell = new Cell(true, 0);
		assertEquals(cell.getNumMineNear(), 0);
		
		cell = new Cell(true, 2);
		assertEquals(cell.getNumMineNear(), 2);
	}
}

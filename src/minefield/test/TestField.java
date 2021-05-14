package minefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import minefield.exceptions.*;
import minefield.object.*;

public class TestField {
	
	private Field field;
	
	/**
	 * Test per il costruttore
	 */
	@Test
	public void test01(){
		field = new FieldNotSafe(10, 10, 10);
		assertEquals(field.maxColumn, 10);
		assertEquals(field.maxRow, 10);
		assertEquals(field.numberOfMines, 10);
		
		field = new FieldSafe(10, 10, 10);
		assertEquals(field.maxColumn, 10);
		assertEquals(field.maxRow, 10);
		assertEquals(field.numberOfMines, 10);
	}
	
	/**
	 * Test per il costruttore di FieldNotSafe
	 */
	@Test
	public void test02(){
		field = new FieldNotSafe(10, 10, 99);
		assertEquals(field.maxColumn, 10);
		assertEquals(field.maxRow, 10);
		assertEquals(field.numberOfMines, 99);
		
		field = new FieldNotSafe(20, 20, 1);
		assertEquals(field.maxColumn, 20);
		assertEquals(field.maxRow, 20);
		assertEquals(field.numberOfMines, 1);
		
		field = new FieldNotSafe(5, 5, 16);
		assertEquals(field.maxColumn, 5);
		assertEquals(field.maxRow, 5);
		assertEquals(field.numberOfMines, 16);
	}
	
	/**
	 * Test per il costruttore di FieldSafe
	 */
	@Test
	public void test03(){
		field = new FieldSafe(10, 10, 91);
		assertEquals(field.maxColumn, 10);
		assertEquals(field.maxRow, 10);
		assertEquals(field.numberOfMines, 91);
		
		field = new FieldSafe(20, 20, 1);
		assertEquals(field.maxColumn, 20);
		assertEquals(field.maxRow, 20);
		assertEquals(field.numberOfMines, 1);
		
		field = new FieldSafe(5, 5, 16);
		assertEquals(field.maxColumn, 5);
		assertEquals(field.maxRow, 5);
		assertEquals(field.numberOfMines, 16);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Colonne
	 */
	@Test(expected = ColumnException.class)
	public void test04a(){
		field = new FieldSafe(10, -10, 91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Colonne
	 */
	@Test(expected = ColumnException.class)
	public void test04b(){
		field = new FieldNotSafe(10, -10, 91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Righe
	 */
	@Test(expected = RowException.class)
	public void test05a(){
		field = new FieldSafe(-10, 10, 91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Righe
	 */
	@Test(expected = RowException.class)
	public void test05b(){
		field = new FieldNotSafe(-10, 10, 91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Mine
	 */
	@Test(expected = MineTooLowException.class)
	public void test06a(){
		field = new FieldSafe(10, 10, -91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Mine
	 */
	@Test(expected = MineTooLowException.class)
	public void test06b(){
		field = new FieldSafe(10, 10, -91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Mine
	 */
	@Test(expected = MineTooLowException.class)
	public void test06c(){
		field = new FieldSafe(10, 10, 0);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Mine
	 */
	@Test(expected = MineTooLowException.class)
	public void test06d(){
		field = new FieldSafe(10, 10, 0);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Mine
	 */
	@Test(expected = MineTooHighException.class)
	public void test06e(){
		field = new FieldSafe(10, 10, 92);
	}
	
	/**
	 * Test per il costruttore con parametri non validi: Mine
	 */
	@Test(expected = MineTooHighException.class)
	public void test06f(){
		field = new FieldNotSafe(10, 10, 100);
	}
	
	/**
	 * Test sul campo prima che avvenga una qualsiasi interazione
	 */
	@Test
	public void test7(){
		field = new FieldSafe(20, 10, 10);
		
		assertEquals(field.getNumCoveredCell(), 200);
		
		for(int j=0; j<field.maxColumn; j++)
			for(int i=0; i<field.maxRow; i++) {
				assertTrue(field.isCellCovered(i, j));
				assertFalse(field.isCellDangerous(i, j));
				assertFalse(field.isCellExploded(i, j));
			}
		
		field = new FieldNotSafe(10, 20, 10);
		assertEquals(field.getNumCoveredCell(), 200);
		
		for(int j=0; j<field.maxColumn; j++)
			for(int i=0; i<field.maxRow; i++) {
				assertTrue(field.isCellCovered(i, j));
				assertFalse(field.isCellDangerous(i, j));
				assertFalse(field.isCellExploded(i, j));
			}
	}
	
	/**
	 * Test per scoprire una cella
	 */
	@Test
	public void test8() {
		field = new FieldSafe(8, 15, 12);
		
		assertTrue(field.uncoverCell(0, 0));
		assertFalse(field.isCellCovered(0, 0));
		assertFalse(field.isCellExploded(0, 0));
		
		field = new FieldNotSafe(18, 5, 24);
		
		assertTrue(field.uncoverCell(0, 0));
		assertFalse(field.isCellCovered(0, 0));
	}
	
	/**
	 * Test specifico per Safe e uncover multipli
	 */
	@Test
	public void test09() {
		field = new FieldSafe(14, 13, 12);
		
		field.uncoverCell(6, 6);
		
		for(int column = 5; column<8; column++)
			for(int row = 5; row<8; row++)
				assertFalse(field.isCellExploded(row, column));
	}
}
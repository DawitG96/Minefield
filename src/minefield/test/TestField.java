package minefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import minefield.Field;
import minefield.FieldNotSafe;
import minefield.FieldSafe;

public class TestField {
	
	private Field field;
	
	/**
	 * Test per il costruttore
	 */
	@Test
	public void test01(){
		field = new FieldNotSafe(10, 10, 10);
		assertEquals(field.column, 10);
		assertEquals(field.row, 10);
		assertEquals(field.numMine, 10);
		
		field = new FieldSafe(10, 10, 10);
		assertEquals(field.column, 10);
		assertEquals(field.row, 10);
		assertEquals(field.numMine, 10);
	}
	
	/**
	 * Test per il costruttore di FieldNotSafe
	 */
	@Test
	public void test02(){
		field = new FieldNotSafe(10, 10, 99);
		assertEquals(field.column, 10);
		assertEquals(field.row, 10);
		assertEquals(field.numMine, 99);
		
		field = new FieldNotSafe(20, 20, 1);
		assertEquals(field.column, 20);
		assertEquals(field.row, 20);
		assertEquals(field.numMine, 1);
		
		field = new FieldNotSafe(5, 5, 16);
		assertEquals(field.column, 10);
		assertEquals(field.row, 10);
		assertEquals(field.numMine, 99);
	}
	
	/**
	 * Test per il costruttore di FieldSafe
	 */
	@Test
	public void test03(){
		field = new FieldSafe(10, 10, 91);
		assertEquals(field.column, 10);
		assertEquals(field.row, 10);
		assertEquals(field.numMine, 99);
		
		field = new FieldSafe(20, 20, 1);
		assertEquals(field.column, 20);
		assertEquals(field.row, 20);
		assertEquals(field.numMine, 1);
		
		field = new FieldSafe(5, 5, 16);
		assertEquals(field.column, 10);
		assertEquals(field.row, 10);
		assertEquals(field.numMine, 99);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test04a(){
		field = new FieldSafe(-10, 10, 91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test04b(){
		field = new FieldNotSafe(-10, 10, 91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test05a(){
		field = new FieldSafe(10, -10, 91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test05b(){
		field = new FieldNotSafe(10, -10, 91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test06a(){
		field = new FieldSafe(10, 10, -91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test06b(){
		field = new FieldSafe(10, 10, -91);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test06c(){
		field = new FieldSafe(10, 10, 0);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test06d(){
		field = new FieldSafe(10, 10, 0);
	}
	
	/**
	 * Test per il costruttore con parametri non validi
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test06e(){
		field = new FieldSafe(10, 10, 92);
	}
	
	/**
	 * Test sul campo prima che avvenga una qualsiasi interazione
	 */
	@Test
	public void test7(){
		field = new FieldSafe(20, 10, 10);
		
		assertEquals(field.getNumCoveredCell(), 100);
		
		for(int j=0; j<field.column; j++)
			for(int i=0; i<field.row; i++)
			{
				assertTrue(field.isCellCovered(i, j));
				assertFalse(field.isCellDangerous(i, j));
				assertFalse(field.isCellMine(i, j));
				assertFalse(field.isMineExploded(i, j));
			}
		
		field = new FieldNotSafe(10, 20, 10);
		assertEquals(field.getNumCoveredCell(), 100);
		
		for(int j=0; j<field.column; j++)
			for(int i=0; i<field.row; i++)
			{
				assertTrue(field.isCellCovered(i, j));
				assertFalse(field.isCellDangerous(i, j));
				assertFalse(field.isCellMine(i, j));
				assertFalse(field.isMineExploded(i, j));
			}
	}
}
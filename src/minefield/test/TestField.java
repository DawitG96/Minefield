package minefield.test;

import static org.junit.Assert.*;

import org.junit.Test;

import minefield.Field;
import minefield.FieldNotSafe;
import minefield.FieldSafe;

public class TestField {
	
	private Field field;
	
	@Test
	public void test01(){
		field = new FieldNotSafe(1, 1, 0);
		assertEquals(field.column, 1);
		assertEquals(field.row, 1);
		assertEquals(field.numMine, 0);
		
		field = new FieldSafe(1, 1, 0);
		assertEquals(field.column, 1);
		assertEquals(field.row, 1);
		assertEquals(field.numMine, 0);
	}
}
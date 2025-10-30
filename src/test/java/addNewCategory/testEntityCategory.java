package addNewCategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import domain.entities.Category;

public class testEntityCategory {
	@Test
	public void testIsValidName_HopLe() {
//		Test truong hop dung
//		assertTrue(Category.isValidName("Laptop"));
//		assertTrue(Category.isValidName("  Bàn phím cơ  "));
		
//		Test truong hop sai
		assertFalse(Category.isValidName(null));
		assertFalse(Category.isValidName(""));
		assertFalse(Category.isValidName(" "));
	}
}

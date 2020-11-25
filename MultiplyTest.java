package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplyTest {

	@Test
	void testMultiply() {
		Junit test = new Junit();
		int result = test.multiply(3, 4);
		assertEquals(12, result);
	}

}

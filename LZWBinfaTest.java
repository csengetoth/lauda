package teszt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LZWBinfaTest {
	LZWBinFa binfa = new LZWBinFa();
	@Test
	void test() {
		String word = "101011101010000011110110";
		for(int i=0;i<word.length();i++) {
			binfa.add(word.charAt(i));
		}
		
		assertEquals(4, binfa.getMelyseg());
		assertEquals(3.0, binfa.getAtlag());
		assertEquals(0.816496580927726, binfa.getSzoras(), 0.0001);
		
	}
	

}

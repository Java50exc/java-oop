package telran.exceptions.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.exceptions.BallBrokenFloor;

class ExceptionsTest {

	@Test
	@Disabled
	void testException()  {
		int res = 0;
			try {
				res = itThrowsCheckedException(10000);
				
				System.out.println("everything ok");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				res = 100;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				res = 200;
			}

			
	}
	private int itThrowsCheckedException(int number) throws Exception  {
		if(number < 0) {
			throw new Exception("just test checked exception");
		}
		if (number > 1000) {
			throw new RuntimeException("number cannot be greater than 1000");
		}
		return number * 2;
		
	}
	@Test
	void ballBrokenFloorTest() {
		BallBrokenFloor bbf = new BallBrokenFloor(200);
		assertEquals(bbf.getFloor(),getMinFloor(bbf));
	}
	private int getMinFloor(BallBrokenFloor bbf) {
		for (int i = 0; i < 200; i++) {
			try {
				bbf.broken(i);
			} catch (Exception e) {
				return i;
			}
		}
		return -1;
	}

}

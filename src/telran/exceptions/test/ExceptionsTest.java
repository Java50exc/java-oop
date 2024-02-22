package telran.exceptions.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.exceptions.BallBrokenFloor;

class ExceptionsTest {

	@Test
	@Disabled
	void testException() {
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

	private int itThrowsCheckedException(int number) throws Exception {
		if (number < 0) {
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
		assertEquals(bbf.getFloor(), getMinFloor(bbf));
	}

	private int getMinFloor(BallBrokenFloor bbf) {
		int left = 1;
		int right = 200;
		int middle = right / 2;

		while (left < right) {
			try {
				bbf.broken(middle);
				left = middle + 1;
			} catch (Exception e) {
				right = middle;
			} finally {
				middle = (left + right) / 2;
			}
		}
		return middle;
	}

}

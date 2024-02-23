package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Shape;
import telran.shapes.Square;

class CanvasTests {

	Canvas canvas = new Canvas();

	@BeforeEach
	void setUp() {
		
		canvas.addShape(new Square(5));
		canvas.addShape(new Square(6));
		canvas.addShape(new Square(5));
		canvas.addShape(new Square(5));
		canvas.addShape(new Square(5));

	}

	@Test
	void perimeter_correctFlow_success() {
		int expected = 104;
		assertEquals(expected, canvas.perimeter());

	}
	
	@Test
	void square_correctFlow_success() {
		int expected = 136;
		assertEquals(expected, canvas.square());
	}
	
	@Test
	void removeIf_removedAll_success() {
		Shape[] expected = {};
		canvas.removeIf(s -> true);
		assertArrayEquals(expected, canvas.shapes);
	}
	
	@Test
	void removeIf_notRemoved_success() {
		int expected = 5;
		canvas.removeIf(s -> false);
		assertEquals(expected, canvas.shapes.length);
	}
	
	@Test
	void removeIf_removedSome_success() {
		int expected = 1;
		canvas.removeIf(s -> s.perimeter() == 20);
		assertEquals(expected, canvas.shapes.length);
	}
}

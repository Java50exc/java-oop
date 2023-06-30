package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.*;

class ShapesTest {
	int[] widthes = {3, 4, 10, 6, 5, 4, 10, 7};
	int[] heights = {7, 4, 12, 1, 7, 8, 10, 4};
	Canvas canvas012;
	Canvas canvas345;
	Canvas canvas012_345_67;
	@BeforeEach
	void setUp() {
		
		canvas012 = new Canvas();
		fillCanvas(canvas012, 0, 3);
		canvas345 = new Canvas();
		fillCanvas(canvas345, 3, 3);
		canvas012_345_67 = new Canvas();
		canvas012_345_67.addShape(canvas012);
		canvas012_345_67.addShape(canvas345);
		fillCanvas(canvas012_345_67, 6, 2);
	}

	private void fillCanvas(Canvas canvas, int offset, int length) {
		int limit = offset + length;
		for(int i = offset; i < limit; i++) {
			Shape shape = widthes[i] == heights[i] ? new Square(heights[i]) : new Rectangle(widthes[i], heights[i]);
			canvas.addShape(shape);
		}
		
	}

	@Test
	void perimeterRectangleTest() {
		Rectangle rec = new Rectangle(3, 4);
		assertEquals(14, rec.perimeter());
	}
	@Test
	void perimeterSquareTest() {
		Square square = new Square(4);
		assertEquals(16, square.perimeter());
	}
	@Test
	void perimeterCanvasTest() {
		assertEquals(80, canvas012.perimeter());
	}
	@Test
	void squareRectangleTest() {
		Rectangle rec = new Rectangle(3, 4);
		assertEquals(12, rec.square());
	}
	@Test
	void squareSquareTest() {
		Square square = new Square(4);
		assertEquals(16, square.square());
	}
	@Test
	void squareCanvasTest() {
		assertEquals(157, canvas012.square());
	}
	@Test
	void removeIfTest() {
		canvas012.removeIf(s -> s instanceof Square);
		assertEquals(64, canvas012.perimeter());
		assertEquals(141, canvas012.square());
	}
	@Test
	void removeCanvasesTest() {
		canvas012_345_67.removeNestedCanvases();
		assertEquals(62, canvas012_345_67.perimeter());
		assertEquals(128, canvas012_345_67.square() );
	}
	@Test
	void iteratorTest() {
		int nShapes = 0;
		Iterator<Shape> it = canvas012_345_67.iterator();
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
		while(it.hasNext()) {
			it.next();
			nShapes++;
		}
		assertEquals(4, nShapes);
		assertThrowsExactly(NoSuchElementException.class, ()->it.next());
		it.remove();
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
	}
	
	

}

package telran.shapes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Canvas implements Shape, Iterable<Shape> {
	public Shape[] shapes = new Shape[0];

	private class CanvasIterator implements Iterator<Shape> {
		private int next = 0;
		private boolean wasNext = false;

		@Override
		public boolean hasNext() {

			return next < shapes.length;
		}

		@Override
		public Shape next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			wasNext = true;
			return shapes[next++];
		}

		@Override
		public void remove() {
			if (!wasNext) {
				throw new IllegalStateException();
			}
			wasNext = false;
			removePrevious(next--);
		}

		private void removePrevious(int cur) {
			System.arraycopy(shapes, cur, shapes, cur - 1, shapes.length - cur);
			shapes = Arrays.copyOf(shapes, shapes.length - 1);
			
		}

	}

	@Override
	public int perimeter() {
		int commonPerimeter = 0;
		
		for (var sh: this) {
			commonPerimeter += sh.perimeter();
		}
		return commonPerimeter;
	}

	@Override
	public int square() {
		int commonSquare = 0;
		
		for (var sh: this) {
			commonSquare += sh.square();
		}
		return commonSquare;
	}

	@Override
	public Iterator<Shape> iterator() {
		return new CanvasIterator();
	}

	public void addShape(Shape shape) {
		shapes = Arrays.copyOf(shapes, shapes.length + 1);
		shapes[shapes.length - 1] = shape;
	}

	public boolean removeIf(Predicate<Shape> predicate) {
		int oldLength = shapes.length;
		Iterator<Shape> it = iterator();
		while(it.hasNext()) {
			Shape cur = it.next();
			if (predicate.test(cur)) {
				it.remove();
			}
		}
		return oldLength > shapes.length;
	}

	public boolean removeNestedCanvases() {
		return removeIf(shape -> shape instanceof Canvas);
	}

}

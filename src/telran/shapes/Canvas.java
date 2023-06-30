package telran.shapes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Canvas implements Shape, Iterable<Shape> {
    private Shape[] shapes = new Shape[0];
    private class CanvasIterator implements Iterator<Shape> {
    	int currentIndex = 0;
    	boolean flNext = false;
		@Override
		public boolean hasNext() {
			
			return currentIndex < shapes.length;
		}

		@Override
		public Shape next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return shapes[currentIndex++];
		}
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			flNext = false;
			removeShapeAtIndex(--currentIndex);
		}
    	
    }
	@Override
	public int perimeter() {
		int res = 0;
		for(Shape shape: this) {
			res += shape.perimeter();
		}
		return res;
	}

	public void removeShapeAtIndex(int index) {
		Shape[] tmp = new Shape[shapes.length - 1];
		System.arraycopy(shapes, 0, tmp, 0, index);
		System.arraycopy(shapes, index + 1, tmp, index, tmp.length - index);
		shapes = tmp;
		
		
	}

	@Override
	public int square() {
		//sum of square values for all shapes in this canvas
		int res = 0;
		for(Shape shape: this) {
			res += shape.square();
		}
		return res;
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
    		Shape shape = it.next();
    		if(predicate.test(shape)) {
    			it.remove();
    		}
    	}
    	return oldLength > shapes.length;
    }
    public boolean removeNestedCanvases() {
    	return removeIf(shape -> shape instanceof Canvas);
    }

}

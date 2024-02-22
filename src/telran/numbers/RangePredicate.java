package telran.numbers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate implements Iterable<Integer> {
	int minInclusive;
	int maxExclusive;
	Predicate<Integer> predicate;

	public RangePredicate(int minInclusive, int maxExclusive) {
		this.predicate = e -> true;
		this.minInclusive = minInclusive;
		this.maxExclusive = maxExclusive;
	}

	public Predicate<Integer> getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}

	public int[] toArray() {
		int[] arr = new int[maxExclusive - minInclusive];
		int ind = 0;
		for (var el : this) {
			System.out.println(el);
			arr[ind++] = el;
		}
		
		return Arrays.copyOf(arr, ind);
	}

	private class RangePredicateIterator implements Iterator<Integer> {
		Predicate<Integer> innerPredicate;
		int next;
		
		RangePredicateIterator(Predicate<Integer> predicate) {
			this.innerPredicate = predicate;
			next = getNext(minInclusive - 1);
		}


		@Override
		public boolean hasNext() {
			return next < maxExclusive;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int cur = next;
			next = getNext(next);
			return cur;
		}
		
		private Integer getNext(int current) {
			do {
				current++;		
			} while (!innerPredicate.test(current) && current <= maxExclusive);
			return current;
		}

	}

	@Override
	public Iterator<Integer> iterator() {

		return new RangePredicateIterator(this.predicate);
	}

}

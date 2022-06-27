package hwr.oop.client.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;


public class CircularFifoQueue<T> implements Queue<T> {

	private LinkedList<T> inner = new LinkedList<>();
	private int capacity;

	public CircularFifoQueue(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("size cannot be negative");
		this.capacity = capacity;
	}

	/**
	 * @return the maximum elements this queue will hold
	 */
	public int capacity() {
		return this.capacity;
	}

	@Override
	public int size() {
		return this.inner.size();
	}

	@Override
	public boolean isEmpty() {
		return this.inner.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.inner.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return this.inner.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.inner.toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return this.inner.toArray(a);
	}

	@Override
	public boolean add(T t) {
		if (this.size() + 1 > this.capacity()) {
			this.inner.removeFirst();
		}

		return this.inner.add(t);
	}

	@Override
	public boolean remove(Object o) {
		return this.inner.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.inner.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (c.size() > this.capacity()) {
			var overshoot = c.size() - this.capacity();
			this.clear();

			return this.inner.addAll(c.stream().skip(overshoot).collect(Collectors.toList()));
		}


		var toRemove = c.size() + this.size() - this.capacity();
		for (int i = 0; i < toRemove; i++) {
			this.inner.removeFirst();
		}

		return this.inner.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.inner.containsAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.inner.retainAll(c);
	}

	@Override
	public void clear() {
		this.inner.clear();
	}

	@Override
	public boolean offer(T t) {
		return this.add(t);
	}

	@Override
	public T remove() {
		return this.inner.remove();
	}

	@Override
	public T poll() {
		return this.inner.poll();
	}

	@Override
	public T element() {
		return this.inner.element();
	}

	@Override
	public T peek() {
		return this.inner.peek();
	}
}

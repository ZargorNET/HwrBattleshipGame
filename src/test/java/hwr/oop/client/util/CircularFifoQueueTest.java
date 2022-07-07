package hwr.oop.client.util;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircularFifoQueueTest {

	CircularFifoQueue<Integer> queue;

	@BeforeEach
	void setUp() {
		queue = new CircularFifoQueue<>(5);
		queue.add(0);
		queue.add(2);
		queue.add(3);
		queue.add(4);
	}

	@Test
	void test_negativeCapacity() {
		try {
			new CircularFifoQueue<>(-5);
			Assertions.fail("should have thrown");
		} catch (IllegalArgumentException success) {
			// Success
		}
	}

	@Test
	void test_capacity() {
		Assertions.assertEquals(5, queue.capacity());
	}

	@Test
	void test_size() {
		Assertions.assertEquals(4, queue.size());
	}

	@Test
	void test_isEmpty() {
		Assertions.assertFalse(queue.isEmpty());
		Assertions.assertTrue(new CircularFifoQueue<Integer>(5).isEmpty());
	}

	@Test
	void test_add() {
		queue.add(5);
		Assertions.assertArrayEquals(new Integer[]{0, 2, 3, 4, 5}, queue.toArray());
		queue.add(6);
		Assertions.assertArrayEquals(new Integer[]{2, 3, 4, 5, 6}, queue.toArray());
	}

	@Test
	void test_addAll() {
		queue.addAll(Arrays.asList(5, 6, 7));
		Assertions.assertArrayEquals(new Integer[]{3, 4, 5, 6, 7}, queue.toArray());
	}

	@Test
	void test_overshootAddAll() {
		queue.addAll(Arrays.asList(5, 6, 7, 8, 9, 10, 11));
		Assertions.assertArrayEquals(new Integer[]{7, 8, 9, 10, 11}, queue.toArray());
	}

	@SuppressWarnings("ConstantConditions")
	@Test
	void test_clear() {
		queue.clear();
		Assertions.assertTrue(queue.isEmpty());
		Assertions.assertEquals(0, queue.size());
	}
}
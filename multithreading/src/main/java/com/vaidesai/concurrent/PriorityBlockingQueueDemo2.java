package com.vaidesai.concurrent;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/*-
 * Same as PriorityBlockingQueueDemo1.java but uses a custom object instead of primitive types.
 * This is to demonstrate the Comparable interface with such priority queues
 * 
 */

class Person {

	public String name;
	public Integer age;

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "name: " + this.name + ", age: " + this.age;
	}
}

class PersonAgeComparator implements Comparator<Person> {

	public int compare(Person p1, Person p2) {
		return p1.age - p2.age;
	}
}

class Producer2 implements Runnable {

	private BlockingQueue<Person> queue;

	public Producer2(BlockingQueue<Person> queue) {
		this.queue = queue;
	}

	public void run() {

		try {

			// add 4 Persons in the queue
			Person p1 = new Person("John", 55);
			queue.put(p1);
			System.out.println("Added: " + p1);

			Person p2 = new Person("Jane", 33);
			queue.put(p2);
			System.out.println("Added: " + p2);

			Person p3 = new Person("Jim", 18);
			queue.put(p3);
			System.out.println("Added: " + p3);

			Person p4 = new Person("Jack", 44);
			queue.put(p4);
			System.out.println("Added: " + p4);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer2 implements Runnable {

	private BlockingQueue<Person> queue;

	public Consumer2(BlockingQueue<Person> queue) {
		this.queue = queue;
	}

	public void run() {

		try {
			// This guy will fetch Persons from the queue based on age
			Thread.sleep(5000);
			System.out.println("Consumed: " + queue.remove());
			System.out.println("Consumed: " + queue.remove());
			System.out.println("Consumed: " + queue.remove());
			System.out.println("Consumed: " + queue.remove());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class PriorityBlockingQueueDemo2 {

	public static void main(String[] args) {

		// Person
		PersonAgeComparator cmp = new PersonAgeComparator();
		BlockingQueue<Person> queue = new PriorityBlockingQueue<Person>(4, cmp);

		Thread producer = new Thread(new Producer2(queue));
		Thread consumer = new Thread(new Consumer2(queue));

		// runs infinitely
		consumer.start();
		producer.start();
	}

}

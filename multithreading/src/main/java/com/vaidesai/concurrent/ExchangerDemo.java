package com.vaidesai.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/*-
 * 	With the help of Exchanger -> two threads can exchange objects
 * 		
 *     exchange() -> exchanging objects is done via one of the two exchange() methods
 *
 *	No wait() & notify() needed since threads are talking to each other directly via exchanger.
 *	1. FillingWorker will let the EmptyingWorker fetch data when the list has 5 items.
 *	2. EmptyingWorker will then drain it and then give it back to the FillingWorker for filling it
 *
 */

class FillingWorker implements Runnable {

	private List<Integer> list;
	private Exchanger<List<Integer>> exchanger;

	public FillingWorker(Exchanger<List<Integer>> exchanger) {
		this.exchanger = exchanger;
		this.list = new ArrayList<Integer>();
	}

	public void run() {

		while (true) {

			// swap list when size = 5
			if (list.size() == 5) {
				try {
					System.out
							.println("FillingWorker handing over the list with size: "
									+ list.size());
					list = exchanger.exchange(list);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			list.add(new Random().nextInt(1000));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class EmptyingWorker implements Runnable {

	private List<Integer> list;
	private Exchanger<List<Integer>> exchanger;

	public EmptyingWorker(Exchanger<List<Integer>> exchanger) {
		this.exchanger = exchanger;
		this.list = new ArrayList<Integer>();
	}

	public void run() {

		while (true) {

			// swap list when size = 10
			if (list.isEmpty()) {
				try {
					System.out
							.println("EmptyingWorker handing over the list with size: "
									+ list.size());
					list = exchanger.exchange(list);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// O(N), this will re-shuffle the list everytime
			System.out.println("Consumed: " + list.remove(0));
		}
	}
}

public class ExchangerDemo {

	public static void main(String[] args) {

		Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
		Thread t1 = new Thread(new FillingWorker(exchanger));
		Thread t2 = new Thread(new EmptyingWorker(exchanger));

		t1.start();
		t2.start();
	}

}

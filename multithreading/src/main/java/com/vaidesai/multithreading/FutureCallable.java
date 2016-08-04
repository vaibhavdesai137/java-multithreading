package com.vaidesai.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor5 implements Callable<String> {

	private int id;

	public Processor5(int id) {
		this.id = id;
	}

	public String call() throws Exception {
		Thread.sleep(1000);
		return "Id: " + id;
	}

}

public class FutureCallable {

	public static void main(String[] args) {

		ExecutorService svc = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<Future<String>>();

		// try executing 10 threads at a time
		// only 2 will be allowed
		for (int i = 1; i <= 10; i++) {

			// each thread will return a string
			// store it in list for a later access
			Future<String> futureStr = svc.submit(new Processor5(i));
			list.add(futureStr);
		}

		// iterate over the list and get the responses
		// this will be called only all threads have finished
		for (Future<String> f : list) {
			try {
				System.out.println(f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		svc.shutdown();

	}

}

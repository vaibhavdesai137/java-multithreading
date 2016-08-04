package com.vaidesai.concurrent;

/*-
 * 	This is an unbounded BlockingQueue of objects that implement the Delayed interface
 * 
 * 		- DelayQueue keeps the elements internally until a certain delay has expired
 *		- an object can only be taken from the queue when its delay has expired !!!
 *		- we cannot place null items in the queue
 *		- The queue is sorted so that the object at the head has a delay that has expired for the longest time. 
 *				If no delay has expired, then there is no head element and poll( ) will return null !!!
 *		
 *		Example: https://examples.javacodegeeks.com/core-java/util/concurrent/delayqueue/java-util-concurrent-delayqueue-example/
 */

public class DelayQueueDemo {

}

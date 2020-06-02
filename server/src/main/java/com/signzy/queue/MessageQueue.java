package com.signzy.queue;

import java.util.PriorityQueue;

import com.signzy.message.Message;

/**
 *	Message queue to store all the incoming messages 
 * 
 */
public class MessageQueue {

	private static PriorityQueue<Message> queue = new PriorityQueue<>();
	
	private MessageQueue() {}
	
	/**
	 * Polls the queue
	 * 
	 * @return
	 */
	public static synchronized Message poll() {
		return queue.poll();
	}
	
	/**
	 * Adds to the queue
	 * 
	 * @param message
	 */
	public static synchronized void put(Message message) {
		queue.add(message);
	}
}

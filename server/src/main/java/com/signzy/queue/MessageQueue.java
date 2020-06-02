package com.signzy.queue;

import java.util.PriorityQueue;

import com.signzy.message.Message;

public class MessageQueue {

	private static PriorityQueue<Message> queue = new PriorityQueue<>();
	
	private MessageQueue() {}
	
	public static synchronized Message poll() {
		return queue.poll();
	}
	
	public static synchronized void put(Message message) {
		queue.add(message);
	}
}

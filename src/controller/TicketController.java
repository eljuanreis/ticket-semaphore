package controller;

import java.util.concurrent.Semaphore;

import generators.TicketGenerator;
import threads.CheckoutThread;

public class TicketController {

	public void runTicketShop(int simultaneousAccesses)
	{
		Semaphore semaforo = new Semaphore(1);

		for (int i = 0; i < simultaneousAccesses; i++) {
			CheckoutThread t = new CheckoutThread(semaforo, TicketGenerator.generateTicketQuantity());
			
			t.start();
		}
	}
}

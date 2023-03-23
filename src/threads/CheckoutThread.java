package threads;

import model.Party;

import java.util.concurrent.Semaphore;

import constants.Timeout;
import generators.*;

public class CheckoutThread extends Thread {

	private Semaphore semaforo;
	private int ticketsQuantity;

	public CheckoutThread(Semaphore semaforo, int ticketsQuantity) {
		this.semaforo = semaforo;
		this.ticketsQuantity = ticketsQuantity;
	}
	
	@Override
	public void run() {
		
		boolean logado = false;
		boolean comprou = false;
		
		try {
			logado = login();
			semaforo.acquire();
			comprou = checkout();
			semaforo.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if  (logado && comprou) {
			try {
				semaforo.acquire();
				validate();
			} catch (Exception e) {
				System.err.println(e.getStackTrace());
			} finally {
				semaforo.release();
			}
		}
	}
	
	private boolean login() throws InterruptedException
	{
		int timeLogin = TimesGenerator.generateLoginTime();

		sleep(timeLogin);

		if (timeLogin > Timeout.MAX_LOGIN_TIME) {
			System.out.printf("[Pedido] [%S] " + " teve um timeout.\n", (int) getId());
			
			return false;
		}
		
		return true;
	}
	
	private boolean checkout() throws InterruptedException
	{
		int timeCheckout = TimesGenerator.generateCheckoutTime();
		
		sleep(timeCheckout);
		
		if (timeCheckout > Timeout.MAX_CHECKOUT_TIME) {
			System.out.printf("[Pedido] [%S] " + " teve a sessão expirada.\n", (int) getId());
			return false;
		}
		
		return true;
	}
	
	private void validate()
	{
		if (Party.hasLimit()) {
			System.out.println("====================");
			System.out.println("JSystem - Venda de ingresso");
			System.out.println("O " + (int) getId() + " acabou de comprar " + this.ticketsQuantity);
			
			constants.Party.PARTY_LIMIT -= this.ticketsQuantity;
			
			System.out.println("Existem " + constants.Party.PARTY_LIMIT + " ingressos disponíveis");
			
			System.out.println("====================");
		} else {
			System.out.printf("[Pedido] [%S] " + " não conseguiu comprar por falta de ingressos.\n", (int) getId());
		}
	}
}

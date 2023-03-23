package view;

import constants.Party;
import controller.TicketController;

public class Main {
	public static void main(String[] args) {
		TicketController t = new TicketController();
		
		t.runTicketShop(Party.SITE_MAX_ACCESS);
	}
}

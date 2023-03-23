package generators;

public class TicketGenerator {

	public static int generateTicketQuantity()
	{
		return (int) (Math.random() * (4 - 1) + 1);
	}
}

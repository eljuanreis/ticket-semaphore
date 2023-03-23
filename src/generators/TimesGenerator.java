package generators;

public class TimesGenerator {
	
	public static int generateLoginTime()
	{
		return (int) (Math.random() * (2000 - 50) + 50);
	}
	
	public static int generateCheckoutTime()
	{
		return (int) (Math.random() * (3000 - 1000) + 1000);
	}
}

package world;

public class Camera {

	public static int x;
	public static int y;
	
	public static int Clamp(int value, int min, int max)
	{
		if (value < min)
		{
			value = min;
		}
		if (value > max)
		{
			value = max;
		}
		return value;
	}
}

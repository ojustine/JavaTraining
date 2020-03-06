import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class YandexContest02
{
	private static ArrayList<Meet> meets = new ArrayList<>();

	static class Meet
	{
		Meet(int start, int duration, String[] party)
		{
			this.start = start;
			this.duration = duration;
			this.party = party;
		}
		private int start;
		private int duration;
		private String[] party;
	}

	public static void main(String[] args)
	{
		try
		{
			InputStreamReader stdIn = new InputStreamReader(System.in);
			BufferedReader buffReader = new BufferedReader(stdIn);
			int requestsNum = Integer.parseInt(buffReader.readLine());
			String[] request;

			for (int i = 0; i < requestsNum; i++)
			{
				request = buffReader.readLine().split("[\\s:]");
				if ("APPIONT".equals(request[0]))
				{
					setMeet(request);
				}
				else if ("PRINT".equals(request[0]))
					return;
				else
					throw (new IllegalArgumentException("Bad request"));
			}
			stdIn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void setMeet(String[] request)
	{
		int time = getTime(request[1], request[2], request[3]);
		int duration = Integer.parseInt(request[4]);
		int partySize = Integer.parseInt(request[5]);
		String[] party = new String[partySize];
		for (int j = 0; j < partySize; j++)
			party[j] = request[j + 6];
		meets.add(new Meet(time, duration, party));
	}

	public static int getTime(String day, String hour, String minute)
	{
		int numericDay = Integer.parseInt(day);
		int numericHour = Integer.parseInt(hour);
		int numericMinute = Integer.parseInt(minute);
		return (numericDay * 24 * 60 + numericHour * 60 + numericMinute);
	}
}

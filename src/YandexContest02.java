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
				request = buffReader.readLine().split(" |:");
				if ("APPIONT".equals(request[0]))
					setMeet(request);
				else if ("PRINT".equals(request[0]))
					printPersonMeets(request);
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
		if (canSetMeet(time, duration))
		{
			for (int j = 0; j < partySize; j++)
				party[j] = request[j + 6];
			meets.add(new Meet(time, duration, party));
			System.out.println("OK");
		}
		else
		{
			System.out.println("FAIL");
			for (String person : party)
			{
				System.out.print(person);
				System.out.println();
			}
		}
	}

	public static boolean canSetMeet(int time, int duration)
	{
		for (Meet meet : meets)
		{
			if ((time >= meet.start && time <= meet.start + meet.duration)
			|| (time + duration >= meet.start && time + duration <= meet.start + meet.duration))
				return (false);
		}
		return (true);
	}

	public static void printPersonMeets(String[] request)
	{
		int dayBegin = getTime(request[1], "8", "0");
		int dayDuration = (23 - 8) * 60;
		for (Meet meet : meets)
		{
			if (((meet.start >= dayBegin && meet.start <= dayBegin + dayDuration)
			|| (meet.start + meet.duration >= dayBegin && meet.start + meet.duration <= dayBegin + dayDuration))
			&& isPersonOnMeet(meet, request[2]))
				printMeet(meet);
		}
	}

	public static void printMeet(Meet meet)
	{
		int time = meet.start;
		int minute = time % 60;
		time -= minute;
		int hour = time % 1440 / 60;
		System.out.print(hour + ":" + minute + " " + meet.duration);
		for (String person : meet.party)
		{
			System.out.print(" " + person);
		}
		System.out.println();
	}

	public static boolean isPersonOnMeet(Meet meet, String person)
	{
		for (String party : meet.party)
		{
			if (party.equals(person))
				return (true);
		}
		return (false);
	}

	public static int getTime(String day, String hour, String minute)
	{
		int numericDay = Integer.parseInt(day);
		int numericHour = Integer.parseInt(hour);
		int numericMinute = Integer.parseInt(minute);
		return ((numericDay - 1) * 24 * 60 + numericHour * 60 + numericMinute);
	}
}

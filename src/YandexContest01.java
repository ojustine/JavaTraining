import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class YandexContest01
{
	public static void main(String[] args)
	{
		try
		{
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer strToken;
			int total = 0;

			int n = Integer.parseInt(buffReader.readLine());
			int[] arr = new int[n];

			for (int i = 0; i < n; i++)
			{
				strToken = new StringTokenizer(buffReader.readLine());
				int a = Integer.parseInt(strToken.nextToken());
				int b = Integer.parseInt(strToken.nextToken());
				arr[i] = a * b;
				total += arr[i];
			}
			for (int i = 0; i < n; i++)
			{
				System.out.printf(Locale.US, "%.12f\n", (double) arr[i] / total);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

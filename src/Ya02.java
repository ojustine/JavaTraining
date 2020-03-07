import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ya02
{
	public static void main(String[] args)
	{
		try
		{
			InputStreamReader stdIn = new InputStreamReader(System.in);
			BufferedReader buffReader = new BufferedReader(stdIn);
			StringTokenizer stringTokenizer;
			int n = Integer.parseInt(buffReader.readLine());
			int[][] plates = new int[n][2];
			int result = 0;

			for (int i = 0; i < n; i++)
			{
				stringTokenizer = new StringTokenizer(buffReader.readLine());
				int length = Integer.parseInt(stringTokenizer.nextToken());
				int width = Integer.parseInt(stringTokenizer.nextToken());
				plates[i][0] = length;
				plates[i][1] = width;
			}
			stdIn.close();
			for (int[] plate : plates)
			{
				int not = 0;
				for (int[] check : plates)
				{
					if (plate == check)
						continue;
					if (Math.min(plate[0], plate[1]) >= Math.max(check[0], check[1]))
					{
						not++;
						continue;
					}
					if (plate[0] < check[0] && plate[1] < check[1])
						break;
					if (plate[1] < check[0] && plate[0] < check[1])
						break;
					not++;
				}
				if (not == plates.length - 1)
					result++;
			}
			System.out.println(result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ya01
{
	public static void main(String[] args)
	{
		try
		{
			InputStreamReader stdIn = new InputStreamReader(System.in);
			BufferedReader buffReader = new BufferedReader(stdIn);
			String[] words = buffReader.readLine().split(" ");
			int n = Integer.parseInt(buffReader.readLine());

			for (int i = 0; i < n; i++)//O(n)
			{
				String crypto = buffReader.readLine();
				for (String word : words)//O(n^2)
				{
					if (checkWord(word, crypto))
					{
						System.out.println(word);
						break;
					}
				}
			}

			stdIn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean checkWord(String bookWord, String crypto)
	{
		if (bookWord.length() != crypto.length())
			return (false);
		int shift = shift(bookWord.charAt(0), crypto.charAt(0));
		for (int i = 1; i < bookWord.length(); i++)
		{
			if (shift != shift(bookWord.charAt(i), crypto.charAt(i)))
				return (false);
		}
		return (true);
	}

	public static int shift(char a, char b)
	{
		int shift = a - b;
		if (shift <= -25)
			shift += 26;
		if (shift >= 25)
			shift -= 26;
		return (shift);
	}
}

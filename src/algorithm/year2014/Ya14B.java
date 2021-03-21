package algorithm.year2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Ya14B
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String abra = in.readLine();
		String kadabra = in.readLine();
		String spirit = in.readLine();
		BigInteger key = new BigInteger(in.readLine());

		BigInteger a = BigInteger.valueOf(spiritsCount(abra, spirit));
		BigInteger b = BigInteger.valueOf(spiritsCount(kadabra, spirit));

		String answer;
		if (key.equals(a))
			answer = "0";
		else if (key.equals(b))
			answer = "1";
		else
			answer = solve(a, b, key, 2);
		System.out.println(answer);
	}

	private static int spiritsCount(String spell, String spirit) {
		return (int)Arrays.stream(spell.split("[^a-zA-Z]")).filter(spirit::equalsIgnoreCase).count();
	}

	private static String solve(BigInteger a, BigInteger b, BigInteger key, int depth) {
		if (a.add(b).equals(key))
			return (Integer.toString(depth));
		else if (a.add(b).compareTo(key) > 0)
			return ("Impossible");
		else
			return (solve(b, a.add(b), key, depth + 1));
	}
}

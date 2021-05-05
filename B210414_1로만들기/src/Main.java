import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(br.readLine());
		dp = new int[1000001];
		for (int i = 1; i <= n; i++) {
			dp[i] = Fun(i);
		}

		bw.write(dp[n] + "");
		bw.close();
	}

	private static int Fun(int num) {
		if (num == 1)
			return 0;
		if (dp[num] > 0)
			return dp[num];

		int min = Integer.MAX_VALUE;
		int v = 0;
//		if (num % 5 == 0) {
//			v = Fun(num / 5) + 1;
//			min = (v < min) ? v : min;
//		}
		if (num % 3 == 0) {
			v = Fun(num / 3) + 1;
			min = (v < min) ? v : min;
		}
		if (num % 2 == 0) {
			v = Fun(num / 2) + 1;
			min = (v < min) ? v : min;
		}
		if (num >= 2) {
			v = Fun(num - 1) + 1;
			min = (v < min) ? v : min;
		}
		// num=1
		return min;
	}

}

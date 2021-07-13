import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] num;
	static int[] board = new int[1001];
	static int ans = 0;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		int N = Integer.parseInt(br.readLine());
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			num[n] = Integer.parseInt(st.nextToken());
			int maxboard = 0;
			for (int i = 0; i < n; i++) {
				if (num[i] < num[n]) {
					if (maxboard < board[num[i]])
						maxboard = board[num[i]];
				}
			}
			board[num[n]] = maxboard + 1;
			ans = (board[num[n]] > ans) ? board[num[n]] : ans;
		}

		bw.write(ans + "\n");
		bw.close();
	}

}

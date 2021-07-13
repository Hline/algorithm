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
			// 이전에 입력받은 수들 중에서 현재수(num[n]) 보다 수는 작지만
			// 가장 긴 수열의 수 갯수
			for (int i = 0; i < n; i++) {
				// 현재수(num[n]) 보다 작은 수들 중에서
				if (num[i] < num[n]) {
					// 가장 긴 수열의 수 갯수
					if (maxboard < board[num[i]])
						maxboard = board[num[i]];
				}
			}
			// 찾은 수열에 현재수까지 포함한 수 갯수. 10 20 + 30
			board[num[n]] = maxboard + 1;
			// 가장 긴 수열인지 확인
			ans = (board[num[n]] > ans) ? board[num[n]] : ans;
		}

		bw.write(ans + "\n");
		bw.close();
	}

}

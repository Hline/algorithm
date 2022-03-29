import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, R;
	static int[] MENU;
	static int[] DAT;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			MENU = new int[N * 2];
			for (int n = 0; n < N; n++) {
				int m = Integer.parseInt(st.nextToken());
				MENU[n] = MENU[N + n] = m;
			}
			int start = 0;
			// R + 1(자신) + R
			int end = R * 2 + 1; 
			// key(음식의번호) value(음식의개수)
			DAT = new int[1000001]; 
			String res = "YES";
			for (int i = start; i < end; i++) {
				DAT[MENU[i]]++;
				if (DAT[MENU[i]] > 2) {
					res = "NO";
					break;
				}
			}
			for (int i = end; i < N + (R * 2); i++) {
				DAT[MENU[end]]++;
				DAT[MENU[start]]--;
				if (DAT[MENU[end]] > 2) {
					// start는 -이므로 무관
					res = "NO";
					break;
				}
				end++;
				start++;
			}
			System.out.println("#" + (t + 1) + " " + res);
		}
	}

}

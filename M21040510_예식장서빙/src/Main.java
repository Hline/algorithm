import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("예식장 서빙\\1.in"));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int maxFood = 0; // 중복체크 최대값
			int[] table = new int[N * 2];
			for (int n = 0; n < N; n++) { // 원형문제 해결방식
				int food = Integer.parseInt(st.nextToken());
				table[n] = food;
				table[n + N] = food;
				if (maxFood < food)
					maxFood = food;
			}

			int range = R * 2 + 1;
			int[] chk = new int[maxFood + 2];
			boolean two = true;
			// 3번째 사람부터
			for (int i = 0; i < range; i++) {
				if (++chk[table[i]] > 2) {
					two = false;
					break;
				}
			}

			if (two)
				for (int i = 0; i < N + R; i++) {
					int next = i + range;
					if (next >= N + R)
						break;
					chk[table[i]]--;	//범위의 첫번째 제거
					chk[table[next]]++;	//범위의 마지막+1 추가
					for (int j = i + 1; j <= next; j++) {	//변경된 범위 검증
						two = (chk[table[j]] <= 2);
						if (!two)
							break;
					}
					if (!two)
						break;
				}

			if (two)
				bw.write("#" + (t + 1) + " YES\n");
			else
				bw.write("#" + (t + 1) + " NO\n");
		}
		bw.close();

	}

}

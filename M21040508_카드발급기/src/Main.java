import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("카드발급기\\1.in"));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] cm = new int[N];
			for (int n = 0; n < N; n++) {
				cm[n] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int[] cards = new int[N];
			for (int n = 0; n < N; n++) {
				cards[n] = Integer.parseInt(st.nextToken());
			}

			int correct = 0;
			// 키 정렬
			int[] sorted = cm.clone();
			Arrays.sort(sorted);

			for (int n = 0; n < N; n++) {
				// 학생키 == 정렬된키[카드번호] 검증
				if (cm[n] == sorted[N - cards[n]])
					correct++;
			}

			if (N == correct)
				bw.write("YES\n");
			else
				bw.write("NO\n");
		}
		bw.close();
	}

}

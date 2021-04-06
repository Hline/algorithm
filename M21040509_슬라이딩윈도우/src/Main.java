import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception, Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("슬라이딩 윈도우\\1.in"));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int[] points = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				points[n] = Integer.parseInt(st.nextToken());
			}

			int sum = 0;
			for (int i = 0; i < W; i++) {
				sum += points[i];
			}
			int max = sum;
			int start = 0, end = W - 1;
			for (int i = 0; i < N; i++) {
				if (i + W >= N)
					break;
				sum -= points[i];
				sum += points[i + W];
				if (max < sum) {
					start = i + 1; // 빠진거 다음
					end = i + W;
					max = sum;
				}
			}

			bw.write("#" + (t + 1) + " " + start + " " + end + " " + max + "\n");
		}
		bw.close();
	}

}

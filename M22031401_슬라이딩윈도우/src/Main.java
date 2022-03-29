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
	static int T;
	static int N;
	static int W;
	static int[] point;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			point = new int[N];
			int start = 0;
			int end = W - 1;
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				point[n] = Integer.parseInt(st.nextToken());
			}
			int sum = 0;
			for (int i = start; i <= end; i++) {
				sum += point[i];
			}
			int[] res = new int[3];
			res[0] = sum;
			res[1] = start;
			res[2] = end;
			for (int i = end; i < N - 1; i++) {
				sum -= point[start];
				start++;
				end++;
				sum += point[end];
				if (sum > res[0]) {
					res[0] = sum;
					res[1] = start;
					res[2] = end;
				}

			}
			bw.write("#" + (t + 1) + " " + res[1] + " " + res[2] + " " + res[0] + "\n");

		}
		bw.close();
	}

}

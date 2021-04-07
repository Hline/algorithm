import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int[] vote = new int[N];
		Arrays.fill(vote, 0);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				vote[j] += map[i][j];
			}
		}
		
		int[] min = new int[2]; // 0Á¡¼ö 1ÀÎµ¦½º
		int[] max = new int[2]; // 0Á¡¼ö 1ÀÎµ¦½º
		min[0] = Integer.MAX_VALUE;
		max[0] = Integer.MIN_VALUE;
		for (int j = 0; j < N; j++) {
			if (min[0] > vote[j]) {
				min[0] = vote[j];
				min[1] = j;
			}
			if (max[0] < vote[j]) {
				max[0] = vote[j];
				max[1] = j;
			}
		}
		bw.write(max[1] + " " + min[1]);
		bw.close();
	}
}
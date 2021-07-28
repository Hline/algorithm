import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int[] A;
	static ArrayList<Integer> board = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		board.add(0);
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
			if (board.get(board.size() - 1) < A[n])
				board.add(A[n]);
			else {
				// 가장 큰수보다 작을 경우 기존 수열을 변경
				int search = BinarySearch(A[n]);
				board.set(search, A[n]);
			}

		}
		bw.write((board.size() - 1) + "\n");
		bw.close();
	}

	// Lower Bound
	// 찾을 수와 일치하는 값이 있을 수도 있고 없을 수도 있음
	private static int BinarySearch(int n) {
		int left = 0;
		int right = board.size() - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (n <= board.get(mid)) {
				// 0번째값은 0으로 탐색기준을 위한 허수이므로 mid 값으로 허용하지 않는다
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		// 최종 mid값
		return (left + right) / 2;
	}
}
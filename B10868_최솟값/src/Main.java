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
	static int N;
	static int M;
	static int NN;
	static int[] input;
	static int[] treeMin;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		NN = 1;
		while (N > NN) {
			NN *= 2;
		}
		input = new int[N];
		treeMin = new int[NN * 2];
		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(br.readLine());
		}
		InitMin(0, N - 1, 1);
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			bw.write(QueryMin(0, N - 1, 1, a, b) + "\n");
		}
		bw.close();

	}

	// 최소값
	private static int InitMin(int start, int end, int now) {
		// 리프노드 (1~1)
		if (start == end)
			return treeMin[now] = input[start];

		int mid = (start + end) / 2;
		int a = InitMin(start, mid, 2 * now);
		int b = InitMin(mid + 1, end, 2 * now + 1);
		return treeMin[now] = (a < b) ? a : b;
	}

	private static int QueryMin(int start, int end, int now, int left, int right) {
		// 탐색하는 트리범위 내에 탐색 대상 노드(left,right)가 없을 경우 (범위 밖)
		// (1) left~right start~end (2) start~end left~right
		if (right < start || end < left)
			return Integer.MAX_VALUE;
		// 탐색하는 트리범위와 탐색 대상 노드(left,right)가 겹칠 경우 경우 (범위 내)
		// left start~end right
		if (left <= start && end <= right)
			return treeMin[now];

		int mid = (start + end) / 2;
		int a = QueryMin(start, mid, 2 * now, left, right);
		int b = QueryMin(mid + 1, end, 2 * now + 1, left, right);
		return (a < b) ? a : b;
	}

	private static void UpdateMin(int start, int end, int now, int target, int diff) {
		// 탐색하는 트리범위 내에 값이 변경되는 노드(target)가 없을 경우 (범위 밖)
		// (1) target start~end (2) start~end target
		if (target < start || end < target)
			return;
		treeMin[now] += diff;
		
		// 리프노드 (1~1)
		if (start == end)
			return;

		int mid = (start + end) / 2;
		UpdateMin(start, mid, 2 * now, target, diff);
		UpdateMin(mid + 1, end, 2 * now + 1, target, diff);
	}
}

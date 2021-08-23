import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int M;
	static int K;
	static int NN;
	static long[] input;
	static long[] tree;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		NN = 1;
		while (N > NN) {
			NN *= 2;
		}
		input = new long[N];
		tree = new long[NN * 2];
		for (int n = 0; n < N; n++) {
			input[n] = Long.parseLong(br.readLine());
		}
		Init(0, N - 1, 1);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				--b;
				long diff = c - input[b];
				input[b] = c;
				Update(0, N - 1, 1, b, diff);
			}
			if (a == 2) {
				--b;
				--c;
				bw.write(Query(0, N - 1, 1, b, (int) c) + "\n");
			}
		}
		bw.close();

	}

	private static long Init(int start, int end, int now) {
		// 리프노드 (1~1)
		if (start == end)
			return tree[now] = input[start];

		int mid = (start + end) / 2;
		return tree[now] = Init(start, mid, 2 * now) + Init(mid + 1, end, 2 * now + 1);
	}

	private static void Update(int start, int end, int now, int target, long diff) {
		// 탐색하는 트리범위 내에 값이 변경되는 노드(target)가 없을 경우 (범위 밖)
		// (1) target start~end (2) start~end target
		if (target < start || end < target)
			return;
		tree[now] += diff;
		
		// 리프노드 (1~1)
		if (start == end)
			return;

		int mid = (start + end) / 2;
		Update(start, mid, 2 * now, target, diff);
		Update(mid + 1, end, 2 * now + 1, target, diff);
	}

	private static long Query(int start, int end, int now, int left, int right) {
		// 탐색하는 트리범위 내에 탐색 대상 노드(left,right)가 없을 경우 (범위 밖)
		// (1) left~right start~end (2) start~end left~right
		if (right < start || end < left)
			return 0;
		
		// 탐색하는 트리범위와 탐색 대상 노드(left,right)가 겹칠 경우 경우 (범위 내)
		// left start~end right
		if (left <= start && end <= right)
			return tree[now];

		int mid = (start + end) / 2;
		return Query(start, mid, 2 * now, left, right) + Query(mid + 1, end, 2 * now + 1, left, right);
	}

}

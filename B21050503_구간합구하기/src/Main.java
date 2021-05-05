import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static long[] arr;
	static long[] tree;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new long[N];
		tree = new long[N * 5];
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		initTree(0, N - 1, 1);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			if (a == 1) {
				long c = Long.parseLong(st.nextToken());
				long diff = c - arr[b];
				arr[b] = c;		// 원본 수배열도 변경
				updateTree(0, N - 1, 1, b, diff);
			}
			if (a == 2) {
				int c = Integer.parseInt(st.nextToken()) - 1;
				long res = findTree(0, N - 1, 1, b, c);
				bw.write(res + "\n");
			}
		}
		bw.close();
	}

	private static long findTree(int start, int end, int now, int left, int right) {
		// findTree: 지정된 범위 내의 합 탐색
		// start, end: 탐색하려는 트리 내 노드의 범위
		// now: 현재 탐색대상 노드 인덱스
		// left, right: 입력된 값의 탐색하려는 범위 (리프노드의 범위)
		if (left > end || right < start)	// 탐색하려는 값의 범위가 트리를 벗어난 경우
			return 0;
		if (left <= start && right >= end)	// 탐색하려는 값의 범위가 트리 내일 경우
			return tree[now];				// 대표 값인 부모노드 사용

		int mid = (start + end) / 2;
		long resL = findTree(start, mid, now * 2, left, right);			// 왼쪽노드 탐색
		long resR = findTree(mid + 1, end, now * 2 + 1, left, right);	// 오른쪽 노드 탐색
		return resL + resR;	// 구간합
	}

	private static void updateTree(int start, int end, int now, int idx, long diff) {
		// updateTree: 지정된 값과 관련된 모든 노드의 값 변경
		// start, end: 트리 내 탐색하려는 노드의 범위
		// now: 현재 탐색대상 노드 인덱스
		// idx: 변경하려는 값의 인덱스
		// diff: 변경값과 원본값의 차이. 구간합이므로 관련된 노드에 차이만큼 더 해줌.
		if (idx < start || idx > end)	// 변경하려는 값의 인덱스가 탐색하려는 트리의 범위 내에 없는 경우
			return;
		tree[now] += diff;	// 구간합이므로 관련된 노드에 차이만큼 더함.
		if (start == end)	// 리프노드일 경우
			return;

		int mid = (start + end) / 2;
		updateTree(start, mid, now * 2, idx, diff);		// 왼쪽 트리 변경
		updateTree(mid + 1, end, now * 2 + 1, idx, diff);	// 오른쪽 트리 변경
	}

	private static long initTree(int start, int end, int now) {
		// initTree: 트리 값 세팅
		// start, end: 트리 내의 노드 범위
		// now: 현재 값을 변경하려는 대상 노드 인덱스
		if (start == end)	// 리프노드
			return tree[now] = arr[start];

		int mid = (start + end) / 2;
		long resL = initTree(start, mid, now * 2); // 왼쪽탐색
		long resR = initTree(mid + 1, end, now * 2 + 1); // 오른쪽탐색
		return tree[now] = resL + resR;	// 구간합
	}

}

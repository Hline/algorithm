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
	static int[] arr;
	static int[] tree;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		tree = new int[N * 4];
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		initTree(0, N - 1, 1);
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			bw.write(findTree(0, N - 1, 1, left, right) + "\n");
		}
		bw.close();
	}

	// start, end: 트리의 시작범위, 끝범위
	// now: 트리에서 현재 탐색하는 노드 인덱스
	// left, right: 입력된 값의 탐색하려는 범위 (리프노드의 범위)
	private static int findTree(int start, int end, int now, int left, int right) {
		// TODO Auto-generated method stub
		if (left > end || right < start)	//탐색범위가 트리범위를 벗어날 경우
			return Integer.MAX_VALUE;
		if (left <= start && right >= end)	//탐색범위가 트리 내일 경우 범위에 해당하는 부모노드 반환
			return tree[now];
		
		// 리프노드까지 탐색
		int mid = (start + end) / 2;
		int resL = findTree(start, mid, now * 2, left, right);
		int resR = findTree(mid + 1, end, now * 2 + 1, left, right);
		return resL < resR ? resL : resR;
	}

	// start, end: 트리의 시작범위, 끝범위
	// now: 트리에서 현재 탐색하는 노드 인덱스
	private static int initTree(int start, int end, int now) {
		// TODO Auto-generated method stub
		if (start == end) // 리프노드
			return tree[now] = arr[start];
		
		// 리프노드가 아닐 경우 리프노드까지 탐색
		int mid = (start + end) / 2;
		int resL = initTree(start, mid, now * 2);
		int resR = initTree(mid + 1, end, now * 2 + 1);
		return tree[now] = resL < resR ? resL : resR;	// 최솟값 반환
	}

}

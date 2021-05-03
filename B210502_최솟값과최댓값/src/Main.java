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
	static int[] minTree;
	static int[] maxTree;
	static int[] num;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("in")); // 상대경로. 프로젝트\in 의 파일을 읽어들임
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		num = new int[N];
		minTree = new int[N * 4];
		maxTree = new int[N * 4];
		for (int n = 0; n < N; n++) {
			num[n] = Integer.parseInt(br.readLine());
		}
		
		initMin(0, N - 1, 1);	// 트리 전체 초기화
		initMax(0, N - 1, 1);
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			// 트리 전체에서 왼쪽~오른쪽 범위 탐색
			bw.write(findMin(0, N - 1, 1, left, right) + " " + findMax(0, N - 1, 1, left, right) + "\n");
		}

		bw.close();

	}

	private static int findMin(int start, int end, int idx, int left, int right) {
		if (left > end || right < start)	// 탐색범위를 벗어난 경우
			return Integer.MAX_VALUE;
		if (left <= start && right >= end)	// 범위 내인 경우
			return minTree[idx];

		int mid = (start + end) / 2;	// 절반씩 탐색
		int resLeft = findMin(start, mid, 2 * idx, left, right);
		int resRight = findMin(mid + 1, end, 2 * idx + 1, left, right);
		return resLeft < resRight ? resLeft : resRight;

	}

	private static int initMin(int start, int end, int idx) {
		if (start == end)	// 리프노드일 경우
			return minTree[idx] = num[start];
		
		int mid = (start + end) / 2;	// 절반씩 탐색
		int resLeft = initMin(start, mid, 2 * idx);
		int resRight = initMin(mid + 1, end, 2 * idx + 1);
		return minTree[idx] = resLeft < resRight ? resLeft : resRight;
	}

	private static int findMax(int start, int end, int idx, int left, int right) {
		if (left > end || right < start)
			return Integer.MIN_VALUE;
		if (left <= start && right >= end)
			return maxTree[idx];

		int mid = (start + end) / 2;
		int resLeft = findMax(start, mid, 2 * idx, left, right);
		int resRight = findMax(mid + 1, end, 2 * idx + 1, left, right);
		return resLeft > resRight ? resLeft : resRight;

	}

	private static int initMax(int start, int end, int idx) {
		if (start == end)
			return maxTree[idx] = num[start];
		int mid = (start + end) / 2;
		int resLeft = initMax(start, mid, 2 * idx);
		int resRight = initMax(mid + 1, end, 2 * idx + 1);
		return maxTree[idx] = resLeft > resRight ? resLeft : resRight;
	}

}

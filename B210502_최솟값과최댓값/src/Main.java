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
//		br = new BufferedReader(new FileReader("in")); // �����. ������Ʈ\in �� ������ �о����
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		num = new int[N];
		minTree = new int[N * 4];
		maxTree = new int[N * 4];
		for (int n = 0; n < N; n++) {
			num[n] = Integer.parseInt(br.readLine());
		}
		
		initMin(0, N - 1, 1);	// Ʈ�� ��ü �ʱ�ȭ
		initMax(0, N - 1, 1);
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			// Ʈ�� ��ü���� ����~������ ���� Ž��
			bw.write(findMin(0, N - 1, 1, left, right) + " " + findMax(0, N - 1, 1, left, right) + "\n");
		}

		bw.close();

	}

	private static int findMin(int start, int end, int idx, int left, int right) {
		if (left > end || right < start)	// Ž�������� ��� ���
			return Integer.MAX_VALUE;
		if (left <= start && right >= end)	// ���� ���� ���
			return minTree[idx];

		int mid = (start + end) / 2;	// ���ݾ� Ž��
		int resLeft = findMin(start, mid, 2 * idx, left, right);
		int resRight = findMin(mid + 1, end, 2 * idx + 1, left, right);
		return resLeft < resRight ? resLeft : resRight;

	}

	private static int initMin(int start, int end, int idx) {
		if (start == end)	// ��������� ���
			return minTree[idx] = num[start];
		
		int mid = (start + end) / 2;	// ���ݾ� Ž��
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

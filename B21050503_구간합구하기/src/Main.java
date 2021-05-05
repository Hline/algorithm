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
				arr[b] = c;		// ���� ���迭�� ����
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
		// findTree: ������ ���� ���� �� Ž��
		// start, end: Ž���Ϸ��� Ʈ�� �� ����� ����
		// now: ���� Ž����� ��� �ε���
		// left, right: �Էµ� ���� Ž���Ϸ��� ���� (��������� ����)
		if (left > end || right < start)	// Ž���Ϸ��� ���� ������ Ʈ���� ��� ���
			return 0;
		if (left <= start && right >= end)	// Ž���Ϸ��� ���� ������ Ʈ�� ���� ���
			return tree[now];				// ��ǥ ���� �θ��� ���

		int mid = (start + end) / 2;
		long resL = findTree(start, mid, now * 2, left, right);			// ���ʳ�� Ž��
		long resR = findTree(mid + 1, end, now * 2 + 1, left, right);	// ������ ��� Ž��
		return resL + resR;	// ������
	}

	private static void updateTree(int start, int end, int now, int idx, long diff) {
		// updateTree: ������ ���� ���õ� ��� ����� �� ����
		// start, end: Ʈ�� �� Ž���Ϸ��� ����� ����
		// now: ���� Ž����� ��� �ε���
		// idx: �����Ϸ��� ���� �ε���
		// diff: ���氪�� �������� ����. �������̹Ƿ� ���õ� ��忡 ���̸�ŭ �� ����.
		if (idx < start || idx > end)	// �����Ϸ��� ���� �ε����� Ž���Ϸ��� Ʈ���� ���� ���� ���� ���
			return;
		tree[now] += diff;	// �������̹Ƿ� ���õ� ��忡 ���̸�ŭ ����.
		if (start == end)	// ��������� ���
			return;

		int mid = (start + end) / 2;
		updateTree(start, mid, now * 2, idx, diff);		// ���� Ʈ�� ����
		updateTree(mid + 1, end, now * 2 + 1, idx, diff);	// ������ Ʈ�� ����
	}

	private static long initTree(int start, int end, int now) {
		// initTree: Ʈ�� �� ����
		// start, end: Ʈ�� ���� ��� ����
		// now: ���� ���� �����Ϸ��� ��� ��� �ε���
		if (start == end)	// �������
			return tree[now] = arr[start];

		int mid = (start + end) / 2;
		long resL = initTree(start, mid, now * 2); // ����Ž��
		long resR = initTree(mid + 1, end, now * 2 + 1); // ������Ž��
		return tree[now] = resL + resR;	// ������
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T;
	static int N;
	static int M;
	static int K;
	static int[] parent;
	static boolean[] virusYN;
	static int[] group;
	static int[] virus;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			virusYN = new boolean[N]; // �� ����� ���̷��� ����
			group = new int[N]; // �׷캰 ��ǻ�� ��
			virus = new int[N]; // �׷캰 ���̷��� ��
			parent = new int[N]; // ������� �θ��� ����
			// ���� ��Ʈ��ũ���� �׷�ȭ
			for (int n = 0; n < N; n++) {
				parent[n] = n;
			}

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				Union(a, b);
			}

			// �� ��庰 ���� ���� ����
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				virusYN[a] = true;
			}

			for (int n = 0; n < N; n++) {
				// �Ҽ� ��Ʈ��ũ ã��
				int apar = Find(n);
				// ���� ��Ʈ��ũ �� ��ǻ�� ��
				group[apar]++;
				// ���� ��Ʈ��ũ �� ���� ��
				if (virusYN[n])
					virus[apar]++;
			}

			// ���� ���
			// 3ȸ ���� ġ���ؾ���
			// �׷캰 ���� ���̽�
			// �Ϲ����� ���̽�
			// 3 2 1 / 1 2 2 / 0 / 2 2 2
			// Ư�� ���̽� (��1�� �ʿ�)
			// 1 1 2 / 1 1 1

			// ���̷����� ���� ��ǻ�� ��
			int safe = N;
			// �Ϲ����� ���̽� ó���� ���� �׷캰 �ִ��
			int three = 0, two = 0, one = 0;
			// Ư�����̽��� �׷캰 ��ǻ�� �� (reverseOrder ���� ����)
			Queue<Integer> oneGroup = new PriorityQueue<>(Comparator.reverseOrder());
			for (int n = 0; n < N; n++) {
				// �׷��� ������� Pass
				if (group[n] == 0)
					continue;
				// ���̷����� ���� ��ǻ�� ��
				if (virus[n] > 0) {
					safe -= group[n];
				}
				// �� �׷쳻�� 3���� �Ѿ�� ���� ����
				if (virus[n] > 3) {
					continue;
				}
				// �� ���̷��� ������ ���� ��ǻŸ ���� ���� �׷�
				if (virus[n] == 3)
					three = (three > group[n]) ? three : group[n];
				if (virus[n] == 2)
					two = (two > group[n]) ? two : group[n];
				if (virus[n] == 1) {
					one = (one > group[n]) ? one : group[n];
					// Ư�����̽� ó���� ���� ���̷����� 1�� ��� �׷� �߰�
					oneGroup.add(group[n]);
				}
			}

			// �Ϲ����� ���̽� ġ���� �׷� ����
			// 3 vs 2+1
			int maxCnt = (three > (two + one) ? three : two + one);
			// Ư�� ���̽� ó��
			// ���̷��� ���� 1�� �׷��� 1�� �ʰ��� ���
			if (oneGroup.size() > 1) {
				// 112
				int a = oneGroup.poll();
				int b = oneGroup.poll();
				maxCnt = (maxCnt > a + b) ? maxCnt : a + b;

				if (!oneGroup.isEmpty()) {
					// 111
					int c = oneGroup.poll();
					maxCnt = (maxCnt > a + b + c) ? maxCnt : a + b + c;
				}
			}

			// ���̷����� ���� ��ǻ�� �� + ġ���� ��ǻ�� ��
			int ans = safe + maxCnt;
			bw.write("#" + (t + 1) + " " + ans + "\n");
		}
		bw.close();
	}

	// a�� b�� ���� �׷� ����
	private static void Union(int a, int b) {
		int apar = Find(a);
		int bpar = Find(b);
		if (apar == bpar)
			return;
		parent[bpar] = apar;
	}

	// a�� ���� �׷��� ã�� �ӵ� ����ȭ�� ���� ��� ����
	private static int Find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = Find(parent[a]);
	}

}

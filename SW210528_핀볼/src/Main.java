import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int t;
	int s;

	public Node(int to, int sum) {
		t = to;
		s = sum;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		int T = Integer.parseInt(br.readLine());	// �׽�Ʈ���̽�
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// ���� ���� �� �ִ� ����
			int Q = Integer.parseInt(st.nextToken());	// ���� Ƚ��
			int[] cost = new int[N];	// ������ ����
			ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				cost[n] = Integer.parseInt(st.nextToken());
				map.add(new ArrayList<Integer>());
			}
			int[] parent = new int[N];	// �ش� ������ ����Ǿ� ���� �������� ���� ����
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				if (n == 0)
					continue;
				parent[n] = to;
				// �ֻ���(1��)���� N���� Ž���ϸ� ���� �����հ� �ܰ踦 ���ϴ� �� �����̹Ƿ� �ܹ��� �׷���
				map.get(to).add(n);
			}

			int[] sum = new int[N];		// N�������� ������ ������
			int[] step = new int[N];	// N���������� �ܰ�
			Queue<Integer> que = new LinkedList<Integer>();
			boolean[] visited = new boolean[N];
			sum[0] = cost[0];	// 1������ ������ 1�� ������ ���Ե�
			step[0] = 1;
			que.add(0);

			// �ֻ���(1��)���� N���� Ž���ϸ� ���� �����հ� �ܰ踦 ���ϱ�
			while (!que.isEmpty()) {
				int now = que.poll();
				if (visited[now])
					continue;
				visited[now] = true;
				for (int next : map.get(now)) {
					int sumNext = sum[now] + cost[next];
					if (sum[next] != 0 && sum[next] <= sumNext)
						continue;
					sum[next] = sumNext;
					step[next] = step[now] + 1;
					que.add(next);
				}
			}

			int msum = 0;	// �� ������ ����
			int rsum = 0;	// ���� ������ ����
			for (int q = 0; q < Q; q++) {
				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;

				// ���� �Ʒ��� �ִ� �������� ���ʷ� Ž���Ͽ� ���� ���� ã��
				int maxStep = step[m] > step[r] ? step[m] : step[r];
				int mpar = m;
				int rpar = r;
				while (maxStep >= 0) {
					if (mpar == rpar) {
						break;
					}
					if (maxStep <= step[m]) {
						mpar = parent[mpar];
					}
					if (maxStep <= step[r]) {
						rpar = parent[rpar];
					}
					maxStep--;
				}

				// �������� �ʰ� ������ ���� �������� ���� ����
				// (���߿� �������� ���� �� ������ ������ ���� �� ����)
				int mpoint = sum[m];
				int rpoint = sum[r];
				// 1.1. �� ���� �ܰ谡 ���� ���: ����������ʹ� �Ѵ� ����X
				// (���� �̵��ϴٰ� �� ������ ���� �����ϸ� ���� �ε��� ������ ������ ������)
				if (step[m] == step[r]) {
					mpoint -= sum[mpar];
					rpoint -= sum[rpar];
				}
				// 1.1. �� ���� �ܰ谡 �ٸ� ���: �ܰ谡 ���� ���� ����������� ����X
				if (step[m] > step[r]) {
					mpoint -= sum[mpar];
				}
				if (step[m] < step[r]) {
					rpoint -= sum[rpar];
				}

				msum += mpoint;
				rsum += rpoint;
			}
			bw.write("#" + (t + 1) + " " + msum + " " + rsum + "\n");
		}
		bw.close();
	}

}

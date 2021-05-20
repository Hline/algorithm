import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class LockInfo implements Comparable<LockInfo> {
	int r;
	int l;
	int v;

	public LockInfo(int ring, int line, int value) {
		r = ring;
		l = line;
		v = value;
	}

	@Override
	public int compareTo(LockInfo r) {
		if (this.r > r.r)
			return -1;
		if (this.r < r.r)
			return 1;
		if (this.v < r.v)
			return -1;
		if (this.v > r.v)
			return 1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // ring // column
			int K = Integer.parseInt(st.nextToken()); // line // row
			ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
			int[][] dist = new int[K][N];
			for (int n = 0; n < N; n++) {
				map.add(new ArrayList<>());
			}
			for (int k = 0; k < K; k++) {
				String l = br.readLine();
				for (int n = 0; n < N; n++) {
					dist[k][n] = Integer.MAX_VALUE;
					if (l.charAt(n) == '1') {
						map.get(n).add(k);
					}
				}
			}
			Queue<LockInfo> que = new PriorityQueue<LockInfo>();
			int[] dr = { -1, +1 };	// ��, �Ʒ� ����
			// ���� �ڹ��躰��
			for (int n = 0; n < N; n++) {
				// ���� 1�� ��ġ�� ���� �߰�. 1�� ������.
				for (int i = 0; i < map.get(n).size(); i++) {
					int l = map.get(n).get(i);
					que.add(new LockInfo(n, l, 0));
					dist[l][n] = 0;
				}
				
				boolean[] visited = new boolean[K];	// �� �ڹ����� ĭ���� Ȯ��
				while (!que.isEmpty()) {
					LockInfo now = que.poll();
					if (visited[now.l])
						continue;
					visited[now.l] = true;
					for (int i = 0; i < dr.length; i++) {
						int nextL = now.l + dr[i];
						int nextV = now.v + 1;	//�� ĭ�� �ű�
						if (nextL < 0)		// �� ���� �� �Ʒ���
							nextL += K;
						if (nextL > K - 1)	// �� �Ʒ��� �� ���� �̵� ����
							nextL -= K;
						if (dist[nextL][n] <= nextV)
							continue;
						dist[nextL][n] = nextV;
						que.add(new LockInfo(n, nextL, nextV));
					}
				}
			}

			int minSum = Integer.MAX_VALUE;
			for (int k = 0; k < K; k++) {	// �� �̵����� ����ġ
				int sum = 0;
				for (int n = 0; n < N; n++) {
					sum += dist[k][n];
				}
				minSum = minSum < sum ? minSum : sum;
			}

			bw.write("#" + (t + 1) + " " + minSum + "\n");
		}
		bw.close();
	}
}

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

class Node implements Comparable<Node> {
	int t;
	int v;

	public Node(int to, int value) {
		t = to;
		v = value;
	}

	@Override
	public int compareTo(Node r) {	// �ּ� ��� �켱
		if (v < r.v)
			return -1;
		if (v > r.v)
			return 1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static ArrayList<ArrayList<Node>> map;
	static int N;
	static int X;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());		// ������
		int M = Integer.parseInt(st.nextToken());	// ���μ�
		X = Integer.parseInt(st.nextToken()) - 1;	// ��Ƽ ������
		int[] dist = new int[N];					// �ҿ���
		
		map = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			map.add(new ArrayList<Node>());
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken()) - 1;	// ����
			int t = Integer.parseInt(st.nextToken()) - 1;	// ����
			int v = Integer.parseInt(st.nextToken());
			map.get(f).add(new Node(t, v));
		}

		dist = Search(new Node(X, 0));	// ��Ƽ ������(X) -> �� ���� ���. (�Ͱ�)

		int max = 0;
		for (int n = 0; n < N; n++) {	// �� ���� -> ��Ƽ ������(X) ���. (����)
			if (n == X)
				continue;
			
			// ����� -> ��� ���������� �ּ� ��� �� ��Ƽ ������(X) ��� ����
			dist[n] += Search(new Node(n, 0))[X];
			max = max > dist[n] ? max : dist[n];	// ���� ���� �ɸ� ���
		}

		bw.write(max + "\n");
		bw.close();
	}

	// ���������� ��� �������� ���� �ּ� ��� Ž��
	// start: ��߸���, ��������� 0
	private static int[] Search(Node start) {
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);

		boolean[] visited = new boolean[N];
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		que.add(start);
		dist[start.t] = start.v;

		while (!que.isEmpty()) {
			Node now = que.poll();
			if (visited[now.t])
				continue;
			visited[now.t] = true;

			for (int i = 0; i < map.get(now.t).size(); i++) {
				Node next = map.get(now.t).get(i);
				if (dist[next.t] <= dist[now.t] + next.v)
					continue;
				dist[next.t] = dist[now.t] + next.v;
				que.add(new Node(next.t, dist[next.t]));
			}
		}
		return dist;
	}
}

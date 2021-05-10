import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int e;	// ������ ����(b)
	long c;	// ���

	public Node(int end, long cost) {
		// TODO Auto-generated constructor stub
		e = end;
		c = cost;
	}

	@Override
	public int compareTo(Node r) {	// priority que���� ����� �� ���� ����
		if (c < r.c)
			return -1;
		if (c > r.c)
			return 1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>();
		boolean[] visited = new boolean[V];	// �ش� ���� Ž������ ó��
		long[] cost = new long[V];			// �� ������ ���
		for (int v = 0; v < V; v++) {
			map.add(new ArrayList<Node>());
			cost[v] = Long.MAX_VALUE;		// �ּҺ���̹Ƿ� max�� ����
		}
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			long c = Long.parseLong(st.nextToken());
			map.get(a).add(new Node(b, c));		// ����� �׷���
			map.get(b).add(new Node(a, c));
		}

		// Ž�� ���� 0(1��) ����
		Queue<Node> que = new PriorityQueue<Node>();
		que.add(new Node(0, 0));
		cost[0] = 0;
		int res = 0;
		while (!que.isEmpty()) {
			Node now = que.poll();
			if (visited[now.e])		// ��� Ž�� ���� Ȯ��
				continue;
			visited[now.e] = true;
			res += now.c;			// ������ ��� ��
			for (int i = 0; i < map.get(now.e).size(); i++) {
				Node next = map.get(now.e).get(i);
				if (cost[next.e] <= next.c)		// ������ Ž���� ��庸�� ����� Ŭ ��� �н� 
					continue;					// ��� que�� ���� ���� ���� ��常 ������ ���ʿ��� ������ ���� �ʱ� ���� �߰�
				cost[next.e] = next.c;
				que.add(next);					// ���������� ���� Ž���� ����
			}
		}

		bw.write(res + " ");
		bw.close();

	}

}

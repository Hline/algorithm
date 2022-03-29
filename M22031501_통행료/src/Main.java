import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 Djikstra
class Node implements Comparable<Node> {
	int to;
	int price;

	Node(int t, int p) {
		to = t;
		price = p;
	}

	@Override
	public int compareTo(Node r) {
		if (price < r.price)
			return -1;
		if (price > r.price)
			return 1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K;
	static int start, end;
	static int[] P;
	static ArrayList<ArrayList<Node>> map;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = new int[K];
		map = new ArrayList<ArrayList<Node>>();
		for (int n = 0; n < N + 1; n++)
			map.add(new ArrayList<Node>());
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map.get(f).add(new Node(t, c));
			map.get(t).add(new Node(f, c));
		}
		for (int k = 0; k < K; k++)
			P[k] = Integer.parseInt(br.readLine());
		for (int k = 1; k < K; k++)
			P[k] += P[k - 1];

		for (int k = -1; k < K; k++) {
			int p = (k < 0) ? 0 : P[k];
			int[] dist = new int[N + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			boolean[] visited = new boolean[N + 1];
			PriorityQueue<Node> que = new PriorityQueue<Node>();
			que.add(new Node(start, 0));
			while (!que.isEmpty()) {
				Node now = que.poll();
				if (visited[now.to])
					continue;
				visited[now.to] = true;
				for (int i = 0; i < map.get(now.to).size(); i++) {
					int nextt = map.get(now.to).get(i).to;
					int nextp = map.get(now.to).get(i).price + now.price + p;
					if (dist[nextt] >= nextp)
						dist[nextt] = nextp;
					que.add(new Node(nextt, nextp));
				}
			}
			System.out.println(dist[end]);
		}

	}

}

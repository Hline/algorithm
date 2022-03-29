import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 Djikstra
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T;
	static int N, M, F;
	static int S, G, H;
	static ArrayList<ArrayList<Node>> map;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			F = Integer.parseInt(st.nextToken());
			int[] finish = new int[F];
			map = new ArrayList<ArrayList<Node>>();
			for (int n = 0; n < N + 1; n++) {
				map.add(new ArrayList<Node>());
			}
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map.get(a).add(new Node(b, d));
				map.get(b).add(new Node(a, d));
			}
			for (int f = 0; f < F; f++) {
				int x = Integer.parseInt(br.readLine());
				finish[f] = x;
			}

			// S에서 모든 노드
			int[] dist = Djikstra(S);
			// H ->
			int[] distH = Djikstra(H);
			// G ->
			int[] distG = Djikstra(G);

			Arrays.sort(finish);

			for (int f = 0; f < F; f++) {
				if (distG[finish[f]] == Integer.MAX_VALUE || distH[finish[f]] == Integer.MAX_VALUE) {
					continue;
				}
				int sgh = dist[G] + distG[H] + distH[finish[f]];
				int shg = dist[H] + distH[G] + distG[finish[f]];
				int s = dist[finish[f]];
				if (s >= sgh || s >= shg) {
					System.out.print(finish[f]+" ");
				}

			}
			System.out.println();
		}

	}

	private static int[] Djikstra(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		que.add(new Node(start, 0));
		dist[start] = 0;

		while (!que.isEmpty()) {
			Node now = que.poll();
			if (visited[now.to])
				continue;
			visited[now.to] = true;

			for (int i = 0; i < map.get(now.to).size(); i++) {
				int nextt = map.get(now.to).get(i).to;
				int nextd = map.get(now.to).get(i).dist + now.dist;
				if (dist[nextt] >= nextd)
					dist[nextt] = nextd;
				que.add(new Node(nextt, nextd));
			}
		}
		return dist;
	}

}

class Node implements Comparable<Node> {
	int to;
	int dist;

	Node(int t, int d) {
		to = t;
		dist = d;
	}

	@Override
	public int compareTo(Node r) {
		if (dist < r.dist)
			return -1;
		if (dist > r.dist)
			return 1;
		return 0;
	}
}

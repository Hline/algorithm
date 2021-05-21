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
	public int compareTo(Node r) {	// 최소 비용 우선
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
		N = Integer.parseInt(st.nextToken());		// 마을수
		int M = Integer.parseInt(st.nextToken());	// 도로수
		X = Integer.parseInt(st.nextToken()) - 1;	// 파티 목적지
		int[] dist = new int[N];					// 소요비용
		
		map = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			map.add(new ArrayList<Node>());
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken()) - 1;	// 시작
			int t = Integer.parseInt(st.nextToken()) - 1;	// 도착
			int v = Integer.parseInt(st.nextToken());
			map.get(f).add(new Node(t, v));
		}

		dist = Search(new Node(X, 0));	// 파티 목적지(X) -> 각 마을 비용. (귀가)

		int max = 0;
		for (int n = 0; n < N; n++) {	// 각 마을 -> 파티 목적지(X) 비용. (참석)
			if (n == X)
				continue;
			
			// 출발지 -> 모든 마을까지의 최소 비용 중 파티 목적지(X) 비용 추출
			dist[n] += Search(new Node(n, 0))[X];
			max = max > dist[n] ? max : dist[n];	// 가장 오래 걸린 비용
		}

		bw.write(max + "\n");
		bw.close();
	}

	// 시작점부터 모든 마을까지 가는 최소 비용 탐색
	// start: 출발마을, 시작점비용 0
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

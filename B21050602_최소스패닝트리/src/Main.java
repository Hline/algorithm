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
	int e;	// 목적지 정점(b)
	long c;	// 비용

	public Node(int end, long cost) {
		// TODO Auto-generated constructor stub
		e = end;
		c = cost;
	}

	@Override
	public int compareTo(Node r) {	// priority que에서 사용할 비교 기준 정의
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
		boolean[] visited = new boolean[V];	// 해당 정점 탐색여부 처리
		long[] cost = new long[V];			// 각 정점별 비용
		for (int v = 0; v < V; v++) {
			map.add(new ArrayList<Node>());
			cost[v] = Long.MAX_VALUE;		// 최소비용이므로 max로 세팅
		}
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			long c = Long.parseLong(st.nextToken());
			map.get(a).add(new Node(b, c));		// 양방향 그래프
			map.get(b).add(new Node(a, c));
		}

		// 탐색 시작 0(1번) 정점
		Queue<Node> que = new PriorityQueue<Node>();
		que.add(new Node(0, 0));
		cost[0] = 0;
		int res = 0;
		while (!que.isEmpty()) {
			Node now = que.poll();
			if (visited[now.e])		// 노드 탐색 여부 확인
				continue;
			visited[now.e] = true;
			res += now.c;			// 정점별 비용 합
			for (int i = 0; i < map.get(now.e).size(); i++) {
				Node next = map.get(now.e).get(i);
				if (cost[next.e] <= next.c)		// 이전에 탐색한 노드보다 비용이 클 경우 패스 
					continue;					// 없어도 que를 통해 가잗 작은 노드만 뽑지만 불필요한 정점을 담지 않기 위해 추가
				cost[next.e] = next.c;
				que.add(next);					// 목적지이자 다음 탐색할 정점
			}
		}

		bw.write(res + " ");
		bw.close();

	}

}

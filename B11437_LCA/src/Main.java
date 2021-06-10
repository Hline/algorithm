import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int t;
	int c;

	public Node(int to, int cost) {
		t = to;
		c = cost;
	}

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		int N = Integer.parseInt(br.readLine());
		int[] step = new int[N];
		int[] parent = new int[N];
		ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<Integer>());
		}

		// 최상위는 1이지만 작은 수가 조상이라는 법은 없음
		// 같은 수준에서 비교하려면 현재 선택된 정점이 어디에 있는지 알아야함
		// 정점별로 탐색을 돌리면 시간초과가 뜨기 때문에 미리 저장해둘 필요가 있음
		// 정점을 잇는 간선정보가 차례대로 주어진단 보장이 없기 때문에 전부 받아
		// 탐색을 통해 수준을 확인해야함
		// 부모-자식 관계는 1에서 먼저 연결되는 순서이므로 일단 양방향으로 노드 구현
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map.get(a).add(b);
			map.get(b).add(a);
		}

		// 해당 노드의 단계, 부모-자식 관계 설정
		boolean[] visited = new boolean[N];
		Queue<Node> que = new LinkedList<Node>();
		step[0] = 1;
		que.add(new Node(0, step[0]));
		
		while (!que.isEmpty()) {
			Node now = que.poll();
			if (visited[now.t])
				continue;
			visited[now.t] = true;
			for (int m : map.get(now.t)) {
				Node next = new Node(m, now.c + 1);
				// 이미 관계가 설정된 경우 패스
				if (step[next.t] > 0 && step[next.t] <= next.c)
					continue;
				step[next.t] = next.c;
				parent[next.t] = now.t;
				que.add(next);
			}
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			// 가장 아래 단계부터 윗단계로 탐색
			int maxStep = step[a] > step[b] ? step[a] : step[b];
			int apar = a;
			int bpar = b;
			for (int j = maxStep; j >= 0; j--) {
				if (apar == bpar) {
					break;
				}
				if (j <= step[a]) {
					apar = parent[apar];
				}
				if (j <= step[b]) {
					bpar = parent[bpar];
				}
			}
			bw.write((apar + 1) + "\n");
		}
		bw.close();

	}

}

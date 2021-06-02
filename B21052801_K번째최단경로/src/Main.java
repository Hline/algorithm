import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 도시수
		int M = Integer.parseInt(st.nextToken());	// 단방향 도로수
		int K = Integer.parseInt(st.nextToken());	// K번째 최단거리
		ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>();
		PriorityQueue<Integer>[] costList = new PriorityQueue[N];	//도시별 최단거리 보관
		for (int n = 0; n < N; n++) {
			map.add(new ArrayList<>());
			// K번째 값까지만 저장, K번째 값만 사용하므로 역순정렬
			costList[n] = new PriorityQueue<>(Collections.reverseOrder());
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			map.get(a).add(new Node(b, c));
		}
		Queue<Node> que = new LinkedList<Node>();
		// 시작점 비용: i번 도시에서 i번 도시로 가는 최단경로는 0
		costList[0].add(0);
		// 1번 도시는 시작도시
		que.add(new Node(0, 0));

		while (!que.isEmpty()) {
			Node now = que.poll();

			for (int i = 0; i < map.get(now.t).size(); i++) {
				Node next = map.get(now.t).get(i);
				int cost = now.c + next.c;
				// K번째 최단거리를 구할 때까지만 탐색
				if (costList[next.t].size() >= K) {
					if (cost >= costList[next.t].peek())
						continue;
					// 에외케이스: 여태까지 구한 K번째 최단거리보다 작을 경우 
					//			가장 큰값을 빼고 추가
					else {
						costList[next.t].poll();
					}
				}
				costList[next.t].add(cost);
				que.add(new Node(next.t, cost));
			}
		}
		int de = 1;	// 디버깅용 중단점 코드
		for (int i = 0; i < N; i++) {
			if (costList[i].size() == 0 || costList[i].size() < K) {
				bw.write(-1 + "\n");
				continue;
			}
			bw.write(costList[i].poll() + "\n");
		}
		bw.close();
	}

}

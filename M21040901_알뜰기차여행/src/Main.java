import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int e;
	int d;

	public Node(int end, int dist) {
		// TODO Auto-generated constructor stub
		e = end;
		d = dist;
	}

	@Override
	public int compareTo(Node r) {
		// TODO Auto-generated method stub
		if (d < r.d)
			return -1;
		if (d > r.d)
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
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[] dist = new int[n];
		boolean[] visited = new boolean[n];
		ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < n; i++) {
			map.add(new ArrayList<Node>());
			dist[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			map.get(from).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		dist[0] = 0;
		pq.add(new Node(0, dist[0]));

		while (!pq.isEmpty()) {
			Node from = pq.poll();
			if (visited[from.e])
				continue;
			for (int i = 0; i < map.get(from.e).size(); i++) {
				Node to = map.get(from.e).get(i);
				int d = from.d + to.d;
				if (d >= dist[to.e])
					continue;
				dist[to.e] = d;
				pq.add(new Node(to.e, d));
				visited[from.e] = true;
			}
		}
		int fin = dist[n - 1];
		if (fin == Integer.MAX_VALUE)
			bw.write("impossible");
		else
			bw.write(dist[n - 1] + "");
		bw.close();
	}

}

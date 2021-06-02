
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int e;
	int t;

	public Node(int end, int time) {
		e = end;
		t = time;
	}

	@Override
	public int compareTo(Node r) {
		if (t < r.t)
			return -1;
		if (t > r.t)
			return 1;

		return 0;
	}

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int n;
	static int m;
	static ArrayList<ArrayList<Node>> map;
	static ArrayList<ArrayList<Node>> reMap;
	static int[] aDist;
	static int[] zDist;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("신규 도로\\1.in"));
		int t = Integer.parseInt(br.readLine());
		for (int tt = 0; tt < t; tt++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new ArrayList<ArrayList<Node>>();
			reMap = new ArrayList<ArrayList<Node>>();

			for (int i = 0; i < n; i++) {
				map.add(new ArrayList<Node>());
				reMap.add(new ArrayList<Node>());
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int time = Integer.parseInt(st.nextToken());
				map.get(start).add(new Node(end, time));
				map.get(end).add(new Node(start, time));
			}

//앞에서 모두
			aDist = Search(0, 0);
			int limit = aDist[n - 1] - 1;
//끝에서 모두
			zDist = Search(n - 1, 0);
			zDist[0] = zDist[n - 1] = Integer.MAX_VALUE;

			Arrays.sort(zDist);

			int maxCnt = 0;
			for (int i = 1; i < n - 1; i++) {
				if (aDist[i] == Integer.MAX_VALUE)
					continue;
				if (aDist[i] >= limit)
					continue;
				int tar = limit - aDist[i]-1;
				int l = 0;
				int r = zDist.length - 1-2;
				int founded = -1;
				while (l <= r) {
					int m = (l + r) / 2;
					if (tar >= zDist[m]) {
						founded = m;
						l = m + 1;
					}
					else {
						r = m - 1;
					}
				}
				maxCnt+=founded+1;
			}

			bw.write("#" + (tt + 1) + " " + maxCnt + "\n");
		}
		bw.close();

	}

	private static int[] Search(int first, int fTime) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean[] visited = new boolean[n];
		int[] dist = new int[n];
		for (int i = 0; i < n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[first] = fTime;
		pq.add(new Node(first, fTime));

		while (!pq.isEmpty()) {
			Node start = pq.poll();
			if (visited[start.e])
				continue;

			visited[start.e] = true;
			for (int i = 0; i < map.get(start.e).size(); i++) {
				Node next = map.get(start.e).get(i);
				if (dist[next.e] <= dist[start.e] + next.t)
					continue;
				dist[next.e] = dist[start.e] + next.t;
				pq.add(next);
			}
		}

		return dist;
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("¿¬¼âÆøÅº\\3.in"));
		int N = Integer.parseInt(br.readLine());
		Point[] map = new Point[N * N];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int idx = Integer.parseInt(st.nextToken()) - 1;
				map[idx] = new Point(i, j);
				pq.add(idx);
			}
		}

		int cnt = 0;
		while (!pq.isEmpty()) {
			int bombNum = pq.poll();
			Point now = map[bombNum];
			if (now == null)
				continue;
			cnt = bombNum + 1;

			for (int i = 0; i < map.length; i++) {
				if (map[i] == null)
					continue;
				if (map[i].x == now.x && map[i].y >= now.y - 1 && map[i].y <= now.y + 1)
					map[i] = null;
				else if (map[i].y == now.y && map[i].x >= now.x - 1 && map[i].x <= now.x + 1)
					map[i] = null;
			}
			boolean debug = true;
		}
		bw.write(cnt + "ÃÊ");

		bw.close();
	}

}

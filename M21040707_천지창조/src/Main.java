import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char[][] map = new char[8][9];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("천지창조\\1.in"));
		for (int i = 0; i < map.length; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}

		ArrayList<Pos> godAl = FindBFS(new Pos(0, 8));
		ArrayList<Pos> humAl = FindBFS(new Pos(7, 0));

		int minDist = Integer.MAX_VALUE;
		for (Pos s : humAl) {
			int dist = 0;
			int[] dr = { 1, 0, -1, 0, 0 };
			int[] dc = { 0, 0, 0, 1, -1 };
			Queue<Pos> que = new LinkedList<Pos>();
			int[][] chk = new int[map.length][map[0].length];
			que.add(s);
			chk[s.x][s.y] = dist;
			while (!que.isEmpty()) {
				Pos p = que.poll();
				dist = chk[p.x][p.y];
				for (int i = 0; i < dr.length; i++) {
					int nextx = p.x + dr[i];
					int nexty = p.y + dc[i];
					if (nextx >= map.length || nextx < 0 || nexty >= map[0].length || nexty < 0)
						continue;
					if (chk[nextx][nexty] != 0)
						continue;

					boolean endYN = false;
					for (Pos g : godAl) {
						if (g.x == p.x && g.y == p.y) {
							endYN = true;
							que.clear();
							break;
						}
					}
					if (endYN)
						break;
					que.add(new Pos(nextx, nexty));
					chk[nextx][nexty] = dist + 1;
				}
			}
			minDist = Math.min(minDist, dist - 1);
		}
		bw.write(minDist + "");
		bw.close();
	}

	public static ArrayList<Pos> FindBFS(Pos s) {
		ArrayList<Pos> al = new ArrayList<Pos>();
		int[] dr = { 1, 0, -1, 0, 0 };
		int[] dc = { 0, 0, 0, 1, -1 };
		Queue<Pos> que = new LinkedList<Pos>();
		boolean[][] visit = new boolean[map.length][map[0].length];
		que.add(s);
		visit[s.x][s.y] = true;
		while (!que.isEmpty()) {
			Pos p = que.poll();
			for (int i = 0; i < dr.length; i++) {
				int nextx = p.x + dr[i];
				int nexty = p.y + dc[i];
				if (nextx >= map.length || nextx < 0 || nexty >= map[0].length || nexty < 0)
					continue;
				if (map[nextx][nexty] == '_') {
					al.add(p);
					continue;
				}
				if (visit[nextx][nexty])
					continue;
				que.add(new Pos(nextx, nexty));
				visit[nextx][nexty] = true;
			}
		}
		return al;
	}

}

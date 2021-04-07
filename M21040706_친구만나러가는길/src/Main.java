import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		br = new BufferedReader(new FileReader("친구 만나러 가는길\\1.in"));
		int[][] map = { { 1, 1, 1, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 1, 1, 0 } };
		int[][] chk = new int[map.length][map[0].length];
		st = new StringTokenizer(br.readLine());
		Pos cheeze = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		Pos fri = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Queue<Pos> que = new LinkedList<Pos>();
		que.add(new Pos(0, 0));
		chk[0][0] = 0;

		boolean eatYN = false;
		int last = 0;
		int[] dr = { -1, 1, 0, 0, 0 };
		int[] dc = { 0, 0, 0, 1, -1 };

		while (!que.isEmpty()) {
			Pos p = que.poll();
			last = chk[p.x][p.y];
			if (p.x == cheeze.x && p.y == cheeze.y && !eatYN) {
				que.clear();
				que.add(cheeze);
				eatYN = true;
				chk = new int[map.length][map[0].length];
				chk[p.x][p.y] = last;
				continue;
			}
			if ((p.x == fri.x && p.y == fri.y) && eatYN) {
				break;
			}
			for (int i = 0; i < dr.length; i++) {
				int newx = p.x + dr[i];
				int newy = p.y + dc[i];
				if (newx >= map.length || newx < 0 || newy >= map[0].length || newy < 0)
					continue;
				if (map[newx][newy] == 0)
					continue;
				if (chk[newx][newy] != 0)
					continue;
				que.add(new Pos(newx, newy));
				chk[newx][newy] = last + 1;
			}
		}

		bw.write(last + "");
		bw.close();

	}

}

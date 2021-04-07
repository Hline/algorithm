import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception, Exception {
//		br = new BufferedReader(new FileReader("¾È³ª¿Í ¿¤»ç M\\1.in"));
		int N = Integer.parseInt(br.readLine());
		String[] map = new String[N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}
		st = new StringTokenizer(br.readLine());
		Pos a = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Pos b = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		int[][] aVisit = new int[N][N];
		Queue<Pos> aQue = new LinkedList<Pos>();
		aQue.add(a);
		int last = 0;
		aVisit[a.x][a.y] = last;

		int[] dr = { 0, 0, 0, 1, -1 };
		int[] dc = { 1, 0, -1, 0, 0 };

		while (!aQue.isEmpty()) {
			Pos ap = aQue.poll();
			last = aVisit[ap.x][ap.y];
			if (ap.x == b.x && ap.y == b.y) {
				break;
			}
			for (int i = 0; i < dr.length; i++) {
				int aNewX = ap.x + dr[i];
				int aNewY = ap.y + dc[i];
				if (aNewX >= N || aNewX < 0 || aNewY >= N || aNewY < 0)
					continue;
				if (aVisit[aNewX][aNewY] != 0)
					continue;
				if (map[aNewX].charAt(aNewY) == '#')
					continue;
				aQue.add(new Pos(aNewX, aNewY));
				aVisit[aNewX][aNewY] = last + 1;
			}
		}

		int adj = last % 2;
		bw.write((last / 2 + adj) + "");
		bw.close();

	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pos implements Comparable<Pos> {
	int x;
	int y;
	int t;

	public Pos(int x, int y, int tired) {
		this.x = x;
		this.y = y;
		this.t = tired;
	}

	@Override
	public int compareTo(Pos r) {
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
	static int[][] map;
	static int[][] tired;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("������ �þ�Ӵ�\\2.in"));
		st = new StringTokenizer(br.readLine());
		int xStart = Integer.parseInt(st.nextToken());	// �ô��� ��ġ��ǥ
		int yStart = Integer.parseInt(st.nextToken());	// �ô��� ��ġ��ǥ

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());			// ������ ũ��
		map = new int[n][n];							// ������ ����
		tired = new int[n][n];							// �� ��ġ�� �Ƿε�
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				tired[i][j] = (map[i][j] == -1) ? map[i][j] : Integer.MAX_VALUE;
			}
		}

		int[] maxTired = search(xStart, yStart);

		bw.write(tired[maxTired[0]][maxTired[1]] + "");

		bw.close();
	}

	// ��� ��ġ�� �ִܰŸ��� �������� �� �Ƿε�
	// xStart, yStart: ������. �ô��� ��ġ��ǥ.
	static int[] search(int xStart, int yStart) {
		int[] dx = { 0, 0, 0, -1, 1 };	// �þ�Ӵϰ� �̵������� x����
		int[] dy = { -1, 1, 0, 0, 0 };	// �þ�Ӵϰ� �̵������� y����

		boolean[][] visited = new boolean[n][n];
		int maxTired = 0;	// ���� ū �Ƿε�
		int maxX = 0;		// ���� ū �Ƿε��� ��ġ
		int maxY = 0;

		// �ִܰŸ��� ã������ PriorrityQueue ���
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
		tired[xStart][yStart] = map[xStart][yStart];
		pq.add(new Pos(xStart, yStart, tired[xStart][yStart]));

		while (!pq.isEmpty()) {
			Pos from = pq.poll();
			if (visited[from.x][from.y])
				continue;
			visited[from.x][from.y] = true;

			for (int i = 0; i < dx.length; i++) {
				int xTo = from.x + dx[i];
				int yTo = from.y + dy[i];

				if (xTo < 0 || xTo >= n)
					continue;
				if (yTo < 0 || yTo >= n)
					continue;
				if (map[xTo][yTo] == -1)
					continue;

				// �̹� ���� �Ƿε��� ������� �̵��� �Ƿε� ��
				int tTo = from.t + map[xTo][yTo];
				if (tired[xTo][yTo] <= tTo)
					continue;

				tired[xTo][yTo] = tTo;
				pq.add(new Pos(xTo, yTo, tTo));
				// �þ�Ӵϰ� ���� ���� ���� ��ġ. ���� ���� ������ ��η� ����.
				if (maxTired <= tTo) {
					maxTired = tTo;
					maxX = xTo;
					maxY = yTo;
				}
			}
		}
		return new int[] { maxX, maxY };
	}

}

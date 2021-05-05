import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] parent;

	public static void main(String[] args) throws Exception {

		int n = Integer.parseInt(br.readLine());
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean stable = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1) {
					stable = union(i, j);
				}
				if (!stable)
					break;
			}
			if (!stable)
				break;
		}
		if (stable)
			bw.write("STABLE");
		else
			bw.write("WARNING");
		bw.close();
	}

	private static boolean union(int a, int b) {
		int aparent = find(a);
		int bparent = find(b);

		// A->B B->B X
		if (aparent == b && bparent == a)
			return false;
		// A->C B->D X
		if (aparent != a && aparent != b && bparent != b && bparent != a)
			return false;
		if (aparent == a && bparent == b)
			parent[b] = aparent;
		return true;
	}

	private static int find(int n) {
		if (parent[n] == n)
			return n;
		else
			return find(parent[n]);
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] parent = new int[26];

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < parent.length; i++)
			parent[i] = -1;

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = st.nextToken().charAt(0) - 'A';
			int b = st.nextToken().charAt(0) - 'A';
			Union(a, b);
		}

		boolean[] team = new boolean[parent.length];
		int cntNone = 0;
		for (int p : parent) {
			if (p == -1)
				cntNone++;
			else
				team[Find(p)] = true;
		}

		int cntTeam = 0;
		for (boolean t : team)
			if (t)
				cntTeam++;

		bw.write(cntTeam + "\n");
		bw.write(cntNone + "\n");
		bw.close();
	}

	public static void Union(int a, int b) {
		int aparent = Find(a);
		int bparent = Find(b);
		if (aparent == -1) {
			aparent = a;
			parent[a] = a;
		}
		if (bparent == -1) {
			bparent = b;
			parent[b] = b;
		}
		if (aparent != bparent)
			parent[aparent] = bparent;
	}

	public static int Find(int n) {
		if (parent[n] == n)
			return n;
		else if (parent[n] == -1)
			return parent[n];
		else
			return Find(parent[n]);
	}

}

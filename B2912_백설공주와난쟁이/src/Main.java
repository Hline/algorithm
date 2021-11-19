import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Dwarf {
	int color;
	int count;

	public Dwarf(int cl, int cn) {
		color = cl;
		count = cn;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int NN;
	static int[] input;
	static Dwarf[] tree;
	static int C;
	static int[][] color; // Arraylist�� �� ��� ������ ����������. ������ �� �ִٸ� �������� ������ ���� ����.
	static int[] count;
	static int M;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new int[N + 1];		// ������ ����
		count = new int[C + 1];		// ���� ����
		color = new int[C + 1][];	// ���� ������ ����
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
			count[input[n]]++;
		}

		for (int c = 1; c <= C; c++) {
			color[c] = new int[count[c]];
			count[c] = 0;
		}

		for (int n = 1; n <= N; n++) {
			int idx = count[input[n]];
			color[input[n]][idx] = n;
			count[input[n]]++;
		}

		NN = 1;
		while (N > NN) {
			NN *= 2;
		}
		tree = new Dwarf[NN * 2];
		Init(1, 1, N);

		M = Integer.parseInt(br.readLine());
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Dwarf qry = Query(1, 1, N, a, b);
			int k = b - a + 1;
			if (qry.count > k / 2) {
				bw.write("yes " + qry.color + "\n");
			} else {
				bw.write("no\n");
			}
		}
		bw.close();
	}

	private static Dwarf Init(int node, int l, int r) {
		if (l == r) {
			// �������� ������ ���� 1
			return tree[node] = new Dwarf(input[l], 1);
		}

		int mid = (l + r) / 2;
		Dwarf left = Init(node * 2, l, mid);
		Dwarf right = Init(node * 2 + 1, mid + 1, r);

		if (left.color == right.color) {
			// �������� l~r ������ ����
			return tree[node] = new Dwarf(left.color, Upper(left.color, r) - Lower(left.color, l));
		}
		// left.color �� Ž������(l~q) �� ����: ����Ž�� Upper (�ֿ���Ž��) - ����Ž�� Lower (������Ž��)
		int lCount = Upper(left.color, r) - Lower(left.color, l);
		// right.color �� Ž������(l~q) �� ����
		int rCount = Upper(right.color, r) - Lower(right.color, l);
		// �����̰� ���� �� ����
		if (lCount > rCount) {
			return tree[node] = new Dwarf(left.color, lCount);
		} else if (lCount < rCount) {
			return tree[node] = new Dwarf(right.color, rCount);
		} else {
			// ���� ���� ��� ���ڻ� 0
			return tree[node] = new Dwarf(0, 0);
		}

	}

	// ����Ž�� Lower Bound
	private static int Lower(int c, int target) {
		if (c == 0)
			return 0;
		int l = 0;
		int r = color[c].length;
		int mid = 0;
		while (l < r) {
			mid = (l + r) / 2;
			// l mid target r
			if (color[c][mid] < target) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return r;
	}

	// ����Ž�� Upper Bound
	private static int Upper(int c, int target) {
		if (c == 0)
			return 0;
		int l = 0;
		int r = color[c].length;
		int mid = 0;
		while (l < r) {
			mid = (l + r) / 2;
			// l mid target r
			if (color[c][mid] <= target) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return r;
	}

	private static Dwarf Query(int node, int l, int r, int ql, int qr) {
		if (qr < l || r < ql) {
			return new Dwarf(0, 0);
		}
		if (ql <= l && r <= qr) {
			return tree[node];
		}

		int mid = (l + r) / 2;
		Dwarf left = Query(node * 2, l, mid, ql, qr);
		Dwarf right = Query(node * 2 + 1, mid + 1, r, ql, qr);
		if (left.color == right.color) {
			// �������� l~r ������ ����
			return new Dwarf(left.color, Upper(left.color, qr) - Lower(left.color, ql));
		}
		// left.color �� Ž������(ql~qr) �� ����: ����Ž�� Upper (�ֿ���Ž��) - ����Ž�� Lower (������Ž��)
		int lCount = Upper(left.color, qr) - Lower(left.color, ql);
		// right.color �� Ž������(ql~qr) �� ����
		int rCount = Upper(right.color, qr) - Lower(right.color, ql);
		if (lCount > rCount) {
			return new Dwarf(left.color, lCount);
		} else if (lCount < rCount) {
			return new Dwarf(right.color, rCount);
		} else {
			return new Dwarf(0, 0);
		}

	}

}

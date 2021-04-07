import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int a;
	char b;

	public Node(int a, String b) {
		this.a = a;
		this.b = b.charAt(0);
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		// 둘다 짝수일 경우
		if (a % 2 == 0 && o.a % 2 == 0) {
			if (a > o.a)
				return 3;
			if (a < o.a)
				return -3;
			if (b > o.b)
				return 3;
			if (b < o.b)
				return -3;
			// 한쪽만 짝수일 경우
		} else if (a % 2 == 0)
			return -3;
		else if (o.a % 2 == 0)
			return 3;
		// 둘다 아닐경우
		else {
			if (a > o.a)
				return 3;
			if (a < o.a)
				return -3;
			if (b > o.b)
				return 3;
			if (b < o.b)
				return -3;
		}

		return 0;
	}

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception, Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		Integer[] aType = new Integer[N];
		Node[] cType = new Node[N];
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < N; j++) {
			aType[j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < N; j++) {
			cType[j] = new Node(aType[j], st.nextToken());
		}

		Arrays.sort(aType);
		for (int a : aType)
			bw.write(a + " ");
		bw.write("\n");

		Arrays.sort(aType, Comparator.reverseOrder());
		for (int a : aType)
			bw.write(a + " ");
		bw.write("\n");

		Arrays.sort(cType);
		for (Node n : cType)
			bw.write(n.a + " ");
		bw.write("\n");

		for (Node n : cType)
			bw.write(n.b + " ");
		bw.write("\n");

		bw.close();
	}

}

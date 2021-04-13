import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Printer implements Comparable<Printer> {
	int id;
	long start;
	long end;

	public Printer(int i) {
		// TODO Auto-generated constructor stub
		id = i;
		start = 0;
		end = 0;
	}

	@Override
	public int compareTo(Printer r) {
//endtime ���� ��, id  ���� ��
		if (end < r.end)
			return -1;
		if (end > r.end)
			return 1;
//		if (end == r.end)
		if (id < r.id)
			return -1;
		if (id > r.id)
			return 1;
		return 0;
	}
}

class Document implements Comparable<Document> {
	int id;
	long req;
	long tat;

	public Document(int i, long r, long t) {
		// TODO Auto-generated constructor stub
		id = i;
		req = r;
		tat = t;
	}

	@Override
	public int compareTo(Document r) {
		// req ������, �ҿ�ð� ���
		if (req < r.req)
			return -1;
		if (req > r.req)
			return 1;
		if (tat > r.tat)
			return 1;
		if (tat < r.tat)
			return -1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		br = new BufferedReader(new FileReader("������ �����층 �ý���\\5.in"));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Printer> priPq = new PriorityQueue<Printer>();
		for (int m = 0; m < M; m++)
			priPq.add(new Printer(m));

		int[] matched = new int[N]; // ������ �Ҵ�� ������id
		Document[] doc = new Document[N];
		PriorityQueue<Document> docPq = new PriorityQueue<Document>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			doc[n] = new Document(n, Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		}

		Arrays.sort(doc);
		for (int i = 0; i < N; i++) {
			Document d = doc[i];
			Printer p = priPq.poll();

			// ���õ� ����, ������ ����
			matched[d.id] = p.id + 1;

			// ������ ���� ����
			// �μⰡ �̹� ���� ���
			if (p.end < d.req) {
				p.start = d.req;
				p.end = d.req + d.tat;
			}
			// �μⰡ ���� ���
			else {
				p.start = p.end;
				p.end = p.start + d.tat;
			}
			priPq.add(p);
		}

		for (int m : matched)
			bw.write(m + "\n");
		bw.close();

	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class TimeTable implements Comparable<TimeTable> {
	int s;
	int e;

	public TimeTable(int start, int end) {
		// TODO Auto-generated constructor stub
		s = start;
		e = end;
	}

	@Override
	public int compareTo(TimeTable r) {
		// TODO Auto-generated method stub
		// 빨리 끝나는 거 먼저
		// 끝나는 시간이 같은 경우 이른거 먼저
		if (e < r.e)
			return -1;
		if (e > r.e)
			return 1;

		if (s < r.s)
			return -1;
		if (s > r.s)
			return 1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static StringTokenizer st;

	public static void main(String[] args) throws Exception, Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("회의실 배정\\3.in"));
		int N = Integer.parseInt(br.readLine());
		TimeTable[] schedule = new TimeTable[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			schedule[i] = new TimeTable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(schedule);
		int cnt = 0;
		int endTime = 0;
		for (TimeTable s : schedule)
			if (s.s >= endTime) {
				cnt++;
				endTime = s.e;
			}

		bw.write(cnt + "");

		bw.close();
	}

}

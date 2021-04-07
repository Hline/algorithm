import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		br = new BufferedReader(new FileReader("∆¯≈∫ ≈ı«œ\\1.in"));
		char[][] map = new char[4][5];
		for (char[] m : map) {
			Arrays.fill(m, '_');
		}
		int[] xdir = { -1, -1, -1, 0, 0, +1, +1, +1 };
		int[] ydir = { +1, 0, -1, +1, -1, +1, 0, -1 };

		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int j = 0; j < xdir.length; j++) {
				int xnew = x + xdir[j];
				int ynew = y + ydir[j];
				if (xnew >= map.length || ynew >= map[0].length)
					continue;
				if (xnew < 0 || ynew < 0)
					continue;
				map[xnew][ynew] = '#';
			}
		}

		for (char[] m : map) {
			for (char mm : m)
				bw.write(mm + " ");
			bw.write("\n");
		}

		bw.close();
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int[][] apart = new int[row][col];
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				apart[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int bRow = Integer.parseInt(st.nextToken());
		int bCol = Integer.parseInt(st.nextToken());
		boolean[] blacks = new boolean[100000];
		for (int i = 0; i < bRow; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < bCol; j++) {
				blacks[Integer.parseInt(st.nextToken())] = true;
			}
		}

		int bCnt = 0;
		for (int[] a : apart)
			for (int aa : a)
				if (blacks[aa])
					bCnt++;

		bw.write(bCnt + "\n");
		bw.write(col * row - bCnt + "");
		bw.close();
	}

}

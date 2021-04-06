import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
		br = new BufferedReader(new FileReader("글귀찾기\\4.in"));
		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		char[][] note = new char[row][col];
		for (int i = 0; i < row; i++) {
			String line = br.readLine();
			for (int j = 0; j < col; j++) {
				note[i][j] = line.charAt(j);
			}
		}
		int n = Integer.parseInt(br.readLine());
		String nTxt = br.readLine();

		int[] bookmark = new int[2];
		int x = 0;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 한차례 검증한 경우 그 다음 글자부터 다시 확인
				if (x == nTxt.length()) {
					i = bookmark[0];
					j = bookmark[1];
					x = 0;
					bookmark = new int[2];
					continue;
				}
				if (note[i][j] == nTxt.charAt(x)) {
					if (x == 0) {
						bookmark[0] = i;
						bookmark[1] = j;
					}
					if (x == nTxt.length() - 1) {
						bw.write("(" + bookmark[0] + "," + bookmark[1] + ")\n");
					}
					x++;
				} else {
					x = 0;
					bookmark = new int[2];

				}
			}
		}
		bw.close();

	}

}

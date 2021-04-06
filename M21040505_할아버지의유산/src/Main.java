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
	static int[][] map = new int[4][8];

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("할아버지의 유산\\3.in"));
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int max = 0;
		// 선택한 땅의 크기
		for (int xSel = 1; xSel <= map.length; xSel++) {
			for (int ySel = 1; ySel <= map[0].length; ySel++) {
				// 선택한 땅의 시작점
				for (int iStart = 0; iStart < map.length; iStart++) {
					for (int jStart = 0; jStart < map[0].length; jStart++) {
						int sum = 0;
						// 녹지(0) 검증
						for (int iChk = iStart; iChk < iStart + xSel; iChk++) {
							// 선택한 땅의 크기가 땅보다 클 경우
							if (iStart + xSel > map.length)
								break;
							if (jStart + ySel > map[0].length)
								break;
							for (int jChk = jStart; jChk < jStart + ySel; jChk++) {
								if (map[iChk][jChk] == 0) {
									sum = 0;
									break;
								}
								sum += map[iChk][jChk];
							}
						}

						if (max < sum)
							max = sum;
					}
				}
			}
		}
		bw.write(max + "");
		bw.close();
	}

}

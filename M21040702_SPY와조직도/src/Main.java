import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int[] nArr = { 0, 1004, 1680, 9941, 3367, 3261, 2976, 4889, 1234, 6461, 7329, 5518 };
		ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < nArr.length; i++) {
			al.add(new ArrayList<>());
		}
		// 2 - 45
		// 3-67
		// 5-89
		// 6-10 11
		al.get(0).add(1);
		al.get(1).add(2);
		al.get(1).add(3);
		al.get(2).add(4);
		al.get(2).add(5);
		al.get(3).add(6);
		al.get(3).add(7);
		al.get(5).add(8);
		al.get(5).add(9);
		al.get(6).add(10);
		al.get(6).add(11);

		int boss = -1;
		int userId = Integer.parseInt(br.readLine());
		int userIdx = -1;
		for (int i = 0; i < nArr.length; i++) {
			for (int a : al.get(i))
				if (nArr[a] == userId) {
					boss = i;
					userIdx = a;
				}
		}

		if (userIdx == -1)
			bw.write("no person");
		else {
			if (nArr[boss] == 0)
				bw.write("no boss");
			else
				bw.write(nArr[boss] + "");
			bw.write("\n");

			if (al.get(boss).size() == 1)
				bw.write("no company");
			else
				for (int a : al.get(boss)) {
					if (nArr[a] != userId)
						bw.write(nArr[a] + " ");
				}
			bw.write("\n");
			if (al.get(userIdx).size() == 0)
				bw.write("no junior");
			else
				for (int a : al.get(userIdx)) {
					bw.write(nArr[a] + " ");
				}
		}
		bw.write("\n");

		bw.close();
	}

}

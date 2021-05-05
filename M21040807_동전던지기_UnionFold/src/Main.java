import java.io.*;
import java.util.StringTokenizer;

class Node {
    int row, col;
    Node(int a, int b) {
        row = a;
        col = b;
    }
}

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int w;
    static int h;
    static char[][] map;

    static int[][] cmds;
    static int[] invalidCmds; //�߸��� ��ɾ��϶� (���� �ִµ� ���� �� ������) 1 ����
    static int cmdCnt;

    static int[][] coin_BossToCnt; //�׷��� ������ �ִ� ������ ����
    static int[] coin_SizeToCnt; //Ư�� ������ � �ִ��� ����
    static int coin_Result; //��� ��� ����

    static int[][] bin_BossToCnt;
    static int[] bin_SizeToCnt;
    static int bin_Result;

    static Node[][] uArr; //���Ͽ����ε� �迭

    static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new char[h][w];

        for (int row= 0; row<h; row++) {
            String line = br.readLine();
            for (int col = 0; col<w; col++) {
                map[row][col] = line.charAt(col); 
            }
        }

        cmdCnt = Integer.parseInt(br.readLine());        
        cmds = new int[cmdCnt][3];
        invalidCmds = new int[cmdCnt];

        for (int i = 0; i<cmdCnt; i++) {
            st = new StringTokenizer(br.readLine());
            cmds[i][0] = Integer.parseInt(st.nextToken());
            if (cmds[i][0] == 1) {
                cmds[i][1] = Integer.parseInt(st.nextToken());
                cmds[i][2] = Integer.parseInt(st.nextToken());
            }
            else {
                cmds[i][1] = Integer.parseInt(st.nextToken());
            }
        }

        coin_BossToCnt = new int[h][w];
        coin_SizeToCnt = new int[h * w * 2 + 2];
        bin_BossToCnt = new int[h][w];
        bin_SizeToCnt = new int[h * w + 1];
    }

    static Node getFind(int trow, int tcol) {
        Node par = uArr[trow][tcol];
        if (par.row == trow && par.col == tcol) return par; // ���� �����̸� ���� ��ȯ
        Node ret = getFind(par.row, par.col);//����ã��

        uArr[trow][tcol].row = ret.row;
        uArr[trow][tcol].col = ret.col;
        // ���󿡰� ���̷�Ʈ�� ����

        return ret;
    }

    static void setUnion(int drow, int dcol, int nrow, int ncol, int tar) {
        Node ret;
        ret = getFind(drow, dcol);
        Node a = new Node(ret.row, ret.col);

        ret = getFind(nrow, ncol);
        Node b = new Node(ret.row, ret.col);

        if (a.row == b.row && a.col == b.col) return;
        //���� �׷��̸� �� �̻� ������ �ʿ���� ��

        uArr[b.row][b.col].row = a.row; 
        uArr[b.row][b.col].col = a.col;
        //b�� a�� �ڽ����� ����
        //����� �۾�
        if (tar == '0') {
            int aCnt = coin_BossToCnt[a.row][a.col]; // a�� ����ũ��
            int bCnt = coin_BossToCnt[b.row][b.col]; // b�� ����ũ��

            coin_SizeToCnt[aCnt * 2]--; // a���� ������ �ش��ϴ� ������ 1�� �پ��
            coin_SizeToCnt[bCnt * 2]--; // b���� ������ �ش��ϴ� ������ 1�� �پ��

            coin_BossToCnt[a.row][a.col] += coin_BossToCnt[b.row][b.col]; 
            // a�� b������ ���� �ش�.

            coin_BossToCnt[b.row][b.col] = 0;
            //b�� ���� ���� ��� 0���� �ʱ�ȭ

            int newCnt = coin_BossToCnt[a.row][a.col];
            coin_SizeToCnt[newCnt * 2]++; // ������ ���� ������ �ش��ϴ� ������ ���� 1�� �߰�
        }
        else {
            int aCnt = bin_BossToCnt[a.row][a.col];
            int bCnt = bin_BossToCnt[b.row][b.col];
            bin_SizeToCnt[aCnt]--; // a���� ������ �ش��ϴ� ������ 1�� �پ��(��, �������� 2�迴���� ���⼱ 1��)
            bin_SizeToCnt[bCnt]--;

            bin_BossToCnt[a.row][a.col] += bin_BossToCnt[b.row][b.col];
            bin_BossToCnt[b.row][b.col] = 0;

            int newCnt = bin_BossToCnt[a.row][a.col];
            bin_SizeToCnt[newCnt]++; // ������ 2������ ���⼱ 1��
        }
    }

    static void makeUnion(int drow, int dcol) {
        int[][] direct = {{-1, 0},{1, 0},{0, 1}, {0, -1}};
        char tar = map[drow][dcol]; 

        for (int t = 0; t<4; t++) {
            int nrow = drow + direct[t][0];
            int ncol = dcol + direct[t][1];
            if (nrow < 0 || ncol < 0 || nrow >= h || ncol >= w) continue;
            if (map[nrow][ncol] != tar) continue; // ������ �κ��� ���� ���� or ��ĭ

            setUnion(drow, dcol, nrow, ncol, tar);
        }
    }

    static void insertThing(int row, int col, char tar) {
        map[row][col] = tar;

        if (tar == '0') {
            coin_SizeToCnt[2]++; // 2���� �ش��ϴ� ���� 1�� �߰�
            coin_BossToCnt[row][col] = 1; // �ش� ������ ���� ����
        }
        else {
            bin_SizeToCnt[1]++; // 1���� �ش��ϴ� ���� 1�� �߰�
            bin_BossToCnt[row][col] = 1; // �ش� ������ ��ĭ ����
        }

    }

    static void initSetting(char tar)
    {   
        uArr = new Node[h][w];
        for (int row = 0; row<h; row++) {
            for (int col =0 ; col<w; col++) {
                uArr[row][col] = new Node(row, col); 
            }
        }
        //�ڱ� �ڽ��� �����̶�� �ʱ�ȭ

        int[][] list = new int[w * h][2]; // union�� ������ ��ǥ
        int t = 0;

        //������ ����Ʈ���ٰ� ���ȭ
        //���� �ƿ� ��� ����
        for (int row =0; row<h; row++) {
            for (int col = 0; col<w; col++) {
                if (map[row][col] == tar) {
                    list[t][0] = row;
                    list[t][1] = col;
                    t++;

                    if (tar == '0') map[row][col] = '_';
                    else map[row][col] = '0';
                }
            }
        }

        //������ �ϳ��� �ø���.
        for (int i = 0; i<t; i++) {
            int row = list[i][0];
            int col = list[i][1];
            map[row][col] = tar;
            insertThing(row, col, tar);//�� ������ ������ ����
            makeUnion(row, col);
        }
    } // ������ �ʱ�ȭ 

    public static void main(String[] args) throws IOException {        

        input();

        //���� ����
        initSetting('0');
        for (int i = 0; i < cmdCnt; i++) {            
            if (cmds[i][0] == 2) {
                coin_Result += coin_SizeToCnt[cmds[i][1]];
            }
            if (cmds[i][0] == 1) {
                //�̹� ������ �ִ°��� �� �ø��ٸ�
                if (map[cmds[i][1]][cmds[i][2]] == '0') {
                    invalidCmds[i] = 1;
                    continue;
                }

                //������ �ϳ��� �ø���.
                insertThing(cmds[i][1], cmds[i][2], '0');
                makeUnion(cmds[i][1], cmds[i][2]);
            }
        }

        //��ĭ ���� (���÷���)
        initSetting('_');
        for (int i = cmdCnt - 1; i >= 0; i--) {   
            if (cmds[i][0] == 2) {
                bin_Result += bin_SizeToCnt[cmds[i][1]];
            }
            if (cmds[i][0] == 1) {
                if (invalidCmds[i] == 1) continue;
                insertThing(cmds[i][1], cmds[i][2], '_');
                makeUnion(cmds[i][1], cmds[i][2]);
            }
        }

        bw.write((coin_Result + bin_Result) + "\n");

        br.close();
        bw.close();
    }
}
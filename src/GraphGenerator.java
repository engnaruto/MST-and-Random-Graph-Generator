import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class GraphGenerator {
	// public UnionFind u;
	public ArrayList<String> arr;
	public Random r;
	public PrintWriter out;

	public GraphGenerator() throws FileNotFoundException {
		r = new Random();
		// out = new PrintWriter("output.txt");
	}

	// true == dense,false = sparse
	public void generateGraph(int numofNodes, boolean graphType, int maxWeight)
			throws FileNotFoundException {
		if (numofNodes < 2) {
			System.out.println("-_-");
			return;
		}
		out = new PrintWriter("output.txt");
		// u = new UnionFind(numofNodes);
		arr = new ArrayList<>();
		int[][] map = new int[numofNodes][numofNodes];
		int maxNumofEdges = (numofNodes * (numofNodes - 1)) / 2;
		int halfMaxNumofEdges = maxNumofEdges / 2;
		int numofEdges = numofNodes - 1;
		if (graphType) {
			if (halfMaxNumofEdges > 0) {
				numofEdges = halfMaxNumofEdges + r.nextInt(halfMaxNumofEdges)
						+ 1;
			}
			if (numofEdges > maxNumofEdges) {
				numofEdges = maxNumofEdges;
			}
		} else {
			if (halfMaxNumofEdges > 0) {
				numofEdges = r.nextInt(halfMaxNumofEdges) + 1;
			}
			if (numofEdges < numofNodes - 1) {
				numofEdges = numofNodes - 1;
			}
		}
//		System.out.println("NODES = " + numofNodes);
//		System.out.println("MAX = " + maxNumofEdges);
//		System.out.println("HALF = " + halfMaxNumofEdges);
//		System.out.println("EDGES = " + numofEdges);

		// for (int i = 0; i < numofEdges; i++) {
		// while (true) {
		// int v1 = r.nextInt(numofNodes) + 1;
		// int v2 = r.nextInt(numofNodes) + 1;
		// int w = r.nextInt(maxWeight) + 1;
		// if (!u.isUnion((v1 - 1), (v2 - 1))) {
		// System.out.println(u.forests+"  "+i+"   "+numofEdges);
		// u.union((v1 - 1), (v2 - 1));
		// String s = v1 + "," + v2 + "," + w;
		// arr.add(s);
		// break;
		// }
		// }
		// }
		for (int i = 0; i < numofNodes - 1; i++) {
			int w = r.nextInt(maxWeight) + 1;
			map[i][i + 1] = w;
			String s = (i + 1) + "," + (i + 2) + "," + w;
			arr.add(s);
		}

		int num = numofNodes - 1;
		while (num != numofEdges && num < numofEdges) {
			int v1 = r.nextInt(numofNodes) + 1;
			int v2 = r.nextInt(numofNodes) + 1;
			int w = r.nextInt(maxWeight) + 1;
			if (map[v1 - 1][v2 - 1] == 0) {
				// System.out.println(u.forests + "  " + i + "   " +
				// numofEdges);
				// u.union((v1 - 1), (v2 - 1));
				// System.out.println("~~~~~~~~~~  "+num);
				map[v1 - 1][v2 - 1] = w;
				map[v2 - 1][v1 - 1] = w;
				String s = v1 + "," + v2 + "," + w;
				arr.add(s);
				num++;
				// break;
			}
		}
		out.println(numofNodes);
		out.println(numofEdges);
		// System.out.println(numofNodes);
		// System.out.println(numofEdges);
//		System.out.println(arr.size());
		for (int i = 0; i < arr.size(); i++) {
			out.println(arr.get(i));
		}
		out.close();
	}
}

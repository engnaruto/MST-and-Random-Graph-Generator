import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Prim {
	public static double[][] map;
	public static double[] dis;
	public static boolean[] visited;
	public static double mstCost;
	public static ArrayList<Nodes>[] map2;
	public static PriorityQueue<Edge> pq;
	public static Runtime runtime;

	public static class Nodes {
		int to;
		double w;

		public Nodes(int to, double w) {
			this.to = to;
			this.w = w;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		double w;

		public Edge(int from, int to, double w) {
			this.from = from;
			this.to = to;
			this.w = w;

		}

		@Override
		public int compareTo(Edge o) {
			if (w - o.w > Math.pow(10, -5)) {
				return 1;
			} else if (w - o.w < Math.pow(10, -5)) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	public static double getMemoryUsed() {
		runtime.gc();
		double memory = runtime.totalMemory() - runtime.freeMemory();
		memory /= 1024;
		memory /= 1024;
		return memory;
	}

	public static void primList() {
		int currNode = 0;
		dis[currNode] = 0;

		for (int k = 0; k < map.length - 1; k++) {
			// System.out.println((currNode + 1) + "       "
			// + Arrays.toString(map[currNode]));
			for (int i = 0; i < map2[currNode].size(); i++) {
				if (!visited[map2[currNode].get(i).to]) {
					if (dis[map2[currNode].get(i).to] > map2[currNode].get(i).w) {
						// System.out.println("~~~~~" + (i + 1) + "   >>  "
						// + dis[i] + "      " + map[currNode][i]);
						dis[map2[currNode].get(i).to] = map2[currNode].get(i).w;
					}
				}
			}
			visited[currNode] = true;
			double min = Integer.MAX_VALUE;
			for (int i = 0; i < dis.length; i++) {
				if (i != currNode && !visited[i] && dis[i] < min) {
					min = dis[i];
					currNode = i;
				}
			}
			// System.out.println(currNode + 1 + "    " + min);

		}
		// System.out.println(Arrays.toString(visited));
		for (int i = 0; i < dis.length; i++) {
			mstCost += dis[i];
		}
	}

	public static void primMatrix() {
		int currNode = 0;
		dis[currNode] = 0;
		// visited[0] = true;
		for (int k = 0; k < map.length - 1; k++) {
			// System.out.println((currNode + 1) + "       "
			// + Arrays.toString(map[currNode]));
			for (int i = 0; i < dis.length; i++) {
				if (!visited[i]) {
					if (dis[i] > map[currNode][i]) {
						// System.out.println("~~~~~" + (i + 1) + "   >>  "
						// + dis[i] + "      " + map[currNode][i]);
						dis[i] = map[currNode][i];
					}
				}
			}
			visited[currNode] = true;
			double min = Integer.MAX_VALUE;
			for (int i = 0; i < dis.length; i++) {
				if (i != currNode && !visited[i] && dis[i] < min) {
					min = dis[i];
					currNode = i;
				}
			}
			// System.out.println(currNode + 1 + "    " + min);

		}
		// System.out.println(Arrays.toString(visited));
		for (int i = 0; i < dis.length; i++) {
			mstCost += dis[i];
		}
	}

	public static void PrimPriorityQueue() {

		Edge edge = pq.poll();

		visited[edge.from] = true;

		if (!visited[edge.to]) {
			mstCost += edge.w;
			visited[edge.to] = true;
		}

		while (!pq.isEmpty()) {
			edge = pq.poll();
			if (!visited[edge.to]) {
				mstCost += edge.w;
				visited[edge.to] = true;
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				new FileInputStream("in.txt")));
		// PrintWriter out = new PrintWriter(new OutputStreamWriter(
		// new FileOutputStream("out.txt")));
		String s = input.readLine();
		// int t = Integer.parseInt(s);
		// for (int u = 0; u < t; u++) {
		// s = input.readLine();
		int n = Integer.parseInt(s);
		s = input.readLine();
		int m = Integer.parseInt(s);

		map = new double[n][n];
		dis = new double[n];
		visited = new boolean[n];
		map2 = new ArrayList[n];
		pq = new PriorityQueue<>();
		Arrays.fill(dis, Integer.MAX_VALUE);
		Arrays.fill(visited, false);

		for (int i = 0; i < map.length; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
			map2[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			String[] a = input.readLine().split(",");
			int from = Integer.parseInt(a[0]);
			int to = Integer.parseInt(a[1]);
			double w = Double.parseDouble(a[2]);
			// if (u == 0) {
			from--;
			to--;
			// }
			map[from][to] = w;
			map[to][from] = w;
			map2[from].add(new Nodes(to, w));
			map2[to].add(new Nodes(from, w));
			Edge edge = new Edge(from, to, w);
			pq.add(edge);
		}
		// for (int i = 0; i < map.length; i++) {
		// System.out.println(Arrays.toString(map[i]));
		// }
		runtime = Runtime.getRuntime();
		runtime.gc();
		mstCost = 0;
		double t = System.currentTimeMillis();
		primMatrix();
		double tt = System.currentTimeMillis();

//		System.out.println("Number of Edges = " + m + "\nMemory Used = "
//				+ getMemoryUsed() + " MB");
		// System.out.println("TIME = " + tt+"    "+t);
		tt -= t;
		System.out.println("Prim Matrix = " + mstCost);
		System.out.println("TIME = " + tt);

		Arrays.fill(dis, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		mstCost = 0;
		runtime.gc();
		t = System.currentTimeMillis();
//		PrimPriorityQueue();
		primList();
		tt = System.currentTimeMillis();
		// System.out.println("TIME = " + tt+"    "+t);
		tt -= t;
		System.out.println("Prim List = " + mstCost);
		System.out.println("TIME = " + tt);
		t = System.currentTimeMillis();
		PrimPriorityQueue();
//		primList();
		tt = System.currentTimeMillis();
		// System.out.println("TIME = " + tt+"    "+t);
		tt -= t;
		System.out.println("Prim PQ = " + mstCost);
		System.out.println("TIME = " + tt);
//		System.out.println("Number of Edges = " + m + "\nMemory Used = "
//				+ getMemoryUsed() + " MB");
		// System.out.println(mstCost);
		// Arrays.fill(visited, false);
		// mstCost = 0;
		// // System.out.println(mstCost);
		// System.out.println("Prim Priority Queue = " + mstCost);
		// }
//		GraphGenerator g = new GraphGenerator();
//		g.generateGraph(1000, true, 1000);
		input.close();
	}
}

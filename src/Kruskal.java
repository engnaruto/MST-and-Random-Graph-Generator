import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge> {
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

class Nodes {
	int to;
	double w;

	public Nodes(int to, double w) {
		this.to = to;
		this.w = w;
	}
}

public class Kruskal {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				new FileInputStream("in.txt")));
		String s = input.readLine();
		// int t = Integer.parseInt(s);
		// for (int u = 0; u < t; u++) {
		// s = input.readLine();
		int n = Integer.parseInt(s);
		s = input.readLine();
		int m = Integer.parseInt(s);

//		ArrayList<Nodes> arr[] = new ArrayList[n];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = new ArrayList<>();
//		}
		UnionFind union = new UnionFind(n);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
//		PriorityQueue<Edge> pqlist = new PriorityQueue<>();
		for (int i = 0; i < m; i++) {
			String[] a = input.readLine().split(",");
			int from = Integer.parseInt(a[0]);
			int to = Integer.parseInt(a[1]);
			double w = Double.parseDouble(a[2]);
			// if (u == 0) {
			from--;
			to--;
			// }
//			arr[from].add(new Nodes(to, w));
			pq.add(new Edge(from, to, w));
		}
		double mstCost = 0;

		double t = System.currentTimeMillis();
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (!union.isUnion(edge.from, edge.to)) {
				union.union(edge.from, edge.to);
				mstCost += edge.w;
				if (union.forests == 1) {
					break;
				}
			}

		}

		double tt = System.currentTimeMillis();
		tt -= t;
		System.out.println("TIME = " + tt);
		System.out.println(mstCost);
		// }
		input.close();
	}
}
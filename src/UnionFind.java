public class UnionFind {
	int[] array;
	int forests;

	public UnionFind(int numElements) {
		array = new int[numElements];
		forests = numElements;
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
	}

	public void union(int a, int b) {
		int root_a = find(a);
		int root_b = find(b);
		if (root_a == root_b)
			return;
		if (array[root_b] < array[root_a]) {
			array[root_b] += array[root_a];
			array[root_a] = root_b;
		} else {
			array[root_a] += array[root_b];
			array[root_b] = root_a;
		}
		forests--;
	}

	public int find(int x) {
		if (array[x] < 0) {
			return x;
		} else {
			array[x] = find(array[x]);
			return array[x];
		}
	}

	public boolean isUnion(int a, int b) {
		return find(a) == find(b);
	}

	public int maxSet() {
		int max = Integer.MAX_VALUE;

		for (int i = 0; i < array.length; i++) {
			max = Math.min(max, array[i]);
		}
		return max * -1;
	}
}

import java.util.*;
import java.io.*;

public class tree_height {

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}

class FastScanner {
    StringTokenizer tok = new StringTokenizer("");
    BufferedReader in;

    FastScanner() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() throws IOException {
        while (!tok.hasMoreElements())
            tok = new StringTokenizer(in.readLine());
        return tok.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}

class TreeHeight {
    int n;
    int parent[];

    int root = -1;
    HashMap<Integer, List<Integer>> nodes = new HashMap<>();

    public TreeHeight() {
    }

    public TreeHeight(int[] parent) {
        this.n = parent.length;
        for (int i = 0; i < n; i++) {
            addNode(parent[i], i);
        }
    }

    void read() throws IOException {
        FastScanner in = new FastScanner();
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            addNode(in.nextInt(), i);
        }
    }

    private void addNode(int parent, int child) {
        nodes.computeIfAbsent(child, node -> new LinkedList<Integer>());
        if (parent == -1) {
            root = child;
        } else {
            nodes.computeIfAbsent(parent, node -> new LinkedList<Integer>()).add(child);
        }
    }

    int computeHeight() {
        return computeHeight(root, 0);
    }

    int computeHeight(int node, int height) {
        int maxheight = ++height;
        for (int child : nodes.get(node)) {
            maxheight = Math.max(maxheight, computeHeight(child, height));
        }
        return maxheight;
    }

    int naiveComputeHeight() {
        // Replace this code with a faster implementation
        int maxHeight = 0;
        for (int vertex = 0; vertex < n; vertex++) {
            int height = 0;
            for (int i = vertex; i != -1; i = parent[i])
                height++;
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }
}

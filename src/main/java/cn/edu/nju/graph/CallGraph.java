package cn.edu.nju.graph;

import cn.edu.nju.util.FileHelper;

import java.util.*;

public class CallGraph {

    private Map<String, List<String>> callGraph = new HashMap<>();

    int edgeNum;

    public CallGraph(String callGraphFile) {
        this.edgeNum = 0;
        List<String> edges = FileHelper.readFile(callGraphFile);
        for(String edge : edges) {
            String [] nodes = edge.split(" ");
            if (nodes.length == 2) {
                this.edgeNum++;
                if(callGraph.containsKey(nodes[0])) {
                    callGraph.get(nodes[0]).add(nodes[1]);
                }
                else {
                    List<String> nodeList = new ArrayList<>();
                    nodeList.add(nodes[1]);
                    callGraph.put(nodes[0], nodeList);
                }
            }
        }
    }


    public Set<String> getNodes() {
        return callGraph.keySet();
    }

    public List<String> getEdges(String node) {
        return callGraph.get(node);
    }

    public double computeGraphDiff(CallGraph g) {

        int sameEdgesNum = 0;
        Set<String> nodes1 = this.getNodes();
        Set<String> nodes2 = g.getNodes();

        for(String node : nodes1) {
            if(nodes2.contains(node)) {
                List<String> edges1 = this.getEdges(node);
                List<String> edges2 = g.getEdges(node);

                List<String> tmp = new ArrayList<>();
                tmp.addAll(edges2);

                for(String edge : edges1) {
                    if(tmp.contains(edge)) {
                        sameEdgesNum++;
                        tmp.remove(edge);
                    }
                }

            }

        }

        int diff = (this.getEdgeNum() + g.getEdgeNum()) - 2 * sameEdgesNum;

        return (double)diff / (double)(this.getEdgeNum() + g.getEdgeNum() - sameEdgesNum);

    }

    public int getEdgeNum() {
        return this.edgeNum;
    }

    public String [] bfs(String start) {


        List<String> res = new ArrayList<>();

        Set<String> nodes = this.getNodes();

        List<String> notVisited = new ArrayList<>(nodes);

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            String v = queue.poll();
            if (v != null && notVisited.contains(v)) {

                List<String> edges = getEdges(v);
                res.add(v);
                for (String e : edges) {
                    queue.offer(e);
                }
                notVisited.remove(v);

            }
            else {
                if(!notVisited.isEmpty()) {
                    queue.offer(notVisited.get(0));
                }

            }

        }

        String [] result = new String[res.size()];
        res.toArray(result);

        return result;
    }


    public double computeEditDistance(CallGraph g) {

        Set<String> set = new HashSet<>();
        set.addAll(g.getNodes());
        set.retainAll(this.getNodes());

        if(set.isEmpty()) {
            return 1.0;
        }

        String start = set.iterator().next();

        String [] bfs1 = this.bfs(start);
        String [] bfs2 = g.bfs(start);

        int len = bfs1.length > bfs2.length ? bfs1.length : bfs2.length;

        int [][] dif = new int[bfs1.length + 1][bfs2.length + 1];

        for(int i = 0; i <= bfs1.length; i++) {
            dif[i][0] = i;
        }

        for(int i = 0; i <= bfs2.length; i++) {
            dif[0][i] = i;
        }


        int temp;
        for(int i = 1; i <= bfs1.length; i++) {
            for(int j = 1; j <= bfs2.length; j++) {
                if(bfs1[i - 1].equals(bfs2[j - 1])) {
                    temp = 0;
                }
                else {
                    temp = 1;
                }

                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }


        }


        return (double) dif[bfs1.length][bfs2.length] / len;
    }


    private int min(int ...is ) {
        int min = Integer.MAX_VALUE;
        for(int i : is) {
            if(min > i) {
                min = i;
            }
        }
        return min;
    }
}

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
                for(String edge : edges1) {
                    if(edges2.contains(edge)) {
                        sameEdgesNum++;
                    }
                }

            }

        }

        int diff = (this.getEdgeNum() + g.getEdgeNum()) - 2 * sameEdgesNum;

        return (double)diff / (double)(this.getEdgeNum() + g.getEdgeNum());

    }

    public int getEdgeNum() {
        return this.edgeNum;
    }
}

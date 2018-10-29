package cn.edu.nju;

import cn.edu.nju.graph.CallGraph;

public class Main {
    public static void main(String[] args) {
        if(args.length == 2) {
            CallGraph g1 = new CallGraph(args[0]);
            CallGraph g2 = new CallGraph(args[1]);

            System.out.println("E(g1) = " + g1.getEdgeNum() + " E(g2) = " + g2.getEdgeNum());
            System.out.println("Diff(g1, g2) =  " + g1.computeGraphDiff(g2) + " (by edges.)");

            System.out.println("Diff(g1, g2) = " + g1.computeEditDistance(g2) + " (by nodes.)");
        }
        else {
            System.out.println("Usage: java Main graphFile1 graphFile2");
        }
    }
}

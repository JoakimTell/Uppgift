
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ordkedjor {

    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    public static void main(String[] args) throws Exception {
        int testSize =
                13;
//                        250;
//                5757;
        testWords("files/test" + testSize + ".txt",
                readFileToWords("files/words" + testSize + ".txt"));
    }
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    public static void testWords(String fnam, ArrayList<String> words) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(fnam)));
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        Digraph digraph = createDirectedGraphSlow(words); // Use same graph for each search.
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            assert line.length() == 11; // indatakoll, om man kör med assertions på
            String start = line.substring(0, 5);
            String goal = line.substring(6, 11);
            // ... sök väg från start till goal här
            //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
            int startNode = words.indexOf(start); // Represent start and goal nodes as integers.
            int goalNode = words.indexOf(goal);
            BreadthFirstDirectedPaths bfp = new BreadthFirstDirectedPaths(digraph, startNode);
            int distance = bfp.distTo(goalNode);
            if (distance == Integer.MAX_VALUE) { // No path => infinity(=Integer.MAX_VALUE) distance.
                distance = -1;
            }
            System.out.println(distance);
            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        }
    }

    private static ArrayList<String> readFileToWords(String fnam) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(fnam)));
        ArrayList<String> words = new ArrayList<String>();
        while (true) {
            String word = r.readLine();
            if (word == null) {
                break;
            }
            assert word.length() == 5;  // indatakoll, om man kör med assertions på
            words.add(word);
        }
        return words;
    }

    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

/**
     * O(V^2).
     *
     * @param words list of words to construct graph
     * @return directed graph
     */

    //Metoden compareWords och createDirectedGraphSlow funkar för 13 och 250 txt filen men inte 5757.
    public static Digraph createDirectedGraphSlow(ArrayList<String> words) {
        Digraph diGraph = new Digraph(words.size());
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                if (compareWords(words.get(i), words.get(j))) {
                    diGraph.addEdge(i, j);
                }
            }
        }
        return diGraph;
    }

    public static boolean compareWords(String word1, String word2) {
        for(int i=1; i<5; i++) {
            if(word2.indexOf(word1.charAt(i))>=0) {
                word2.replace(word1.charAt(i), ' ');
            }
            else
                return false;
        }

        return true;
    }
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
}

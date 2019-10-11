package by.sci.laba2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppRunner {


    private static List<Node> listNodeSearch = new ArrayList<>();

    public static List<Node> getElementsCode(String str) {
        listNodeSearch.clear();
//        String str = "programming languages";
        String code = "";
        List<Node> collect = Stream.of(str.split(""))
                .sorted()
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                .entrySet().stream()
                .map(x -> new Node(x.getKey(), x.getValue(), 0))
                .sorted()
                .collect(Collectors.toList());

        Node rootNode = createTree(collect);
        deepSearchNode(rootNode);
        rootNode.setRoot(true);
        return listNodeSearch.stream()
                .sorted().collect(Collectors.toList());
    }

    public static String getCode (List<Node> listNodeSearch,String str){
        Map<String, String> mapCodes = listNodeSearch.stream().collect(Collectors.toMap(Node::getSymbols, Node::getCode));
        String code = "";
        code = str.chars()
                .mapToObj(x -> mapCodes.get(String.valueOf((char) x)))
                .reduce((x, y) -> x + y).get();
        return code;
    }
    private static void deshrift(Map<String, String> mapCodes, String mainCode) {
        String code = "";
        char[] chars = mainCode.toCharArray();
        for (char charX : chars) {
            code += charX;
            if (mapCodes.get(code) != null) {
                System.out.print(mapCodes.get(code));
                code = "";
            }

        }
    }


    private static String currentValue = "";

    private static void deepSearchNode(Node rootNode) {
        for (Node node : rootNode.getNodes()) {
            currentValue += node.getNumber();
            if (node.getNodes().size() == 0) {
                node.setCode(currentValue);
                listNodeSearch.add(node);
                currentValue = currentValue.substring(0, currentValue.length() - 1);
            } else {
                deepSearchNode(node);
            }
        }
        currentValue = (currentValue.length() > 0) ? currentValue.substring(0, currentValue.length() - 1) : "";
    }


    private static Node createTree(List<Node> list) {
        Node min1 = list.stream()
                .min(Comparator.comparing(Node::getWight)).get();
        list.remove(min1);
        Node min2 = list.stream()
                .min(Comparator.comparing(Node::getWight)).get();
        list.remove(min2);
        Node rootNode;
        if (min1.getWight() < min2.getWight()) {

            min1.setNumber(1);
            min2.setNumber(0);
            rootNode = createNode(min2, min1);
        } else {
            min1.setNumber(0);
            min2.setNumber(1);
            rootNode = createNode(min1, min2);
        }
        list.add(rootNode);
        if (list.size() != 1) {
            return createTree(list);
        } else {
            return rootNode;
        }
    }

    private static Node createNode(Node min, Node max) {
        Node node = new Node(max.getSymbols() + min.getSymbols(), min.getWight() + max.getWight(), 0);
        node.getNodes().add(min);
        node.getNodes().add(max);
        return node;
    }

}

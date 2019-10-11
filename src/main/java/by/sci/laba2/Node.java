package by.sci.laba2;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

    private String symbols;
    private Long wight;
    private Integer number;
    private String code;
    private Boolean root = false;

    private List<Node> nodes = new ArrayList<Node>();

    public Node(String symbols, Long wight, Integer number) {
        this.symbols = symbols;
        this.wight = wight;
        this.number = number;
    }


    public Node(String symbols, Integer wight, Integer number) {
        this.symbols = symbols;
        this.wight = new Long(wight);
        this.number = number;
    }

    public boolean isHasChilds() {
        return (nodes.size() > 0);
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public Long getWight() {
        return wight;
    }

    public void setWight(Long wight) {
        this.wight = wight;
    }

    public void setWight(Integer wight) {
        this.wight = new Long(wight);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "by.csi.Node{" +
                "symbols='" + symbols + '\'' +
                ", wight=" + wight +
                ", number=" + number +
                '}';
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

    public int compareTo(Node o) {
        return (int) (this.wight - o.wight);
    }
}

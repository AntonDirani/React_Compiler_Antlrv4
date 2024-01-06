package AST.JSX;


import java.io.FileWriter;
import java.util.LinkedList;


public abstract class Node {

    protected static Integer nr;
    protected Integer lineNum;

    private final LinkedList<Node> children = new LinkedList<>();


    public void addChild(Node node) {
        if (node != null) {
            this.children.add(node);
        }
    }

    public void addChildren(LinkedList<Node> nodes) {
        this.children.addAll(nodes);

    }

    public Node getChild(int i) {
        return this.children.get(i);
    }

    protected String nodeInfo() {
        return String.format("%s", this.getClass().getSimpleName());
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node child : this.children){
            stringBuilder.append(child);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }



    public LinkedList<Node> getChildren() {
        return this.children;
    }

    public void print(FileWriter writer) throws Exception {
        Node.nr = 0;
        writer.write("digraph {\ngraph [ordering=\"out\"];\n");
        printNode(writer);
        writer.write("}\n");
    }



    public int printNode(FileWriter writer) throws Exception {
        Integer my_nr = Node.nr++;

        writer.write(String.format("node%d[shape=record label=\"", my_nr));

        writer.write(this.nodeInfo());

        writer.write("\"];\n");

        for (Node child : this.getChildren()) {
            Integer child_nr = child.printNode(writer);
            writer.write(String.format("node%d -> node%d;\n", my_nr, child_nr));
        }

//        Integer prev_child_nr = 0;
//        for (int i=0;i<this.getChildren().size();i++){
//            Integer child_nr = this.getChildren().get(i).printNode(writer);
//            if (i==0){
//                writer.write(String.format("node%d -> node%d;\n", my_nr, child_nr));
//            }
//            else{
//                writer.write(String.format("node%d -> node%d;\n", prev_child_nr, child_nr));
//            }
//            prev_child_nr = child_nr;
//            writer.write(String.format("node%d -> node%d;\n", child_nr, my_nr));
//        }

        return my_nr;
    }

}

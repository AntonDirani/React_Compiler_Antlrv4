package AST;

public class ParamterNode extends Node {

    String paramName;

    public ParamterNode(String paramName) {
        this.paramName = paramName;
    }

    @Override
    protected String nodeInfo() {
            return String.format("%s|%s", this.getClass().getSimpleName(), this.paramName);

    }
}

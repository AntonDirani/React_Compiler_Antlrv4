package AST;

public class ImportStatementNode extends StatementNode {
    public String importPath;
    public String libraryName;

    public ImportStatementNode(String importPath, String libraryName) {
        this.importPath = importPath;
        this.libraryName=libraryName;
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s | %s | %s", this.getClass().getSimpleName(), "Library Name: " + libraryName, "Path: " + importPath.substring(1, importPath.length() - 1));
    }

        @Override
     public String toString() {
        return String.format("%s | %s, %s", this.getClass().getSimpleName(), "Library Name: " +libraryName ,"Path: " +importPath/*.substring(1, importPath.length() - 1)*/);
    }
}

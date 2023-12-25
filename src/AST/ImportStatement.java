package AST;

public class ImportStatement extends Statement {
    public String importPath;
    public String libraryName;

    public ImportStatement(String importPath, String libraryName) {
        this.importPath = importPath;
        this.libraryName=libraryName;
    }


        @Override
     public String toString() {
        return String.format("%s | %s, %s", this.getClass().getSimpleName(), "Library Name: " +libraryName ,"Path: " +importPath/*.substring(1, importPath.length() - 1)*/);
    }
}

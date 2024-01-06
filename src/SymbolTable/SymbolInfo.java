package SymbolTable;

public class SymbolInfo {

    private String name;
    private String type;
    private String value;
    private String memoryLocation;
    private String scope;

    public SymbolInfo() {

    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {

        return name;
    }

    public void setValue(String value) {
         this.value = value;
    }
    public Object getValue() {
        return value;
    }

    public void setType(String type) {
         this.type = type;
    }
    public String getType() {
        return type;
    }

    public String getMemoryLocation() {
        return memoryLocation;
    }

    public String getScope() {
        return scope;
    }
}

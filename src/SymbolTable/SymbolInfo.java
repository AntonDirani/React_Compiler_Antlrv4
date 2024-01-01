package SymbolTable;

public class SymbolInfo {


    //name ,type : type maybe int , string ,func, class or ,memory location,scope ,value : value for variable or parameter for func
    private String name;
    private String type;
    private String value;
    private String memoryLocation; // تفترض وجود هذه المعلومة في جدول الرموز
    private String scope; // تفترض وجود هذه المعلومة في جدول الرموز


    public SymbolInfo() {

    }


    /*public SymbolInfo(String type,String name) {
        this.type =type;
        this.name = name;
        }

    public SymbolInfo(String type,String name, String value) {
        this.type =type;
        this.name = name;
        this.value = value;

    }
    public SymbolInfo(String type,String name, String value,String memoryLocation,String scope) {
        this.type =type;
        this.name = name;
        this.value = value;
        this.memoryLocation = memoryLocation;
        this.scope = scope;
    }
*/


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

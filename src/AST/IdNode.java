package AST;

public class IdNode   extends Statement{

    String Id;


    public IdNode(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "{"+ Id + '\'' +
                '}';
    }
}

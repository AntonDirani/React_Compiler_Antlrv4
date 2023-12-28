package AST;

public class StringInterpolation extends  Statement{
    String sDollar;
    String thisKeyWord;
    String dot;
    String id;

    /*  public StringInterpolation(String sDollar,String id) {
          this.sDollar = sDollar;
          this.id = id;
      }
  */
    public StringInterpolation(String sDollar, String thisKeyWord,String dot,String id) {
        this.sDollar = sDollar;
        this.thisKeyWord = thisKeyWord;
        this.dot = dot;
        this.id = id;
    }

    @Override
    public String toString() {
        return  String.format("%s{%s%s%s}",sDollar,thisKeyWord,dot,id);

    }
}

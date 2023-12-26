package AST.React.ReactHooks;

import AST.Statement;

public class PairValue extends Statement {

    String first_var;
    String second_var;

    public PairValue(String first_var, String second_var) {
        this.first_var = first_var;
        this.second_var = second_var;
    }

    @Override
    public String toString() {
        return "PairValue{" +
                "first_var='" + first_var + '\'' +
                ", second_var='" + second_var + '\'' +
                '}';
    }
}

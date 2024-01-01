package SymbolTable;

import java.util.ArrayList;
import java.util.List;


public class SymbolTable
{
    //Map<String, SymbolInfo> table;
    List<SymbolInfo> row;// = new ArrayList<>();

    public SymbolTable()
    {
        this.row = new ArrayList<>();
    }

    public List<SymbolInfo> getRow()
    {
        return row;
    }
    public void setRow(List<SymbolInfo> row){this.row =row;}

/*
    public void addVariable(String name, String type, Object value, String memoryLocation, String scope) {
        SymbolInfo symbolInfo = new SymbolInfo(name, type, (String) value, memoryLocation, scope);
        symbolTable.put(name, symbolInfo);
    }

    public void addClass(String className, String memoryLocation) {
        SymbolInfo symbolInfo = new SymbolInfo(className, "Class", null, memoryLocation, "FileScope");
        symbolTable.put(className, symbolInfo);
    }

    public void addFunction(String functionName, String returnType, String memoryLocation, String scope) {
        SymbolInfo symbolInfo = new SymbolInfo(functionName, "Function", null, memoryLocation, scope);
        symbolTable.put(functionName, symbolInfo);
    }
    public void addSymbol(String name, String type) {
      if (!row.contains(name)) {
          SymbolInfo info = new SymbolInfo(name, type);
          row.add(Integer.parseInt(name), info);
      } else {
          System.out.println("Error: Symbol " + name + " is already defined.");
      }
     }*/

  public void print()
  {
      //System.out.println("SymbolTable| " +"Type"+ "\t   " +"name"+ "\t\t\t"+"value");
      System.out.println("\nSymbol table contents: ");

      for (int i = 0 ; i< row.size();i++)
      {
         if(row.get(i) != null)
         {
             System.out.println(row.get(i).getType() + "\t\t\t" +row.get(i).getName()+ "\t\t\t" +row.get(i).getValue());
         }
      }
  }
 /*   @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Symbol Table:\n");
        for (SymbolInfo info : this.row) {
            result.append("Type: ").append(info.getType()).append(", value: ").append(info.getValue()).append("\n");
        }
        return result.toString();
    }*/

}

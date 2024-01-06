package SymbolTable;

import java.util.ArrayList;
import java.util.List;


public class SymbolTable
{
    List<SymbolInfo> row;

    public SymbolTable()
    {
        this.row = new ArrayList<>();
    }

    public List<SymbolInfo> getRow()
    {
        return row;
    }
    public void setRow(List<SymbolInfo> row){this.row =row;}



  public void print()
  {
      System.out.println("\nSymbol table contents: ");

      for (int i = 0 ; i< row.size();i++)
      {
         if(row.get(i) != null)
         {
             System.out.println(row.get(i).getType() + "\t\t\t" +row.get(i).getName()+ "\t\t\t" +row.get(i).getValue());
         }
      }
  }
}

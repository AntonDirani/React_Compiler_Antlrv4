
import AST.Program;

import Visitor.MyVisitor;
import grammar.LexerGram;
import grammar.ParserGram;

import org.antlr.v4.runtime.CharStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {
    public static void main(String []args) throws Exception {
        String source = "Files/test.txt";
        CharStream charStream = fromFileName(source);
        LexerGram lexer = new LexerGram(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserGram parser = new ParserGram(tokens);
        ParseTree ast = parser.program();
        MyVisitor visitor = new MyVisitor();
        Program program = (Program) visitor.visit(ast);
        // Creating a File object that
        // represents the disk file
        PrintStream o = new PrintStream(new File("AST_Output.txt"));

        // Store current System.out
        // before assigning a new value
        PrintStream console = System.out;

        // Assign o to output stream
        // using setOut() method
        System.setOut(o);

        // Display message only
        System.out.println(
                program);

        // Use stored value for output stream
        System.setOut(console);

        // Display message only
       // System.out.println(program);
        System.out.println("\nAST \n"+program);


    }
}




























































/*
import old.Node;
import old.ProgramNode;
import grammar.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileWriter;
import java.io.IOException;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {
    public static void main(String []args) throws Exception {
        String source = "Files/test.txt";
        CharStream charStream = fromFileName(source);
        LexerGram lexer = new LexerGram(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserGram parser = new ParserGram(tokens);
        ParseTree ast = parser.program();
        MyVisitor example1Visitor = new MyVisitor();
        Node program = (Node) example1Visitor.visit(ast);
        System.out.println(program);


        *//*String testFilepath = "Files/test.txt";

        // Initialize streams and parser
        CharStream charStream = CharStreams.fromFileName(testFilepath);
        LexerGram lexer = new LexerGram(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserGram parser = new ParserGram(tokens);

        // Get parseTree
        ParseTree parseTree = parser.program();

        // Get AST and write it to a text file
        FileWriter writer = new FileWriter("ast1.dot");
        MyVisitor visitor = new MyVisitor();

        ProgramNode programNode = (ProgramNode) visitor.visit(parseTree);
        System.out.println(programNode);
        programNode.print(writer);



        writer.close();
*//*
    }
}*/

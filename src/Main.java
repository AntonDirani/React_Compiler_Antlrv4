
import AST.ProgramNode;
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
       /* String source = "Files/test.txt";
        CharStream charStream = fromFileName(source);
        LexerGram lexer = new LexerGram(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserGram parser = new ParserGram(tokens);
        ParseTree ast = parser.program();
        MyVisitor example1Visitor = new MyVisitor();
        Node program = (Node) example1Visitor.visit(ast);
        System.out.println(program);*/


        String testFilepath = "Files/test.txt";

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

    }
}

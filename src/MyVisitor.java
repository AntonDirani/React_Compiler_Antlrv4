import  AST.*;

import AST.Variables.VariableAssignmentNode;
import AST.Variables.VariableDeclarationNode;
import grammar.ParserGram;
import grammar.ParserGramBaseVisitor;

public  class MyVisitor extends ParserGramBaseVisitor {


    public ProgramNode visitProgram(ParserGram.ProgramContext ctx){

        ProgramNode programNode = new ProgramNode();
        //System.out.println(ctx.importStatement().size());
       /* for (int i = 0; i < ctx.importStatement().size(); i++) {
            programNode.addChild((Node) visitImportStatement(ctx.importStatement(i)));
        }*/

            for (int i = 0; i < ctx.statement().size(); i++) {
                Node node = (Node) visitStatement(ctx.statement(i));
                programNode.addChild(node);
                /*if (ctx.statement(i).variableDeclaration() != null) {
                    LinkedList<Node> listnode = (LinkedList<Node>) visitStatement(ctx.statement(i));
                    programNode.addChildren(listnode);
                } else {
                    Node node = (Node) visitStatement(ctx.statement(i));
                    programNode.addChild(node);
                }*/
            }

        return programNode;
    }

    @Override
    public Node visitImportStatement(ParserGram.ImportStatementContext ctx) {
        String importPath = ctx.StringLiteral().getText();
        String libraryName = "";
        if (ctx.ID()!=null){
            libraryName = ctx.ID().getText();
        }
        if (ctx.REACT()!=null){
             libraryName = ctx.REACT().getText();
        }
        //TODO: call hook here


        return new ImportStatementNode(importPath,libraryName);
    }

    @Override
    public Object visitAssignment(ParserGram.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        Node value;
        value = (Node) visitVariableValues(ctx.variableValues());
        return new VariableAssignmentNode(varName, value);
    }

    @Override
    public Object visitStatement(ParserGram.StatementContext ctx) {
    return super.visitStatement(ctx);
    }

    @Override
    public String visitDataType(ParserGram.DataTypeContext ctx) {
        String type = "null";
        if (ctx.CONST() != null) {
            type = "Const";
        } else if (ctx.LET() != null) {
            type = "Let";
        } else if (ctx.VAR() != null) {
            type = "Var";
        }
            return type;

    }

    @Override
    public LiteralNode visitLiteral(ParserGram.LiteralContext ctx) {
        String literal = ctx.getText();
        return new LiteralNode(literal);
    }



    @Override
    public Node visitVariableValues(ParserGram.VariableValuesContext ctx) {

         Node value = null;
        if (ctx.literal() != null) {
            value= visitLiteral(ctx.literal());
        }/* else if (ctx.hook() != null) {
            value= visitHook(ctx.hook());
        }*/ else if (ctx.ID()!= null) {
            value= new LiteralNode(ctx.ID(0).getText()) ;
        }
        return value;
    }

    /*@Override
    public Node visitHook(ParserGram.HookContext ctx) {
       // return super.visitHook(ctx);
        return Nod;
    }*/

    public Node visitVariableDeclaration(ParserGram.VariableDeclarationContext ctx ){

       // LinkedList<Node> ans = new LinkedList<>();
        String type =  visitDataType(ctx.dataType());
        String variableName = ctx.ID().getText();
            Node initializer;
            initializer = (Node) visitVariableValues(ctx.variableValues());

        return new  VariableDeclarationNode(ctx.getStart().getLine(),type, variableName, initializer);

    }






}
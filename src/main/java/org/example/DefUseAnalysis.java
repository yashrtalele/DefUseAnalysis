package org.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DefUseAnalysis {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("/Users/yash/IdeaProjects/BankingSystem/src/main/java/org/example/BankingSystem.java");
            CompilationUnit cu = StaticJavaParser.parse(fis);

            cu.accept(new DefUseVisitor(), null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class DefUseVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(VariableDeclarator var, Void arg) {
            super.visit(var, arg);
            System.out.println("Definition: " + var.getName() + " in " + var.getRange());
        }

        @Override
        public void visit(NameExpr nameExpr, Void arg) {
            super.visit(nameExpr, arg);
            System.out.println("Usage: " + nameExpr.getName() + " in " + nameExpr.getRange());
        }

        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
            System.out.println("\nAnalyzing method: " + md.getNameAsString());
        }
    }
}
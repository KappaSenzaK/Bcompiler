package com.example.me.codeAnalysis;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BinaryExpressionSyntax extends ExpressionSyntax {
    private ExpressionSyntax left;
    private SyntaxToken operatorToken;
    private ExpressionSyntax right;
    

    public BinaryExpressionSyntax(ExpressionSyntax left, SyntaxToken operatorToken, ExpressionSyntax right) {
        this.left = left;
        this.operatorToken = operatorToken;
        this.right = right;
    }


    public ExpressionSyntax getLeft() {
        return left;
    }


    public void setLeft(ExpressionSyntax left) {
        this.left = left;
    }


    public SyntaxToken getOperatorToken() {
        return operatorToken;
    }


    public void setOperatorToken(SyntaxToken operatorToken) {
        this.operatorToken = operatorToken;
    }


    public ExpressionSyntax getRight() {
        return right;
    }


    public void setRight(ExpressionSyntax right) {
        this.right = right;
    }


    @Override
    public SyntaxKind kind() {
        return SyntaxKind.BINARY_EXPRESSION;
    }


    @Override
    public Iterable<SyntaxNode> getChildren() {
        return new Iterable<SyntaxNode>() {
            @Override
            public Iterator<SyntaxNode> iterator() {
                return new Iterator<SyntaxNode>() {
                    private int index = 0;
            
                    @Override
                    public boolean hasNext() {
                        return index < 3;
                    }
            
                    @Override
                    public SyntaxNode next() {
                        switch (index) {
                            case 0:
                                index++;
                                return left;
                            case 1:
                                index++;
                                return operatorToken;
                            case 2:
                                index++;
                                return right;
                            default:
                                throw new NoSuchElementException();
                        }
                    }
                };
            }
        };
    }

    
}
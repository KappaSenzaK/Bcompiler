package com.example.me.codeAnalysis;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ParanthesesExpressionSyntax extends ExpressionSyntax {
    private SyntaxNode openParenthesesToken;
    private ExpressionSyntax expression;
    private SyntaxNode closedParenthesestoken;

    public ParanthesesExpressionSyntax(SyntaxNode openParenthesesToken, ExpressionSyntax expression, SyntaxNode closedParenthesestoken) {
        this.openParenthesesToken = openParenthesesToken;
        this.expression = expression;
        this.closedParenthesestoken = closedParenthesestoken;

    }

    @Override
    public SyntaxKind kind() {
        return SyntaxKind.PARENTHESESED_EXPRESSION;
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
                                return openParenthesesToken;
                            case 1:
                                index++;
                                return expression;
                            case 2:
                                index++;
                                return closedParenthesestoken;
                            default:
                                throw new NoSuchElementException();
                        }
                    }
                };
            }
        };
    }

    public SyntaxNode getOpenParenthesesToken() {
        return openParenthesesToken;
    }

    public void setOpenParenthesesToken(SyntaxNode openParenthesesToken) {
        this.openParenthesesToken = openParenthesesToken;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }

    public void setExpression(ExpressionSyntax expression) {
        this.expression = expression;
    }

    public SyntaxNode getClosedParenthesestoken() {
        return closedParenthesestoken;
    }

    public void setClosedParenthesestoken(SyntaxNode closedParenthesestoken) {
        this.closedParenthesestoken = closedParenthesestoken;
    }

    
    
}

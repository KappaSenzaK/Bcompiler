package com.example.me.codeAnalysis;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnaryExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken operatorToken;
    private ExpressionSyntax operand;

    public UnaryExpressionSyntax(SyntaxToken operatorToken, ExpressionSyntax operand) {
        this.operatorToken = operatorToken;
        this.operand = operand;
    }

    public SyntaxToken getOperatorToken() {
        return operatorToken;
    }

    public ExpressionSyntax getOperand() {
        return operand;
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
                        return index < 2;
                    }

                    @Override
                    public SyntaxNode next() {
                        switch (index) {
                            case 0:
                                index++;
                                return operatorToken;
                            case 1:
                                index++;
                                return operand;
                            default:
                                throw new NoSuchElementException();
                        }
                    }
                };
            }
        };
    }
}

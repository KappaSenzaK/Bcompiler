package com.example.me.codeAnalysis;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class LiteralExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken literalToken;

    public LiteralExpressionSyntax(SyntaxToken literalToken) {
        this.literalToken = literalToken;
    }

    @Override
    public SyntaxKind kind() {
        return SyntaxKind.NUMBER_EXPRESSION;
    }
    
    public SyntaxToken getLiteralToken() {
        return literalToken;
    }

    public void setLiteralToken(SyntaxToken numberToken) {
        this.literalToken = numberToken;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return new Iterable<SyntaxNode>() {
            @Override
            public Iterator<SyntaxNode> iterator() {
                return new Iterator<SyntaxNode>() {
                    private boolean hasMore = true;
    
                    @Override
                    public boolean hasNext() {
                        return hasMore;
                    }
    
                    @Override
                    public SyntaxNode next() {
                        if (hasMore) {
                            hasMore = false;
                            return literalToken;
                        } else {
                            throw new NoSuchElementException();
                        }
                    }
                };
            }
        };
    }
}

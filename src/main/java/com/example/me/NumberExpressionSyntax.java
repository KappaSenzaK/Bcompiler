package com.example.me;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class NumberExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken numberToken;

    public NumberExpressionSyntax(SyntaxToken numberToken) {
        this.numberToken = numberToken;
    }

    @Override
    public SyntaxKind kind() {
        return SyntaxKind.NUMBER_EXPRESSION;
    }
    
    public SyntaxToken getNumberToken() {
        return numberToken;
    }

    public void setNumberToken(SyntaxToken numberToken) {
        this.numberToken = numberToken;
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
                            return numberToken;
                        } else {
                            throw new NoSuchElementException();
                        }
                    }
                };
            }
        };
    }
}

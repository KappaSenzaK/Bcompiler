package com.example.me.codeAnalysis;

import java.util.List;

public final class LiteralExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken literalToken;

    public LiteralExpressionSyntax(SyntaxToken literalToken) {
        this.literalToken = literalToken;
    }

    @Override
    public SyntaxKind kind() {
        return SyntaxKind.LITERAL_EXPRESSION;
    }
    
    public SyntaxToken getLiteralToken() {
        return literalToken;
    }

    public void setLiteralToken(SyntaxToken numberToken) {
        this.literalToken = numberToken;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return List.of(literalToken);
    }
}

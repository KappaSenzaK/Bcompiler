package com.example.me.codeanalysis.syntax;

import java.util.List;

public final class LiteralExpressionSyntax extends ExpressionSyntax {
    private SyntaxToken literalToken;
    private Object value;

    public LiteralExpressionSyntax(SyntaxToken literalToken, Object value) {
        this.literalToken = literalToken;
        this.value = value;
    }

    public LiteralExpressionSyntax(SyntaxToken literalToken) {
        this.literalToken = literalToken;
        this.value = literalToken.getValue();
    }

    @Override
    public SyntaxKind kind() {
        return SyntaxKind.LITERAL_EXPRESSION;
    }
    
    public SyntaxToken getLiteralToken() {
        return literalToken;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return List.of(literalToken);
    }
}

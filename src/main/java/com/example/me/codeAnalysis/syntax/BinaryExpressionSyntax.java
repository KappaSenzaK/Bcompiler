package com.example.me.codeanalysis.syntax;

import java.util.List;

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

    public SyntaxToken getOperatorToken() {
        return operatorToken;
    }

    public ExpressionSyntax getRight() {
        return right;
    }

    @Override
    public SyntaxKind kind() {
        return SyntaxKind.BINARY_EXPRESSION;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return List.of(left, operatorToken, right);
    }
}
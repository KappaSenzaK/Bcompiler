package com.example.me.codeanalysis.syntax;

import java.util.List;

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
        return SyntaxKind.UNARY_EXPRESSION;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return List.of(operatorToken, operand);
    }
}

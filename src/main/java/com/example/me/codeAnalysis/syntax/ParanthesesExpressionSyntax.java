package com.example.me.codeanalysis.syntax;

import java.util.List;

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
        return List.of(openParenthesesToken, expression, closedParenthesestoken);
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

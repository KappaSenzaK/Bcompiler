package com.example.me.codeAnalysis;

public final class Evaluator {
    private ExpressionSyntax root;

    public Evaluator(ExpressionSyntax root) {
        this.root = root;
    }

    public int evaluate() {
        return evaluateExpression(root);
    }

    private int evaluateExpression(ExpressionSyntax root) {
        if (root instanceof LiteralExpressionSyntax n) {
            return (int) n.getLiteralToken().getValue();
        }

        if (root instanceof BinaryExpressionSyntax b) {
            int left = evaluateExpression(b.getLeft());
            int right = evaluateExpression(b.getRight());

            SyntaxKind operatorKind = b.getOperatorToken().getKind();

            if(operatorKind == SyntaxKind.PLUS_TOKEN ){ 
                return left + right;
            } else if(operatorKind == SyntaxKind.MINUS_TOKEN) {
                return left - right;
            } else if(operatorKind == SyntaxKind.STAR_TOKEN) {
                return left * right;
            } else if(operatorKind == SyntaxKind.SLASH_TOKEN) {
                return left / right;
            } else {
                throw new RuntimeException("Unexpected oprator token: " + operatorKind);
            }
        }

        if (root instanceof ParanthesesExpressionSyntax p) {
            return evaluateExpression(p.getExpression());
        }
        
        throw new RuntimeException("Unexpected node: " + root.kind());
    }
}

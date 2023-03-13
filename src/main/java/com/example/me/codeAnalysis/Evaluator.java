package com.example.me.codeAnalysis;

import com.example.me.binding.BoundBinaryExpression;
import com.example.me.binding.BoundBinaryOperatorKind;
import com.example.me.binding.BoundExpression;
import com.example.me.binding.BoundLiteralExpression;
import com.example.me.binding.BoundUnaryExpression;
import com.example.me.binding.BoundUnaryOperatorKind;

public final class Evaluator {
    private BoundExpression root;

    public Evaluator(BoundExpression root) {
        this.root = root;
    }

    public Object evaluate() {
        return evaluateExpression(root);
    }

    private Object evaluateExpression(BoundExpression root) {
        if (root instanceof BoundLiteralExpression n) {
            return n.getValue();
        }

        if (root instanceof BoundUnaryExpression u) {
            int operand = (int) evaluateExpression(u.getOperand());

            BoundUnaryOperatorKind kind = u.getOperatorKind();
            switch (kind) {
                case IDENTITY:
                    return -operand;
                case NEGATION:
                    return +operand;
                default:
                    throw new RuntimeException("Unexpected unary operator token: " + kind);
            }
        }

        if (root instanceof BoundBinaryExpression b) {
            int left = (int) evaluateExpression(b.getLeft());
            int right = (int) evaluateExpression(b.getRight());

            BoundBinaryOperatorKind operatorKind = b.getOperatorKind();

            switch (operatorKind) {
                case ADDITION:
                    return left + right;
                case SUBTRACTION:
                    return left - right;
                case MULTIPLICATION:
                    return left * right;
                case DIVISION:
                    return left / right;
                default:
                    throw new RuntimeException("Unexpected binary operator token: " + operatorKind);
            }
        }

        throw new RuntimeException("Unexpected node: " + root.kind());
    }
}

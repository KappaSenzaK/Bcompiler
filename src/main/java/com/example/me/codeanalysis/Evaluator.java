package com.example.me.codeanalysis;

import com.example.me.codeanalysis.binding.BoundBinaryExpression;
import com.example.me.codeanalysis.binding.BoundBinaryOperatorKind;
import com.example.me.codeanalysis.binding.BoundExpression;
import com.example.me.codeanalysis.binding.BoundLiteralExpression;
import com.example.me.codeanalysis.binding.BoundUnaryExpression;
import com.example.me.codeanalysis.binding.BoundUnaryOperatorKind;

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
            Object operand = evaluateExpression(u.getOperand());

            BoundUnaryOperatorKind kind = u.getOperatorKind();
            switch (kind) {
                case IDENTITY:
                    return -(int) operand;
                case NEGATION:
                    return (int) operand;
                case LOGICAL_NEGATION:
                    return !(boolean) operand;
                default:
                    throw new RuntimeException("Unexpected unary operator token: " + kind);
            }
        }

        if (root instanceof BoundBinaryExpression b) {
            Object left = evaluateExpression(b.getLeft());
            Object right = evaluateExpression(b.getRight());

            BoundBinaryOperatorKind operatorKind = b.getOperatorKind();

            switch (operatorKind) {
                case ADDITION:
                    return (int) left + (int) right;
                case SUBTRACTION:
                    return (int) left - (int) right;
                case MULTIPLICATION:
                    return (int) left * (int) right;
                case DIVISION:
                    return (int) left / (int) right;
                case LOGICAL_AND:
                    return (boolean) left && (boolean) right;
                case LOGICAL_OR:
                    return (boolean) left || (boolean) right;
                default:
                    throw new RuntimeException("Unexpected binary operator token: " + operatorKind);
            }
        }

        throw new RuntimeException("Unexpected node: " + root.kind());
    }
}

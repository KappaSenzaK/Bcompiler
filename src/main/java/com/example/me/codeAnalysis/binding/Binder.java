package com.example.me.codeanalysis.binding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.me.codeanalysis.syntax.BinaryExpressionSyntax;
import com.example.me.codeanalysis.syntax.ExpressionSyntax;
import com.example.me.codeanalysis.syntax.LiteralExpressionSyntax;
import com.example.me.codeanalysis.syntax.SyntaxKind;
import com.example.me.codeanalysis.syntax.UnaryExpressionSyntax;

public class Binder {
    private List<String> diagnostics = new ArrayList<>();

    public List<String> diagnostics() {
        return this.diagnostics;
    }

    public BoundExpression bindExpression(ExpressionSyntax syntax) {
        switch (syntax.kind()) {
            case LITERAL_EXPRESSION:
                return bindLiteralExpression((LiteralExpressionSyntax) syntax);
            case UNARY_EXPRESSION:
                return bindUnaryExpression((UnaryExpressionSyntax) syntax);
            case BINARY_EXPRESSION:
                return bindBinaryExpression((BinaryExpressionSyntax) syntax);
            default:
                throw new RuntimeException("Unexpected syntax: " + syntax.kind());
        }
    }

    private BoundExpression bindLiteralExpression(LiteralExpressionSyntax syntax) {
        var value = syntax.getValue() != null ? syntax.getValue() : 0;
        return new BoundLiteralExpression(value);
    }

    private BoundExpression bindUnaryExpression(UnaryExpressionSyntax syntax) {
        BoundExpression boundOperand = bindExpression(syntax.getOperand());
        BoundUnaryOperatorKind boundUnaryOperatorKind = bindUnaryOperatorKind(syntax.getOperatorToken().kind(),
                boundOperand.type());
        if (boundUnaryOperatorKind == null) {
            diagnostics.add("Unary operator '" + syntax.getOperatorToken().getText() + "' is not defined for type "
                    + boundOperand.type());
            return boundOperand;
        }

        return new BoundUnaryExpression(boundUnaryOperatorKind, boundOperand);
    }

    private BoundExpression bindBinaryExpression(BinaryExpressionSyntax syntax) {
        BoundExpression left = bindExpression(syntax.getLeft());
        BoundExpression right = bindExpression(syntax.getRight());
        BoundBinaryOperatorKind boundBinaryOperatorKind = bindBinaryOperatorKind(syntax.getOperatorToken().getKind(),
                left.type(), right.type());
        if (boundBinaryOperatorKind == null) {
            diagnostics.add("Binary operator '" + syntax.getOperatorToken().getText() + "' is not defined for types "
                    + left.type() + " and " + right.type());
            return left;
        }

        return new BoundBinaryExpression(left, boundBinaryOperatorKind, right);
    }

    private BoundUnaryOperatorKind bindUnaryOperatorKind(SyntaxKind kind, Type operandType) {
        if (operandType == Integer.class) {
            switch (kind) {
                case PLUS_TOKEN:
                    return BoundUnaryOperatorKind.IDENTITY;
                case MINUS_TOKEN:
                    return BoundUnaryOperatorKind.NEGATION;
                    default:
            }
        }

        if(operandType == Boolean.class) {
            switch (kind) {
                case BANG_TOKEN:
                    return BoundUnaryOperatorKind.LOGICAL_NEGATION;
                    default:
            }
        }

        return null;
    }

    private BoundBinaryOperatorKind bindBinaryOperatorKind(SyntaxKind kind, Type leftType, Type rightType) {
        if (leftType == Integer.class && rightType == Integer.class) {
            switch (kind) {
                case PLUS_TOKEN:
                    return BoundBinaryOperatorKind.ADDITION;
                case MINUS_TOKEN:
                    return BoundBinaryOperatorKind.SUBTRACTION;
                case STAR_TOKEN:
                    return BoundBinaryOperatorKind.MULTIPLICATION;
                case SLASH_TOKEN:
                    return BoundBinaryOperatorKind.DIVISION;
                default:
            }
        }

        if(leftType == Boolean.class && rightType == Boolean.class) { 
            switch(kind) {
                case AMPERSEND_AMPERSEND_TOKEN: 
                    return BoundBinaryOperatorKind.LOGICAL_AND;
                case PIPE_PIPE_TOKEN: 
                    return BoundBinaryOperatorKind.LOGICAL_OR;
                default: 
            }
        }

        return null;
    }
}

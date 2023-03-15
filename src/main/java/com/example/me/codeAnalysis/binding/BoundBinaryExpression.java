package com.example.me.codeanalysis.binding;

import java.lang.reflect.Type;

public class BoundBinaryExpression extends BoundExpression {
    private BoundExpression left;
    private BoundBinaryOperatorKind operatorKind;
    private BoundExpression right;

    public BoundBinaryExpression(BoundExpression left, BoundBinaryOperatorKind operatorKind, BoundExpression right) {
        this.left = left;
        this.operatorKind = operatorKind;
        this.right = right;
    }

    @Override
    public Type type() {
        return left.type();
    }

    @Override
    public BoundNodeKind kind() {
        return BoundNodeKind.UNARY_EXPRESSION;
    }

    public BoundExpression getRight() {
        return right;
    }

    public BoundExpression getLeft() {
        return left;
    }

    public BoundBinaryOperatorKind getOperatorKind() {
        return operatorKind;
    }
}

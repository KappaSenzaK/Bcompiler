package com.example.me.codeanalysis.binding;

import java.lang.reflect.Type;

public final class BoundUnaryExpression extends BoundExpression {
    private BoundUnaryOperatorKind operatorKind;
    private BoundExpression operand;

    public BoundUnaryExpression(BoundUnaryOperatorKind operatorKind, BoundExpression operand) {
        this.operatorKind = operatorKind;
        this.operand = operand;
    }

    @Override
    public Type type() {
        return operand.type();
    }

    @Override
    public BoundNodeKind kind() {
        return BoundNodeKind.UNARY_EXPRESSION;
    }

    public BoundExpression getOperand() {
        return operand;
    }

    public BoundUnaryOperatorKind getOperatorKind() {
        return operatorKind;
    }
}

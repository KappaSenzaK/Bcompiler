package com.example.me.codeanalysis.binding;

import java.lang.reflect.Type;

public class BoundLiteralExpression extends BoundExpression{
    private Object value;

    public BoundLiteralExpression(Object value){ 
        this.value = value;
    }

    @Override
    public Type type() {
        return value.getClass();
    }

    @Override
    public BoundNodeKind kind() {
        return BoundNodeKind.LITERAL_EXPRESSION;
    }

    public Object getValue() {
        return value;
    }
    
}

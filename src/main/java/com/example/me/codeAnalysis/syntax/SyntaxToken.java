package com.example.me.codeanalysis.syntax;

import java.util.List;

public final class SyntaxToken extends SyntaxNode {
    private SyntaxKind kind;
    private int position;
    private String text;
    private Object value;

    public SyntaxToken(SyntaxKind kind, int position, String text, Object value) {
        this.kind = kind;
        this.position = position;
        this.text = text;
        this.value = value;
    }

    public SyntaxKind getKind() {
        return kind;
    }

    public void setKind(SyntaxKind kind) {
        this.kind = kind;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public SyntaxKind kind() {
        return this.kind;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return List.of();
    }
}

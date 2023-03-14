package com.example.me.codeanalysis;

public abstract class SyntaxNode {
    public abstract SyntaxKind kind();

    public abstract Iterable<SyntaxNode> getChildren();
}

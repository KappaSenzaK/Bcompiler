package com.example.me;

public abstract class SyntaxNode {
    public abstract SyntaxKind kind();

    public abstract Iterable<SyntaxNode> getChildren();
}

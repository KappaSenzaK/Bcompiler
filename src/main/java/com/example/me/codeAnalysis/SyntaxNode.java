package com.example.me.codeAnalysis;

public abstract class SyntaxNode {
    public abstract SyntaxKind kind();

    public abstract Iterable<SyntaxNode> getChildren();
}

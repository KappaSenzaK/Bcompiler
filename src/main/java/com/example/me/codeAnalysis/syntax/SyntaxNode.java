package com.example.me.codeanalysis.syntax;

public abstract class SyntaxNode {
    public abstract SyntaxKind kind();

    public abstract Iterable<SyntaxNode> getChildren();
}

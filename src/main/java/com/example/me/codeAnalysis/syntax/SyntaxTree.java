package com.example.me.codeanalysis.syntax;

import java.util.List;

public final class SyntaxTree {
    private List<String> diagnostics;
    private ExpressionSyntax root;
    private SyntaxToken endOfFileToken;

    public SyntaxTree(List<String> diagnostics, ExpressionSyntax root, SyntaxToken endOfFileToken){
        this.diagnostics = diagnostics;
        this.root = root;
        this.endOfFileToken = endOfFileToken;
    }

    public static SyntaxTree parse(String text){
        Parser parser = new Parser(text);
        return parser.parse();
    }

    public List<String> diagnostics() {
        return diagnostics;
    }

    public ExpressionSyntax root() {
        return root;
    }

    public SyntaxToken getEndOfFileToken() {
        return endOfFileToken;
    }

    @Override
    public String toString() {
        return "SyntaxTree [diagnostics=" + diagnostics + ", expressionSyntax=" + root + ", endOfFileToken="
                + endOfFileToken + "]";
    }
}

package com.example.me.codeAnalysis;

public final class SyntaxFacts {
    public static int getBinaryOperatorPrecedence(SyntaxKind kind) {
        switch (kind) {
            case SLASH_TOKEN:
            case STAR_TOKEN:
                return 2;

            case MINUS_TOKEN:
            case PLUS_TOKEN:
                return 1;

            default:
                return 0;
        }
    }
}

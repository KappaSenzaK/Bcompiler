package com.example.me.codeanalysis;

public final class SyntaxFacts {
    public static int getUnaryOperatorPrecedence(SyntaxKind kind) {
        switch (kind) {
            case MINUS_TOKEN:
            case PLUS_TOKEN:
                return 3;
    
            default:
                return 0;
        }
    }

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

    public static SyntaxKind getKeywordKind(String text) {
        switch(text) {
            case "true": 
                return SyntaxKind.TRUE_KEYWORD;
            case "false":
                return SyntaxKind.FALSE_KEYWORD;
            default:   
                return SyntaxKind.IDENTIFIER_TOKEN;
        }
    }

}

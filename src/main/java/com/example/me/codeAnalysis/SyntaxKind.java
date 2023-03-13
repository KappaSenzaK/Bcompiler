package com.example.me.codeAnalysis;

public enum SyntaxKind {
    //token
    END_OF_FILE_TOKEN,
    BAD_TOKEN,
    WHITE_SPACE_TOKEN,

    NUMBER_TOKEN,
    STRING_TOKEN,
    PLUS_TOKEN,
    MINUS_TOKEN,
    STAR_TOKEN,
    SLASH_TOKEN,
    OPEN_PARENTHESES_TOKEN,
    CLOSE_PARENTHESES_TOKEN,

    //expressions
    NUMBER_EXPRESSION,
    BINARY_EXPRESSION,
    PARENTHESESED_EXPRESSION
}

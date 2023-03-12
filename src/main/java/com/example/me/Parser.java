package com.example.me;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private SyntaxToken[] tokens;
    private int position;
    private List<String> diagnostics = new ArrayList<>();

    public Parser(String text) { 
        Lexer lexer = new Lexer(text);
        List<SyntaxToken> listTokens = new ArrayList<>();

        while(true){
            SyntaxToken token = lexer.lex();

            if (token.getKind() != SyntaxKind.BAD_TOKEN && token.getKind() != SyntaxKind.WHITE_SPACE_TOKEN) {
                listTokens.add(token);
            }

            if(token.getKind() == SyntaxKind.END_OF_FILE_TOKEN) {
                break;
            }
        }

        this.tokens = new SyntaxToken[listTokens.size()];
        this.tokens = listTokens.toArray(this.tokens);
        this.diagnostics.addAll(lexer.diagnostics());
    }

    public List<String> diagnostics(){
        return this.diagnostics;
    }

    private SyntaxToken peek(int offset) {
        int index = position + offset;
        if(index >= tokens.length) {
            return tokens[tokens.length - 1];
        }

        return tokens[index];
    }

    private SyntaxToken current() {
        return peek(0);
    }

    private SyntaxToken nextToken() {
        SyntaxToken current = current();
        this.position++;
        return current;
    }

    private SyntaxToken match(SyntaxKind kind) {
        if (current().getKind() == kind) {
            return nextToken();
        }

        this.diagnostics.add("ERROR: Unexpected token " + "<" + current().kind() + "> " + "expected: " + kind);
        return new SyntaxToken(kind, current().getPosition(), null, null);
    }

    public SyntaxTree parse() {
        ExpressionSyntax expression = parsePrimary();
        SyntaxToken endOfFileToken = match(SyntaxKind.END_OF_FILE_TOKEN);
        return new SyntaxTree(diagnostics, expression, endOfFileToken);
    }

    private ExpressionSyntax parsePrimary(){
        ExpressionSyntax left = parsePrimaryExpression();

        while (current().getKind() == SyntaxKind.PLUS_TOKEN || current().getKind() == SyntaxKind.MINUS_TOKEN) {
            SyntaxToken operatorToken = nextToken();
            ExpressionSyntax right = parsePrimaryExpression();

            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }

        return left;
    } 

    private ExpressionSyntax parsePrimaryExpression() {
        SyntaxToken numberToken = match(SyntaxKind.NUMBER_TOKEN);
        return new NumberExpressionSyntax(numberToken);
    }
}

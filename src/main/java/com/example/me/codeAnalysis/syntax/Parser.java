package com.example.me.codeanalysis.syntax;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private SyntaxToken[] tokens;
    private int position;
    private List<String> diagnostics = new ArrayList<>();

    protected Parser(String text) {
        Lexer lexer = new Lexer(text);
        List<SyntaxToken> listTokens = new ArrayList<>();

        while (true) {
            SyntaxToken token = lexer.lex();

            if (token.getKind() != SyntaxKind.BAD_TOKEN && token.getKind() != SyntaxKind.WHITE_SPACE_TOKEN) {
                listTokens.add(token);
            }

            if (token.getKind() == SyntaxKind.END_OF_FILE_TOKEN) {
                break;
            }
        }

        this.tokens = new SyntaxToken[listTokens.size()];
        this.tokens = listTokens.toArray(this.tokens);
        this.diagnostics.addAll(lexer.diagnostics());
    }

    protected List<String> diagnostics() {
        return this.diagnostics;
    }

    private SyntaxToken peek(int offset) {
        int index = position + offset;
        if (index >= tokens.length) {
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

    private SyntaxToken matchToken(SyntaxKind kind) {
        if (current().getKind() == kind) {
            return nextToken();
        }

        this.diagnostics.add("ERROR: Unexpected token " + "<" + current().kind() + "> " + "expected: " + kind);
        return new SyntaxToken(kind, current().getPosition(), null, null);
    }

    protected SyntaxTree parse() {
        ExpressionSyntax expression = parseExpression();
        SyntaxToken endOfFileToken = matchToken(SyntaxKind.END_OF_FILE_TOKEN);
        return new SyntaxTree(diagnostics, expression, endOfFileToken);
    }

    private ExpressionSyntax parseExpression(int parentPrecedence) {
        ExpressionSyntax left;
        int unaryOperatorPrecedence = SyntaxFacts.getUnaryOperatorPrecedence(current().kind());
        if (unaryOperatorPrecedence != 0 && unaryOperatorPrecedence >= parentPrecedence) {
            SyntaxToken operatorToken = nextToken();
            ExpressionSyntax operand = parseExpression(unaryOperatorPrecedence);
            left = new UnaryExpressionSyntax(operatorToken, operand);
        } else {
            left = parsePrimaryExpression();
        }

        while (true) {
            int precedence = SyntaxFacts.getBinaryOperatorPrecedence(current().kind());
            if (precedence == 0 || precedence <= parentPrecedence) {
                break;
            }

            SyntaxToken operatorToken = nextToken();
            ExpressionSyntax right = parseExpression(precedence);
            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }

        return left;
    }

    private ExpressionSyntax parseExpression() {
        return parseExpression(0);
    }

    private ExpressionSyntax parsePrimaryExpression() {
        switch (current().kind()) {
            case OPEN_PARENTHESES_TOKEN: {
                SyntaxNode left = nextToken();
                ExpressionSyntax expression = parseExpression();
                SyntaxNode right = matchToken(SyntaxKind.CLOSE_PARENTHESES_TOKEN);

                return new ParanthesesExpressionSyntax(left, expression, right);
            }
            case TRUE_KEYWORD:
            case FALSE_KEYWORD: {
                SyntaxToken keywordToken = nextToken();
                boolean value = keywordToken.kind() == SyntaxKind.TRUE_KEYWORD;
                return new LiteralExpressionSyntax(keywordToken, value);
            }
            default: {
                SyntaxToken numberToken = matchToken(SyntaxKind.NUMBER_TOKEN);
                return new LiteralExpressionSyntax(numberToken);
            }
        }
    }
}

package com.example.me.codeanalysis.syntax;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String text;
    private int position;
    private List<String> diagnostics = new ArrayList<>();

    protected Lexer(String text) {
        this.text = text;
    }

    protected List<String> diagnostics() {
        return this.diagnostics;
    }

    private char current() {
        return peek(0);
    }

    private char lookahead() {
        return peek(1);
    }

    private char peek(int offset) {
        int index = this.position + offset;
        if (index >= this.text.length()) {
            return '\0';
        }

        return this.text.charAt(index);
    }

    private void next() {
        this.position++;
    }

    protected SyntaxToken lex() {
        if (this.position >= this.text.length()) {
            return new SyntaxToken(SyntaxKind.END_OF_FILE_TOKEN, position, "\0", null);
        }

        if (Character.isWhitespace(current())) {
            int start = this.position;

            while (Character.isWhitespace(current())) {
                next();
            }

            String whitespaces = this.text.substring(start, this.position);

            return new SyntaxToken(SyntaxKind.WHITE_SPACE_TOKEN, start, whitespaces, null);
        }

        if (Character.isDigit(current())) {
            int start = this.position;

            while (Character.isDigit(current())) {
                next();
            }

            String subText = this.text.substring(start, this.position);
            Integer integer = null;
            try {
                integer = Integer.valueOf(subText);
            } catch (NumberFormatException e) {
                diagnostics.add("The number: " + "'" + subText + "'" + " cannot be parsed to Integer.");
            }

            return new SyntaxToken(SyntaxKind.NUMBER_TOKEN, start, subText, integer);
        }

        if (Character.isLetter(current())) {
            int start = this.position;

            while (Character.isLetter(current())) {
                next();
            }

            String subText = this.text.substring(start, this.position);
            SyntaxKind kind = SyntaxFacts.getKeywordKind(subText);

            return new SyntaxToken(kind, start, subText, null);
        }

        switch (current()) {
            case '+':
                return new SyntaxToken(SyntaxKind.PLUS_TOKEN, position++, "+", null);
            case '-':
                return new SyntaxToken(SyntaxKind.MINUS_TOKEN, position++, "-", null);
            case '*':
                return new SyntaxToken(SyntaxKind.STAR_TOKEN, position++, "*", null);
            case '/':
                return new SyntaxToken(SyntaxKind.SLASH_TOKEN, position++, "/", null);
            case '(':
                return new SyntaxToken(SyntaxKind.OPEN_PARENTHESES_TOKEN, position++, "(", null);
            case ')':
                return new SyntaxToken(SyntaxKind.CLOSE_PARENTHESES_TOKEN, position++, ")", null);
            case '!':
                return new SyntaxToken(SyntaxKind.BANG_TOKEN, position++, "!", null);
            case '&':
                if (lookahead() == '&') {
                    return new SyntaxToken(SyntaxKind.AMPERSEND_AMPERSEND_TOKEN, position += 2, "&&", null);
                }
                break;
            case '|':
                if(lookahead() == '|') {
                    return new SyntaxToken(SyntaxKind.PIPE_PIPE_TOKEN, position+=2, "||", null);
                }
                break;
        }

        diagnostics.add("ERROR: bad character input: " + "'" + current() + "'");
        return new SyntaxToken(SyntaxKind.BAD_TOKEN, position++, this.text.substring(this.position - 1, 1), null);
    }

    protected String getText() {
        return text;
    }
}

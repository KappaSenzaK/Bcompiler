package com.example.me.codeAnalysis;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String text;
    private int position;
    private List<String> diagnostics = new ArrayList<>();

    public Lexer(String text) {
        this.text = text;
    }

    public List<String> diagnostics(){
        return this.diagnostics;
    }

    private char current() {
        if (this.position >= text.length())
            return '\0';

        return this.text.charAt(position);
    }

    private void next() {
        this.position++;
    }

    public SyntaxToken lex() {
        if (this.position >= this.text.length()) {
            return new SyntaxToken(SyntaxKind.END_OF_FILE_TOKEN, position, "\0", null);
        }

        if (Character.isWhitespace(current())) {
            int start = this.position;

            while (Character.isWhitespace(current())) {
                next();
            }

            String whitespaces = this.text.substring(start, this.position);

            return new SyntaxToken(SyntaxKind.WHITE_SPACE_TOKEN, start, whitespaces, whitespaces);
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

            return new SyntaxToken(SyntaxKind.STRING_TOKEN, start, subText, subText);
        }

        if(current() == '+'){
            return new SyntaxToken(SyntaxKind.PLUS_TOKEN, position++, "+", null);
        } else if (current() == '-') {
            return new SyntaxToken(SyntaxKind.MINUS_TOKEN, position++, "-", null);
        } else if (current() == '*') {
            return new SyntaxToken(SyntaxKind.STAR_TOKEN, position++, "*", null);
        } else if (current() == '/') {
            return new SyntaxToken(SyntaxKind.SLASH_TOKEN, position++, "/", null);
        } else if (current() == '(') {
            return new SyntaxToken(SyntaxKind.OPEN_PARENTHESES_TOKEN, position++, "(", null);
        } else if (current() == ')') {
            return new SyntaxToken(SyntaxKind.CLOSE_PARENTHESES_TOKEN, position++, ")", null);
        }

        diagnostics.add("ERROR: bad character input: " + "'" + current() + "'");
        return new SyntaxToken(SyntaxKind.BAD_TOKEN, position++, this.text.substring(this.position - 1, 1), null);
    }

    public String getText() {
        return text;
    }
}

package com.example.me;

import java.util.Scanner;

import com.example.me.utils.ColorBackgrounds;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String line = scanner.nextLine();
        scanner.close();

        Parser parser = new Parser(line);
        SyntaxTree syntaxTree = parser.parse();
        System.out.print(ColorBackgrounds.ANSI_BLACK_BACKGROUND);

        prettyPrint(syntaxTree.root());

        System.out.print(ColorBackgrounds.ANSI_BLACK_BACKGROUND);

        if(!parser.diagnostics().isEmpty()) {
            System.out.print(ColorBackgrounds.ANSI_RED_BACKGROUND);

            for(String diagnostic : parser.diagnostics()) {
                System.out.println(diagnostic);
            }

            System.out.print(ColorBackgrounds.ANSI_BLACK_BACKGROUND);
        }
    }

    public static void prettyPrint(SyntaxNode node, String indent, boolean isLast) {
        String marker = isLast ? "└──" : "├──";

        System.out.print(indent);
        System.out.print(marker);
        System.out.print(node.kind());

        if (node instanceof SyntaxToken t && t.getValue() != null) {
            System.out.print(" ");
            System.out.print(t.getValue());
        }

        System.out.println();

        indent += isLast ? "    " : "│   ";

        SyntaxNode lastChild = lastOrDefault(node.getChildren());

        for (SyntaxNode child : node.getChildren()) {
            prettyPrint(child, indent, child.equals(lastChild));
        }
    }
    
    public static void prettyPrint(SyntaxNode node) {
        prettyPrint(node, "", true);
    }

    private static SyntaxNode lastOrDefault(Iterable<SyntaxNode> children) {
        SyntaxNode lastChild = null;
        for(SyntaxNode child : children) {
            lastChild = child;
        }
        return lastChild;
    }

}

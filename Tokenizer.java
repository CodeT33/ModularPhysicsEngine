import java.util.*;

enum TokenType {
    USE, WITH, OUT, THEN, OUTPUT, VIA, IDENTIFIER, NUMBER, EQUALS, COMMA, SEMICOLON, EOF, CONVERT, INTO, VAR, ADD, SUB, MUL, DIV, MOD,
    SQUAREBRCLEFT, SQUAREBRCRIGHT, NORMALBRCLEFT, NORMALBRCRIGHT,
    SET, GLOBALCONDS
}

class Token {
    TokenType type;
    String text;

    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return type + "(" + text + ")";
    }
}

public class Tokenizer {

    private String input;
    private int position = 0;
    private int length;

    public Tokenizer(String input) {
        this.input = input;
        this.length = input.length();
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (position < length) {
            char c = input.charAt(position);

            //Skip whitespaces
            if (Character.isWhitespace(c)) {
                position++;
                continue;
            }

            //Symbols
            if (c == '=') {
                tokens.add(new Token(TokenType.EQUALS, "="));
                position++;
                continue;
            }

            if (c == ',') {
                tokens.add(new Token(TokenType.COMMA, ","));
                position++;
                continue;
            }

            if (c == ';') {
                tokens.add(new Token(TokenType.SEMICOLON, ";"));
                position++;
                continue;
            }

            if (c == '+') {
                tokens.add(new Token(TokenType.ADD, "+"));
                position++;
                continue;
            }

            if (c == '-') {
                tokens.add(new Token(TokenType.SUB, "-"));
                position++;
                continue;
            }

            if (c == '*') {
                tokens.add(new Token(TokenType.MUL, "*"));
                position++;
                continue;
            }

            if (c == '/') {
                tokens.add(new Token(TokenType.DIV, "/"));
                position++;
                continue;
            }

            if (c == '%') {
                tokens.add(new Token(TokenType.MOD, "%"));
                position++;
                continue;
            }

            if (c == '(') {
                tokens.add(new Token(TokenType.NORMALBRCLEFT, "("));
                position++;
                continue;
            }

            if (c == ')') {
                tokens.add(new Token(TokenType.NORMALBRCRIGHT, ")"));
                position++;
                continue;
            }

            if (c == '[') {
                tokens.add(new Token(TokenType.SQUAREBRCLEFT, "["));
                position++;
                continue;
            }

            if (c == ']') {
                tokens.add(new Token(TokenType.SQUAREBRCRIGHT, "]"));
                position++;
                continue;
            }

            //Numbers
            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                while (position < length && (Character.isDigit(input.charAt(position)) || input.charAt(position) == '.')) {
                    number.append(input.charAt(position));
                    position++;
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString()));
                continue;
            }

            //Words
            if (Character.isLetter(c) || c == '_') {
                StringBuilder word = new StringBuilder();
                while (position < length && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
                    word.append(input.charAt(position));
                    position++;
                }
                String text = word.toString();
                TokenType type = switch (text.toUpperCase()) {
                    case "USE" -> TokenType.USE;
                    case "WITH" -> TokenType.WITH;
                    case "OUT" -> TokenType.OUT;
                    case "THEN" -> TokenType.THEN;
                    case "OUTPUT" -> TokenType.OUTPUT;
                    case "VIA" -> TokenType.VIA;
                    case "CONVERT" -> TokenType.CONVERT;
                    case "INTO" -> TokenType.INTO;
                    case "VAR" -> TokenType.VAR;
                    case "SET" -> TokenType.SET;
                    case "GLOBALCONDS" -> TokenType.GLOBALCONDS;
                    //Add new word here
                    default -> TokenType.IDENTIFIER;
                };
                tokens.add(new Token(type, text));
                continue;
            }
            throw new RuntimeException("Unknown character: " + c);

        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

}

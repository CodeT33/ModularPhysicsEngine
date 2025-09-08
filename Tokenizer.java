import java.util.*;

enum TokenType {
    USE, WITH, OUT, THEN, OUTPUT, VIA, IDENTIFIER, NUMBER, EQUALS, COMMA, SEMICOLON, EOF
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
            if (Character.isLetter(c)) {
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

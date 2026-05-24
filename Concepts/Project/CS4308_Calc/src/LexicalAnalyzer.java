public class LexicalAnalyzer {
    String input;
    int index;
    int row;
    int col;
    int startRow;
    int startCol;

    public LexicalAnalyzer(String input) {
        this.input = input;
        this.index = 0;
        this.row = 1;
        this.col = 1;
    }

    private char peek() {
        int inputLength = input.length();
        if (index < inputLength) {
            return input.charAt(index);
        } else {
            return '\0';
        }
    }

    private char advance() {
        char currentChar = peek();
        if (currentChar == '\0') {
            return '\0';
        }
        index++;
        col++;
        return currentChar;

    }

    private void skipWhiteSpace() {
        while (Character.isWhitespace(peek())) {
            advance();
        }
    }

    public Token getToken() {
        skipWhiteSpace();
        char c = peek();
        startRow = row;
        startCol = col;
        if (c == '\0') {
            return new Token(TokenType.END_OF_INPUT, "", startRow, startCol);
        } else if (c == '+') {
            advance();
            return new Token(TokenType.ADDITION, "+", startRow, startCol);
        } else if (c == '-') {
            advance();
            return new Token(TokenType.SUBTRACTION, "-", startRow, startCol);
        } else if (c == '*') {
            advance();
            return new Token(TokenType.MULTIPLICATION, "*", startRow, startCol);
        } else if (c == '/') {
            advance();
            return new Token(TokenType.DIVISION, "/", startRow, startCol);
        } else if (c == '(') {
            advance();
            return new Token(TokenType.LEFT_PARENTHESIS, "(", startRow, startCol);
        } else if (c == ')') {
            advance();
            return new Token(TokenType.RIGHT_PARENTHESIS, ")", startRow, startCol);
        } else if (c == '=') {
            advance();
            return new Token(TokenType.ASSIGNMENT, "=", startRow, startCol);
        } else if (c == ',') {
            advance();
            return new Token(TokenType.COMMA, ",", startRow, startCol);
        } else if (Character.isDigit(c)) {
            String lexeme = "";
            while (Character.isDigit(peek())) {
                lexeme += advance();
            }
            return new Token(TokenType.INTEGER_LITERAL, lexeme, startRow, startCol);
        } else if (Character.isLetter(c)) {
            String lexeme = "";
            while (Character.isLetter(peek()) || Character.isDigit(peek())) {
                lexeme += advance();
            }
            if (lexeme.equals("print")) {
                return new Token(TokenType.PRINT, lexeme, startRow, startCol);
            } else if (lexeme.equals("read")) {
                return new Token(TokenType.READ, lexeme, startRow, startCol);
            } else if (lexeme.equals("if")) {
                return new Token(TokenType.IF, lexeme, startRow, startCol);
            } else if (lexeme.equals("then")) {
                return new Token(TokenType.THEN, lexeme, startRow, startCol);
            } else if (lexeme.equals("else")) {
                return new Token(TokenType.ELSE, lexeme, startRow, startCol);
            } else if (lexeme.equals("end")) {
                return new Token(TokenType.END, lexeme, startRow, startCol);
            } else if (lexeme.equals("do")) {
                return new Token(TokenType.DO, lexeme, startRow, startCol);
            } else if (lexeme.equals("while")) {
                return new Token(TokenType.WHILE, lexeme, startRow, startCol);
            } else {
                return new Token(TokenType.IDENTIFIER, lexeme, startRow, startCol);

            }
        }else if (c == '.'){
            advance();
            String lexeme = "";
            while (Character.isLetter(peek())) {
                lexeme += advance();
            }
            advance();
            if (lexeme.equals("lt")) {
                return new Token(TokenType.LT, lexeme, startRow, startCol);
            }else if (lexeme.equals("le")) {
                return new Token(TokenType.LE, lexeme, startRow, startCol);
            }else if (lexeme.equals("gt")) {
                return new Token(TokenType.GT, lexeme, startRow, startCol);
            }else if (lexeme.equals("ge")) {
                return new Token(TokenType.GE, lexeme, startRow, startCol);
            }else if (lexeme.equals("eq")) {
                return new Token(TokenType.EQ, lexeme, startRow, startCol);
            }else if (lexeme.equals("ne")) {
                return new Token(TokenType.NE, lexeme, startRow, startCol);
            }else{
                throw new RuntimeException("Unrecognized character: " + c);
            }

        } else {
            throw new RuntimeException("Unrecognized character: " + c);
        }
    }
    public Token peekToken(){
        int savedIndex = index;
        int savedCol = col;
        int savedRow = row;
        Token next = getToken();
        index = savedIndex;
        col = savedCol;
        row = savedRow;
        return next;
    }
}

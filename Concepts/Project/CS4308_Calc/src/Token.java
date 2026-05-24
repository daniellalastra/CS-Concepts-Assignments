public class Token {
    TokenType type;
    String lexeme;
    int row;
    int column;

    public Token(TokenType type, String lexeme, int row, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.row = row;
        this.column = column;
    }
    public TokenType getType() {
        return type;
    }
    public String getLexeme() {
        return lexeme;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public String toString() {
        return type + " " + lexeme + " " + row + " " + column;
    }

}

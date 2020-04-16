package company.tic_tac_toe;

public class TicTacToe {
    private static String[][] canvas = new String[3][3];
    private static String defaultFiller = " ";
    private static String newline = "\n\r";

    public TicTacToe() {
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                canvas[i][j] = defaultFiller;
            }
        }
    }

    public boolean fillValue(int position, String value) {
        int row = position / 3;
        int column = position % 3;
        if (column > 2 || row > 2){
            return false;
        }
        canvas[row][column] = value;
        return true;
    }

    public boolean isGameOver() {
        for (int j = 0; j < canvas.length; j++) {
            if (
                    canvas[j][0].equals(canvas[j][1])
                    && canvas[j][1].equals(canvas[j][2])
                    && !canvas[j][1].equals(defaultFiller)
            )
                return true;
        }

        for (int j = 0; j < canvas.length; j++) {
            if (
                    canvas[0][j].equals(canvas[1][j])
                    && canvas[1][j].equals(canvas[2][j])
                    && !canvas[1][j].equals(defaultFiller)
            )
                return true;
        }

        if (
                canvas[0][0] == canvas[1][1]
                && canvas[1][1] == canvas[2][2]
                        && !canvas[1][1].equals(defaultFiller)
        )
            return true;

        if (canvas[0][2] == canvas[1][1]
                && canvas[1][1] == canvas[2][0]
                && !canvas[1][1].equals(defaultFiller))
            return true;

        return false;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        int count = 0;
        builder.append(newline);
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas.length; j++) {
                var value = canvas[i][j].equals(defaultFiller) ? count : canvas[i][j];
                builder.append(value + "|");
                count++;
            }
            builder.append(newline + "-----" + newline);
        }
        return builder.toString();
    }
}

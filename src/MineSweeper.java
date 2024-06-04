import java.lang.Math;

public class MineSweeper {
    private Cell[][] grid;
    private int totalBombs;
    private int size;

    public MineSweeper(int size) {
        this.size = size;
        grid = new Cell[size][size];
        totalBombs = size*size/10; //O campo minado vai possuir 10% de células preenchidas com bombas

        //Adicioando as bombas normais
        for (int i = 0; i < totalBombs; i++) {
            int x = (int) (size*Math.random());
            int y = (int) (size*Math.random());

            if (grid[x][y] != null)
                i--;
            else
                grid[x][y] = new Bomb();
        }

        //Adicionando duas bombas especiais
        for (int i = 0; i < 2; i++) {
            int x = (int) (size*Math.random());
            int y = (int) (size*Math.random());

            if (grid[x][y] != null)
                i--;
            else
                grid[x][y] = new SpecialBomb();
        }

        //Definindo os numeros dos campos sem bombas
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int cont = 0;
                if (grid[x][y] == null) {
                    //Olhando quantas bombas tem ao redor
                    for (int i = x - 1; i <= x + 1; i++) {
                        for (int j = y - 1; j <= y + 1; j++) {
                            if (i >= 0 && j >= 0 && i < size && j < size)
                                if (grid[i][j] != null && grid[i][j].isBomb())
                                    cont++;
                        }
                    }

                    grid[x][y] = new Field(cont);
                }
            }
        }

    }

    public String toString() {
        String ret = "";

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                //if (grid[x][y].isOpen())
                    if (grid[x][y].isBomb())
                        ret += "[" + grid[x][y].getValue() + "]";
                    else
                        ret += "[ " + grid[x][y].getValue() + "]";
                //else
                //    ret += "[  ]";
            }
            ret += "\n";
        }

        return ret;

    }

    /*
    public boolean openCell(int x, int y) {
        if (grid[x][y].isBomb())
            return true;

        return false;
    }
    */
}
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

// Matrix Class
public class Matrix {

    // Variables
    private double[][] mat = new double[100][100];
    private int rowSize;
    private int colSize;

    /* Constructor */
    public Matrix() {

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++) {
                this.mat[i][j] = 0;
            }
        }

        this.rowSize = 0;
        this.colSize = 0;

    }

    public Matrix(int newRowSize, int newColSize) {

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++) {
                this.mat[i][j] = 0;
            }
        }

        this.rowSize = newRowSize;
        this.colSize = newColSize;

    }

    public Matrix(Matrix M) {

        this.rowSize = M.rowSize;
        this.colSize = M.colSize;

        for(int i = 0; i < this.rowSize; i++){
            for(int j = 0; j < this.colSize; j++) {
                this.mat[i][j] = M.mat[i][j];
            }
        }

    }

    /* Setter */
    public void setRowSize(int n) {
        this.rowSize = n;
    }

    public void setColSize(int n) {
        this.colSize = n;
    }

    /* Getter */
    public int getRowSize() {
        return this.rowSize;
    }

    public int getColSize() {
        return this.colSize;
    }

    private double power(double x, int power) {

        double result = 1.0;
        for(int i = 0; i < power; i ++) {
            result *= x;
        }
        return result;
    }

    /*
     *  MARK OPERATIONS
     */

    // Find a row with the max value in a particular column from a particular row
    private int getMaxAbsColIndex(int currentRow, int currentCol){

        // Initiate Maximum
        int maxRowIndex = currentRow;

        //Searching for max
        for (int i = currentRow; i < this.rowSize; i ++) {
            if (Math.abs(this.mat[i][currentCol]) > Math.abs(this.mat[maxRowIndex][currentCol])) {
                maxRowIndex = i;
            }
        }

        return maxRowIndex;
    }

    // Swap two rows
    private void swapRow(int row1Index, int row2Index){

        // Temporary row
        double[] tempRow = new double[this.colSize];

        // Swapping
        for (int j = 0; j < this.colSize; j++) {
            tempRow[j] = this.mat[row1Index][j];
        }
        for (int j = 0; j < this.colSize; j++) {
            this.mat[row1Index][j] = this.mat[row2Index][j];
        }
        for (int j = 0; j < this.colSize; j++) {
            this.mat[row2Index][j] = tempRow[j];
        }

    }

    // Pivoting Point
    private void moveUpPivot(int currentRow, int currentCol) {

        // Find max
        int pivotRow = this.getMaxAbsColIndex(currentRow, currentCol);

        // Swapping
        this.swapRow(currentRow, pivotRow);
    }

    // Divide Row
    private void divideRow(int i, double divider) {

        for(int j = 0; j < this.colSize; j++) {
            this.mat[i][j] /= divider;
        }

    }

    // Row Substraction (R1 - R2)
    private void substractRow(int i1, int i2, double multiply) {

        for(int j = 0; j < this.colSize; j++) {
            this.mat[i1][j] -= (this.mat[i2][j] * multiply);
        }

    }

    // Returns true if a row is zero row
    private boolean isZeroRow(int currentRow) {

        for(int i = 0; i < this.colSize; i++) {
            if (this.mat[currentRow][i] != 0) {
                return false;
            }
        }

        return true;

    }

    // Returns true if the matrix has a zero row
    private boolean hasZeroRow() {

        for(int i = 0; i < this.rowSize; i++) {
            if (this.isZeroRow(i) == true) {
                return true;
            }
        }

        return false;

    }

    // Returns true if a row is wierd row
    private boolean isWeirdRow(int currentRow) {

        for(int i = 0; i < this.colSize - 1; i++) {
            if (this.mat[currentRow][i] != 0) {
                return false;
            }
        }

        if (this.mat[currentRow][this.colSize - 1] == 0) {
            return false;
        }

        return true;

    }

    // Returns true if the matrix has a wierd row
    private boolean hasWeirdRow() {

        for(int i = 0; i < this.rowSize; i++) {
            if (this.isWeirdRow(i) == true) {
                return true;
            }
        }

        return false;

    }

    // Returns true if there are no solutions for the equation System
    private boolean isNoSolution() {

        return (this.hasWeirdRow());

    }

    // Returns true if there isn't enough equations to solve all variables
    private boolean isInfiniteSolution() {

        return (this.rowSize < (this.colSize - 1));

    }

    // Returns the index of leading one in a row
    private int getLeadingOneIndex(int currentRow) {

        boolean isZero = true;
        for (int i = 0; i < this.colSize - 1; i ++) {
            if (this.mat[currentRow][i] == 1.0) {
                return i;
            }
        }

        return -1;

    }

    // Returns the index of a row with leading one at a given column
    private int getRowIndexWithLeadingOneAt(int leadingOneIndex) {

        for (int i = 0; i < this.rowSize; i ++) {
            if (this.getLeadingOneIndex(i) == leadingOneIndex) {
                return i;
            }
        }

        return -1;

    }

    private void deleteRow(int currentRow) {

        for (int i = currentRow; i < this.rowSize - 1; i ++) {
            for (int j = 0; j < this.colSize; j ++) {
                this.mat[i][j] = this.mat[i + 1][j];
            }
        }
        this.rowSize --;

    }

    private void deleteZeroRows() {

        for (int i = this.rowSize - 1; i >= 0; i --) {
            if (isZeroRow(i)) {
                deleteRow(i);
            }
        }

    }

    private void gaussJordanElimination() {

        int currentBaseRow = 0;
        int currentBaseCol = 0;
        int currentRow;

        for (; (currentBaseRow < this.rowSize) && (currentBaseCol < this.colSize - 1); currentBaseRow ++) {

            /*/ DEBUG
            this.write();
            System.out.println();
            /*/

            // Choose pivot and move it up, skip if pivot is 0
            this.moveUpPivot(currentBaseRow, currentBaseCol);

            while ((this.mat[currentBaseRow][currentBaseCol] == 0) && (currentBaseCol < this.colSize - 1)) {
                currentBaseCol ++;
                this.moveUpPivot(currentBaseRow, currentBaseCol);
            }

            if (currentBaseCol > this.colSize - 2) {
                break;
            }

            double pivotDivider = this.mat[currentBaseRow][currentBaseCol];
            this.divideRow(currentBaseRow, pivotDivider);

            // Gauss Elimination
            for (currentRow = currentBaseRow + 1; currentRow < this.rowSize; currentRow ++) {
                double multiplier = this.mat[currentRow][currentBaseCol];
                substractRow(currentRow, currentBaseRow, multiplier);
            }

            // Jordan Elimination
            for (currentRow = currentBaseRow - 1; currentRow >= 0; currentRow --) {
                double multiplier = this.mat[currentRow][currentBaseCol];
                substractRow(currentRow, currentBaseRow, multiplier);
            }

            currentBaseCol ++;

        }
    }

    /*
     *  MARK READ
     */

    // Read Matrix from shell
    public void read() {

        Scanner scan = new Scanner(System.in);

        //Read Row
        System.out.println("Masukan jumlah baris : ");
        this.rowSize = scan.nextInt();

        //Read Column
        System.out.println("Masukan jumlah kolom : ");
        this.colSize = scan.nextInt();

        //Read element
        System.out.println("Input matriks : ");
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
                this.mat[i][j] = scan.nextDouble();
            }
        }
    }

    public void readFromFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println("Masukkan nama file eksternal: ");
            System.out.print(">>");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            if (reader.nextLine().equals("BARIS")) {
              this.rowSize = reader.nextInt();
            }
            if (reader.nextLine().equals("KOLOM")) {
              this.colSize = reader.nextInt();

            }

            for (int row = 0; row < this.rowSize; row++) {
                   for (int col = 0; col < this.colSize; col++) {
                       this.mat[row][col] = reader.nextDouble() ;
                  }
            }

            reader.close();
            System.out.println("Berhasil membaca dari file '" + fileName + "'.");

        } catch (IOException i) {
            System.out.println("Tidak dapat membaca dari file eksternal.");
        }

    }

    public void readForHilbert() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Masukan dimensi matriks Hilbert : ");
        this.rowSize = scan.nextInt();
        this.colSize = this.rowSize + 1;

        for (int i = 0; i < this.rowSize; i ++) {
            for (int j = 0; j < this.colSize - 1; j ++) {
                this.mat[i][j] = 1.0 / (i + j + 1.0);
            }
        }

        for (int i = 0; i < this.rowSize; i ++) {
            this.mat[i][this.colSize - 1] = 1.0;
        }

    }

    public void readForHilbertFromFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println("Masukkan nama file eksternal: ");
            System.out.print(">>");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            if (reader.nextLine().equals("DIMENSI")) {
              this.rowSize = reader.nextInt();
              this.colSize = this.rowSize + 1;
            }

            for (int i = 0; i < this.rowSize; i ++) {
                for (int j = 0; j < this.colSize - 1; j ++) {
                    this.mat[i][j] = 1.0 / (i + j + 1.0);
                }
            }

            for (int i = 0; i < this.rowSize; i ++) {
                this.mat[i][this.colSize - 1] = 1.0;
            }

            reader.close();
            System.out.println("Berhasil membaca dari file '" + fileName + "'.");

        } catch (IOException i) {
            System.out.println("Tidak dapat membaca dari file eksternal.");
        }

    }

    // Read Matrix from shell for interpolation
    public void readForInterpolation() {

        Scanner scan = new Scanner(System.in);
        double x, y;

        // Read number of points
        while (this.rowSize < 1) {

            System.out.println("Masukkan jumlah titik yang diketahui : ");
            this.rowSize = scan.nextInt();

            if (this.rowSize < 1) {
                System.out.println("Jumlah titik harus lebih dari 0.");
            }

        }

        // Assign row size
        this.colSize = this.rowSize + 1;

        // Read points
        System.out.println("Input titik-titik : ");
        for (int i = 0; i < this.rowSize; i++) {

            System.out.print("X" + (i + 1) + " = ");
            x = scan.nextDouble();
            System.out.print("f(X" + (i + 1) + ") = ");
            y = scan.nextDouble();

            for (int j = 0; j < this.colSize - 1; j ++) {
                this.mat[i][this.colSize - 2 - j] = power(x, j);
            }

            this.mat[i][this.colSize - 1] = y;

        }
    }

    public void readForInterpolationFromFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println("Masukkan nama file eksternal: ");
            System.out.print(">>");
            String fileName = scan.nextLine();
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            double x, y;

            if (reader.nextLine().equals("JUMLAH")) {
              this.rowSize = reader.nextInt();
              this.colSize = this.rowSize + 1;
            }

            for (int i = 0; i < this.rowSize; i++) {

                if (reader.nextLine().equals("f(")) {
                    x = scan.nextDouble();
                    if (reader.nextLine().equals(") =")) {
                        y = scan.nextDouble();
                    } else {
                        System.out.println("Format file eksternal salah.");
                        break;
                    }
                } else {
                    System.out.println("Format file eksternal salah.");
                    break;
                }

                for (int j = 0; j < this.colSize - 1; j ++) {
                    this.mat[i][this.colSize - 2 - j] = power(x, j);
                }

                this.mat[i][this.colSize - 1] = y;

            }

            reader.close();
            System.out.println("Berhasil membaca dari file '" + fileName + "'.");

        } catch (IOException i) {
            System.out.println("Tidak dapat membaca dari file eksternal.");
        }

    }

    /*
     *  MARK WRITE
     */

    // Write Matrix to shell
    public void write() {

        if ((this.rowSize == 0) && (this.colSize == 0)) {

            System.out.println("\n||Matriks kosong||");

        }
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
                System.out.format("%.3f",this.mat[i][j]);
                System.out.print(" ");
                if (j == (this.colSize -2)) {
                    System.out.print("| ");
                }
            }
            System.out.print("\n");
        }
    }

    // Write Matrix to external file
    public void writeToFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println("Masukkan nama file eksternal: ");
            System.out.print(">>");
            String fileName = scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            if ((this.rowSize == 0) && (this.colSize == 0)) {
                writer.println("\n||Matriks kosong||");
            }
            for (int i = 0; i < this.rowSize; i++) {
                for (int j = 0; j < this.colSize; j++) {
                    writer.format("%.3f",this.mat[i][j]);
                    writer.print(" ");
                    if (j == (this.colSize -2)) {
                        writer.print("| ");
                    }
                }
                writer.print("\n");
            }

            writer.close();
            System.out.println("Berhasil menulis ke file '" + fileName + "'.");

        } catch (IOException e) {
              System.out.println("Tidak dapat menulis ke file eksternal.");
        }

    }

    // Menuliskan hasil operasi hampiran dari hasil interpolasi
    public void writeInterpolationAsFunctionApproximation() {

        Matrix reducedMat = new Matrix(this);
        reducedMat.gaussJordanElimination();

        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan nilai X:");
        double x = scan.nextDouble();

        System.out.print("Nilai hampiran dari f(X) = ");

        double result = 0.0;
        for (int i = 0; i < reducedMat.rowSize; i ++) {
            result += power(reducedMat.mat[i][reducedMat.colSize - 1], this.rowSize - 1 - i);
        }

        System.out.println(result);

    }

    // Menuliskan hasil operasi hampiran dari hasil interpolasi ke file eksternal
    public void writeInterpolationAsFunctionApproximationToFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println("Masukkan nama file eksternal: ");
            System.out.print(">>");
            String fileName = scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            Matrix reducedMat = new Matrix(this);
            reducedMat.gaussJordanElimination();

            System.out.println("Masukkan nilai X:");
            double x = scan.nextDouble();

            writer.print("Nilai hampiran dari f(X) = ");

            double result = 0.0;
            for (int i = 0; i < reducedMat.rowSize; i ++) {
                result += power(reducedMat.mat[i][reducedMat.colSize - 1], this.rowSize - 1 - i);
            }

            writer.println(result);

            writer.close();
            System.out.println("Berhasil menulis ke file '" + fileName + "'.");

        } catch (IOException e) {
              System.out.println("Tidak dapat menulis ke file eksternal.");
        }

    }

    public void writeGaussJordan() {

        Matrix reducedMat = new Matrix(this);
        reducedMat.gaussJordanElimination();
        reducedMat.write();

    }

    public void writeGaussJordanToFile() {

        Matrix reducedMat = new Matrix(this);
        reducedMat.gaussJordanElimination();
        reducedMat.writeToFile();

    }

    public void writeInterpolationSolution() {

        Matrix reducedMat = new Matrix(this);
        reducedMat.gaussJordanElimination();

        System.out.println("Hasil interpolasi fungsi:");
        System.out.print("f(X) = ");

        for (int i = 0; i < reducedMat.rowSize; i ++) {
            System.out.println(reducedMat.mat[i][reducedMat.colSize - 1] + "X^" + (this.rowSize - 1 - i) + " ");
        }

    }

    public void writeInterpolationSolutionToFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println("Masukkan nama file eksternal: ");
            System.out.print(">>");
            String fileName = scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            Matrix reducedMat = new Matrix(this);
            reducedMat.gaussJordanElimination();

            writer.println("Hasil interpolasi fungsi:");
            writer.print("f(X) = ");

            for (int i = 0; i < reducedMat.rowSize; i ++) {
                writer.println(reducedMat.mat[i][reducedMat.colSize - 1] + "X^" + (this.rowSize - 1 - i) + " ");
            }

            writer.close();
            System.out.println("Berhasil menulis ke file '" + fileName + "'.");

        } catch (IOException e) {
              System.out.println("Tidak dapat menulis ke file eksternal.");
        }

    }

    public void writeGaussJordanSolution() {

        Matrix reducedMat = new Matrix(this);
        reducedMat.gaussJordanElimination();
        reducedMat.deleteZeroRows();

        if (reducedMat.isNoSolution()) {

            System.out.println("Sistem persamaan tidak memiliki solusi.");

        } else if (reducedMat.isInfiniteSolution()) {

            System.out.println("Sistem persamaan memiliki banyak solusi. Bentuk paramtetrik nya:");

            String paramChars = "pqrstuvwxyzabcdefghijklmno";
            boolean paramMap[] = new boolean[reducedMat.colSize - 1];
            char params[] = new char[reducedMat.colSize - 1];

            // Initialize parameters array
            for (int i = 0; i < paramMap.length; i ++) {
                paramMap[i] = true;
                params[i] = '\0';
            }

            // Assign parametric variables
            for (int i = 0; i < reducedMat.rowSize; i ++) {
                int leadingOneIndex = reducedMat.getLeadingOneIndex(i);
                if (leadingOneIndex != -1) {
                    paramMap[leadingOneIndex] = false;
                }
            }

            // Assign parameters
            int paramCount = 0;
            for (int i = 0; i < paramMap.length; i ++) {
                if (paramMap[i] == true) {
                    params[i] = paramChars.charAt(paramCount);
                    paramCount ++;
                }
            }

            for (int i = 0; i < paramMap.length; i ++) {

                String result = "X" + (i + 1) + " = ";

                if (!paramMap[i]) {

                    int rowIndex = reducedMat.getRowIndexWithLeadingOneAt(i);
                    result += reducedMat.mat[rowIndex][reducedMat.colSize - 1] + " ";

                    for (int j = 0; j < reducedMat.colSize - 1; j ++) {

                        if ((reducedMat.mat[rowIndex][j] != 0.0) && (reducedMat.mat[rowIndex][j] != 1.0)) {
                            result += "+ " + (reducedMat.mat[rowIndex][j] * -1.0) + String.valueOf(params[j]) + " ";
                        }

                    }

                } else {
                    result += params[i];
                }

                System.out.println(result);

            }

        } else {

            System.out.println("Sistem persamaan memiliki solusi unik:");

            for (int i = 0; i < reducedMat.rowSize; i ++) {
                System.out.println("X" + (i + 1) + " = " + reducedMat.mat[i][reducedMat.colSize - 1]);
            }

        }

    }

    public void writeGaussJordanSolutionToFile() {

        try {

            Scanner scan = new Scanner(System.in);
            System.out.println("Masukkan nama file eksternal: ");
            System.out.print(">>");
            String fileName = scan.nextLine();
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            Matrix reducedMat = new Matrix(this);
            reducedMat.gaussJordanElimination();
            reducedMat.deleteZeroRows();

            if (reducedMat.isNoSolution()) {

                writer.println("Sistem persamaan tidak memiliki solusi.");

            } else if (reducedMat.isInfiniteSolution()) {

                writer.println("Sistem persamaan memiliki banyak solusi. Bentuk paramtetrik nya:");

                String paramChars = "pqrstuvwxyzabcdefghijklmno";
                boolean paramMap[] = new boolean[reducedMat.colSize - 1];
                char params[] = new char[reducedMat.colSize - 1];

                // Initialize parameters array
                for (int i = 0; i < paramMap.length; i ++) {
                    paramMap[i] = true;
                    params[i] = '\0';
                }

                // Assign parametric variables
                for (int i = 0; i < reducedMat.rowSize; i ++) {
                    int leadingOneIndex = reducedMat.getLeadingOneIndex(i);
                    if (leadingOneIndex != -1) {
                        paramMap[leadingOneIndex] = false;
                    }
                }

                // Assign parameters
                int paramCount = 0;
                for (int i = 0; i < paramMap.length; i ++) {
                    if (paramMap[i] == true) {
                        params[i] = paramChars.charAt(paramCount);
                        paramCount ++;
                    }
                }

                for (int i = 0; i < paramMap.length; i ++) {

                    String result = "X" + (i + 1) + " = ";

                    if (!paramMap[i]) {

                        int rowIndex = reducedMat.getRowIndexWithLeadingOneAt(i);
                        result += reducedMat.mat[rowIndex][reducedMat.colSize - 1] + " ";

                        for (int j = 0; j < reducedMat.colSize - 1; j ++) {

                            if ((reducedMat.mat[rowIndex][j] != 0.0) && (reducedMat.mat[rowIndex][j] != 1.0)) {
                                result += "+ " + (reducedMat.mat[rowIndex][j] * -1.0) + String.valueOf(params[j]) + " ";
                            }

                        }

                    } else {
                        result += params[i];
                    }

                    writer.println(result);

                }

            } else {

                writer.println("Sistem persamaan memiliki solusi unik:");

                for (int i = 0; i < reducedMat.rowSize; i ++) {
                    writer.println("X" + (i + 1) + " = " + reducedMat.mat[i][reducedMat.colSize - 1]);
                }

            }

            writer.close();
            System.out.println("Berhasil menulis ke file '" + fileName + "'.");

        } catch (IOException e) {
              System.out.println("Tidak dapat menulis ke file eksternal.");
        }

    }

}

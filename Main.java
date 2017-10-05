import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    //Variable intialitation
    Scanner scan = new Scanner(System.in);
    boolean EndMenu = false;
    boolean EndCase;

    while (!EndMenu) {
        System.out.println("===================================================================================================");
        System.out.println("  ____     _     _   _  ____   ____              _   ___   ____   ____      _     _   _ ");
        System.out.println(" / ___|   / \\   | | | |/ ___| / ___|            | | / _ \\ |  _ \\ |  _ \\    / \\   | \\ | |");
        System.out.println("| |  _   / _ \\  | | | |\\___ \\ \\___ \\  _____  _  | || | | || |_) || | | |  / _ \\  |  \\| |");
        System.out.println("| |_| | / ___ \\ | |_| | ___) | ___) ||_____|| |_| || |_| ||  _ < | |_| | / ___ \\ | |\\  |");
        System.out.println(" \\____|/_/   \\_\\ \\___/ |____/ |____/         \\___/  \\___/ |_| \\_\\|____/ /_/   \\_\\|_| \\_|");
        System.out.println("===================================================================================================");
        System.out.print("\n");

        System.out.println("||============================SELAMAT DATANG DI GAUSS-JORDAN======================================||");
        System.out.println(">> Pilih menu  :");
        System.out.println("1. Matriks biasa");
        System.out.println("2. Matriks Hilbert");
        System.out.println("3. Interpolasi");
        System.out.println("4. Exit");
        System.out.print(">> ");


        String OptionMenu = scan.nextLine();
        switch (OptionMenu) {
            case "1" :

                Matrix M1 = new Matrix();
                EndCase = false;
                while (!EndCase) {

                    System.out.print("\n");
                    System.out.println("||====================================AUGMENTED MATRIX============================================||");
                    System.out.println(">> Pilih menu  :");
                    System.out.println("1. Input matriks dari keyboard");
                    System.out.println("2. Input matriks dari file eksternal");
                    System.out.println("3. Tampilkan solusi matriks");
                    System.out.println("4. Back");
                    System.out.print(">> ");

                    String OptionCase = scan.nextLine();
                    switch (OptionCase) {
                        case "1" :
                            M1.read();
                            break;

                        case "2" :
                            M1.readFromFile();
                            break;

                        case "3" :
                            System.out.print("\n");
                            M1.writeGaussJordanSolution();
                            System.out.println("Apakah anda ingin menginput ke file eksternal?(Y,T)");
                            System.out.print(">> ");
                            String Input = scan.nextLine();
                            if (Input == "Y") {
                              M1.writeGaussJordanSolutionToFile();
                            }
                            break;

                        case "4" :
                            EndCase = true;
                            break;

                        default :
                            System.out.println("\n||Input salah, masukan input ulang||\n");
                            break;
                    }
                }
                break;

            case "2" :

                Matrix M2 = new Matrix();
                EndCase = false;
                while (!EndCase) {

                    System.out.print("\n");
                    System.out.println("||======================================HILBERT MATRIX============================================||");
                    System.out.println(">> Pilih menu  :");
                    System.out.println("1. Input matriks hilbert dari keyboard");
                    System.out.println("2. Input matriks hilbert dari file eksternal");
                    System.out.println("3. Tampilkan solusi matriks hilbert");
                    System.out.println("4. Back");
                    System.out.print(">> ");

                    String OptionCase = scan.nextLine();
                    switch (OptionCase) {
                        case "1" :
                            M2.readForHilbert();
                            break;

                        case "2" :
                            M2.readForHilbertFromFile();
                            break;

                        case "3" :
                            System.out.print("\n");
                            M2.writeGaussJordanSolution();
                            System.out.println("Apakah anda ingin menginput ke file eksternal?(Y,T)");
                            System.out.print(">> ");
                            String Input = scan.nextLine();
                            if (Input == "Y") {
                              M2.writeGaussJordanSolutionToFile();
                            }
                            break;

                        case "4" :
                            EndCase = true;
                            break;

                        default :
                            System.out.println("\n||Input salah, masukan input ulang||\n");
                            break;
                    }

                }
                break;

            case "3" :

                Matrix M3 = new Matrix();
                EndCase = false;
                while (!EndCase) {

                    System.out.print("\n");
                    System.out.println("||====================================INTERPOLATION============================================||");
                    System.out.println(">> Pilih menu  :");
                    System.out.println("1. Input titik dari keyboard");
                    System.out.println("2. Input titik dari file eksternal");
                    System.out.println("3. Aproksimasi nilai fungsi");
                    System.out.println("4. Tampilkan solusi interpolasi");
                    System.out.println("5. Back");
                    System.out.print(">> ");

                    String OptionCase = scan.nextLine();
                    switch (OptionCase) {
                        case "1" :
                            M3.readForInterpolation();
                            break;

                        case "2" :
                            M3.readForInterpolationFromFile();
                            break;

                        case "3" :
                            System.out.print("\n");
                            if (M3.getRowSize() == 0) {
                              System.out.println("\n||Anda belum input||\n");
                            }
                            else {
                              M3.writeInterpolationAsFunctionApproximation();
                              System.out.println("Apakah anda ingin menginput ke file eksternal?(Y,T)");
                              System.out.print(">> ");
                              String Input = scan.nextLine();
                              if (Input == "Y") {
                                M3.writeInterpolationAsFunctionApproximationToFile();
                              }
                            }
                            break;

                        case "4" :
                            if (M3.getRowSize() == 0) {
                              System.out.println("\n||Anda belum input||\n");
                            }
                            else {
                              M3.writeInterpolationSolution();
                              System.out.println("Apakah anda ingin menginput ke file eksternal?(Y,T)");
                              String Input = scan.nextLine();
                              if (Input == "Y") {
                                M3.writeInterpolationSolutionToFile();
                              }
                            }
                            break;

                        case "5" :
                            EndCase = true;
                            break;

                        default :
                            System.out.println("\n||Input salah, masukan input ulang||\n");
                            break;
                    }
                }
                break;

            case "4" :
                EndMenu = true;
                break;

            default :
                System.out.println("\n||Input salah, masukan input ulang||\n");
                break;
        }
    }
  }
}

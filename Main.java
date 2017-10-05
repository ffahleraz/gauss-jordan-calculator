import java.util.Scanner;

public class Main {

    private final static void clearConsole() {

        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
            System.out.println("llll");
        }

    }

  public static void main(String[] args) {

    //Variable intialitation
    Scanner scan = new Scanner(System.in);
    boolean EndMenu = false;
    boolean EndCase;

    while (!EndMenu) {

        clearConsole();

        System.out.println("===================================================================================================");
        System.out.println("  ____     _     _   _  ____   ____              _   ___   ____   ____      _     _   _ ");
        System.out.println(" / ___|   / \\   | | | |/ ___| / ___|            | | / _ \\ |  _ \\ |  _ \\    / \\   | \\ | |");
        System.out.println("| |  _   / _ \\  | | | |\\___ \\ \\___ \\  _____  _  | || | | || |_) || | | |  / _ \\  |  \\| |");
        System.out.println("| |_| | / ___ \\ | |_| | ___) | ___) ||_____|| |_| || |_| ||  _ < | |_| | / ___ \\ | |\\  |");
        System.out.println(" \\____|/_/   \\_\\ \\___/ |____/ |____/         \\___/  \\___/ |_| \\_\\|____/ /_/   \\_\\|_| \\_|");
        System.out.println("===================================================================================================");
        System.out.print("\n");

        System.out.println("||============================ SELAMAT DATANG DI GAUSS-JORDAN ======================================||");
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
                    System.out.println("||==================================== AUGMENTED MATRIX ============================================||");
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
                    System.out.println("||====================================== HILBERT MATRIX ============================================||");
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
                    System.out.println("||==================================== INTERPOLATION ============================================||");
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
                            if (M3.getRowSize() == 0) {
                                System.out.println();
                                System.out.println("Titik yang diketahui belum diinput.");
                            } else {
                                System.out.println();
                                System.out.print("Apakah anda ingin menginput ke file eksternal? (Y, T): ");
                                String Input = scan.nextLine();
                                if ("Yy".contains(Input)) {
                                    M3.writeInterpolationAsFunctionApproximationToFile();
                                } else {
                                    M3.writeInterpolationAsFunctionApproximation();
                                }
                            }
                            break;

                        case "4" :
                            if (M3.getRowSize() == 0) {
                                System.out.println();
                                System.out.println("Titik yang diketahui belum diinput.");
                            } else {
                                System.out.println();
                                System.out.print("Apakah anda ingin menginput ke file eksternal? (Y, T): ");
                                String Input = scan.nextLine();
                                if ("Yy".contains(Input)) {
                                    M3.writeInterpolationSolutionToFile();
                                } else {
                                    M3.writeInterpolationSolution();
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

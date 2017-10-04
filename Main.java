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
        System.out.println("1. Matriks augmented dengan input keyboard");
        System.out.println("2. Matriks augmented dengan input file eksternal");
        System.out.println("3. Interpolasi");
        System.out.println("4. Exit");
        System.out.print(">>");

        String OptionMenu = scan.nextLine();
        switch (OptionMenu) {
            case "1" :

                Matrix M1 = new Matrix();
                EndCase = false;
                while (!EndCase) {

                    System.out.print("\n");
                    System.out.println("||====================================AUGMENTED MATRIX============================================||");
                    System.out.println(">> Pilih menu  :");
                    System.out.println("1. Input matriks");
                    System.out.println("2. Tampilkan matriks eselon tereduksi");
                    System.out.println("3. Tampilkan solusi matriks");
                    System.out.println("4. Back");
                    System.out.print(">>");

                    String OptionCase = scan.nextLine();
                    switch (OptionCase) {
                        case "1" :
                            M1.read();
                            break;

                        case "2" :
                            M1.writeGaussJordan();
                            break;

                        case "3" :
                            M1.writeGaussJordanSolution();
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

  /*          case 2 :

                Matrix M2 = new Matrix();
                EndCase = false;
                while (!EndCase) {

                    System.out.println(">> Pilih menu  :");
                    System.out.println("1. Input file eksternal");
                    System.out.println("2. Tampilkan matriks eselon tereduksi");
                    System.out.println("3. Tampilkan solusi matriks ke layar");
                    System.out.println("4. Tampilkan solusi matriks ke file eksternal");
                    System.out.println("5. Back");

                    int OptionCase = scan.nextInt();
                    switch (OptionCase) {
                        case 1 :
                            M2.read();
                            break;

                        case 2 :
                            M.Matrix(M);
                            CopyM.gaussJordanElimination();
                            break;

                        case 3 :
                            CopyM.Matrix(M);
                            CopyM.gaussElimination();
                            break;

                        case 4 :
                            CopyM.Matrix(M);
                            CopyM.solution();
                            break;

                        case 5 :
                            EndCase = true;
                            break;

                        default :
                            System.out.println("\n||Input salah, Masukan input ulang||\n");
                            break;
                    }
                }
                break;
*/

            case "3" :
              Matrix M3 = new Matrix();
              EndCase = false;
              while (!EndCase) {

                  System.out.print("\n");
                  System.out.println("||====================================INTERPOLATION============================================||");
                  System.out.println(">> Pilih menu  :");
                  System.out.println("1. Input titik");
                  System.out.println("2. Tampilkan solusi interpolasi");
                  System.out.println("3. Back");
                  System.out.print(">>");

                  String OptionCase = scan.nextLine();
                  switch (OptionCase) {
                      case "1" :
                          M3.readForInterpolation();
                          break;

                      case "2" :
                          M3.writeInterpolationSolution();
                          break;

                      case "3" :
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

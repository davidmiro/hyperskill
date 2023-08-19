//package cinema;
import java.util.Scanner;
public class Cinema {
    public static int purchasedTickets = 0;
    public static double percentage;
    public static int currentIncome = 0;
    public static int totalIncome;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        char[][] cinema = createCinema(row, seats);
        totalIncome = caclculateTotalIncome(row, seats);
        while(true) {
            printMenu();
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    showSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    showStatistics(cinema);
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }
    }

    public static char[][] createCinema(int row, int seats) {
        char[][]cinema = new char[row][seats];
        for(int i = 0; i < row; i++) {
            for(int j =0; j < seats; j ++) {
                cinema[i][j] = 'S';
            }
        }
        return cinema;
    }

    public static void showSeats(char[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= cinema[0].length; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++){
            System.out.print(i + 1);
            for (int j = 0; j < cinema[i].length; j++){
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }


    public static void buyTicket(char[][] cinema) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a row number:");
            int chosenRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int chosenSeat = scanner.nextInt();
            System.out.println();
            int totalSeats = cinema.length * cinema[0].length;
            int frontHalfRows = cinema.length / 2;
            int ticketPrice;
            int numRows = cinema.length;
            int numSeatsPerRow = cinema[0].length;
            if (chosenRow < 1 || chosenRow > numRows || chosenSeat < 1 || chosenSeat > numSeatsPerRow) {
                System.out.println("Wrong input!");
                continue;
            }

            if (totalSeats <= 60) {
                ticketPrice = 10;
            } else {
                if (chosenRow <= frontHalfRows) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = 8;
                }
            }

            System.out.println("Ticket price: $" + ticketPrice);
            System.out.println();
            if (cinema[chosenRow - 1][chosenSeat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                cinema[chosenRow - 1][chosenSeat - 1] = 'B';
                currentIncome += (totalSeats <= 60 || chosenRow <= frontHalfRows) ? 10 : 8;
                purchasedTickets++;
                showSeats(cinema);
                break;
            }
        }

    }
    public static int caclculateTotalIncome(int row, int seats) {
        if (row * seats <= 60) {
            return row * seats * 10;
        } else {
            int firstHalf = row / 2;
            int backHalf = row - firstHalf;
            return (firstHalf * seats * 10) + (backHalf * seats * 8);
        }
    }
    public static void showStatistics(char[][] cinema) {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        percentage = (double) purchasedTickets / (cinema.length * cinema[0].length) * 100;
        String formattedPercentage = String.format("%.2f", percentage);
        System.out.println("Percentage: " + formattedPercentage + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}
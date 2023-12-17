package code;

import java.util.ArrayDeque;
import java.util.Queue;

public class Day10 {
    private static final char START = 'S';
    private static final char[] PIPES = {'|', '-', 'L', 'J', '7', 'F'};
    private static final int[] DIRS = {-1,0,0,1,1,0,0,-1};

    private char[][] grid;
    private final int rows;
    private final int cols;

    public Day10(String[] input) {
        rows = input.length;
        cols = input[0].length();
        grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = input[i].toCharArray();
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private boolean isPipe(char c) {
        for (char pipe : PIPES) {
            if (c == pipe) {
                return true;
            }
        }
        return false;
    }

    private int bfs(int startRow, int startCol) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];

        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            System.out.println("-".repeat(20));
            System.out.println("Current pipe " + grid[currentRow][currentCol] + " at (" + currentRow + "," + currentCol + ")");
            for (int i = 0; i < DIRS.length; i += 2) {
                int newRow = currentRow + DIRS[i];
                int newCol = currentCol + DIRS[i + 1];

                if (isValid(newRow, newCol) && !visited[newRow][newCol] && isPipe(grid[newRow][newCol])) {
                    char currentPipe = grid[currentRow][currentCol];
                    char newPipe = grid[newRow][newCol];
                    System.out.println("Trying for pipe " + newPipe + " at (" + newRow + "," + newCol + ")" + " index" + i);

                    if (canConnect(currentPipe, newPipe, i)) {
                        System.out.println("Connected pipe from " + currentPipe +" at (" + currentRow + "," + currentCol+ ") to " + newPipe +"at(" + newRow + "," + newCol +")" );
                        queue.offer(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                        distance[newRow][newCol] = distance[currentRow][currentCol] + 1;
                        maxDistance = Math.max(maxDistance, distance[newRow][newCol]);
                    }
                }
            }
        }

        for (int i = 0; i < distance.length; i++) {

            for (int j = 0; j < distance[0].length; j++) {
                System.out.print(distance[i][j] + " ");

            }
            System.out.println();

        }

        return maxDistance;
    }

    private boolean canConnect(char currentPipe, char newPipe, int idx) {
        return switch (currentPipe) {
            case '|' ->
                    ((idx == 0 && (newPipe == '|' || newPipe == '7' || newPipe == 'F')) || (idx == 4 && (newPipe == '|' || newPipe == 'J' || newPipe == 'L')));
            case '-' ->
                    ((idx == 2 && (newPipe == '-' || newPipe == '7' || newPipe == 'J')) || (idx == 6 && (newPipe == '-' || newPipe == 'F' || newPipe == 'L')));
            case 'L' ->
                    ((idx == 0 && (newPipe == '|' || newPipe == '7' || newPipe == 'F')) || (idx == 2 && (newPipe == '-' || newPipe == '7' || newPipe == 'J')));
            case 'J' ->
                    ((idx == 0 && (newPipe == '|' || newPipe == '7' || newPipe == 'F')) || (idx == 6 && (newPipe == '-' || newPipe == 'F' || newPipe == 'L')));
            case '7' ->
                    ((idx == 4 && (newPipe == '|' || newPipe == 'J' || newPipe == 'L')) || (idx == 6 && (newPipe == '-' || newPipe == 'F' || newPipe == 'L')));
            case 'F' ->
                    ((idx == 2 && (newPipe == '-' || newPipe == '7' || newPipe == 'J')) || (idx == 4 && (newPipe == '|' || newPipe == 'J' || newPipe == 'L')));
            case 'S' ->
                    ((idx == 0 && (newPipe == '|' || newPipe == '7' || newPipe == 'F')) || (idx == 2 && (newPipe == '-' || newPipe == 'J' || newPipe == '7')) || (idx == 4 && (newPipe == '|' || newPipe == 'J' || newPipe == 'L')) || (idx == 6 && (newPipe == '-' || newPipe == 'F' || newPipe == 'L')));
            default -> false;
        };
    }


    public int findFarthestPoint() {
        int startRow = -1;
        int startCol = -1;

        // Find the starting position
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == START) {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }

        if (startRow == -1 || startCol == -1) {
            throw new IllegalStateException("Starting position not found.");
        }

        return bfs(startRow, startCol);
    }

    public static void main(String[] args) {
        String input = """
                F-|F|7LJ7F7.L-J7L-L-7L-J7.FF.F|--J.L-7--7.L-7FFFF7-F|---F--L-FF-FF|.L.F|77|--7-.FF7-7--L7.-7F-7FL-7FFF-777FF-L-L.L-F.|--7.77-LF7-|F--|---777
                |-7FF|||F7J.FJL|.L7LL7F|J7.J.|.|JF-7LLJJL-JJLFJLL7F||.FFJ||.LFJF7.LJ.F7|L-J7J|.L-JF7|F77F7J|7.LJ|LFJLLLLJ7F|.L-.-F-J7LJ-7FJL7FLL7L7JJ..JL-7|
                F7|-LJF-F--LJ-FF7L|7FJJLF7FF-J-|-F.L.L7LLL7|F7JLF---.F-LJ|F-JL7L7-7.FLLJFF-|L-L7JJJ.--LJ-JJL--7L||F7.|-J.FFFJ.|.LL7L-.FFLJJ-JL-FJJL-77-|JLLJ
                -J|.|F7LJ..|LFJ-J7|J|J-||JF|7||..|-7F7L-7.|-LF7.F7|7.-J|LF|---J7|||F7|L|-|.|F.LL-7-|||JLFLF7JLL-L-LJFF7-7-LJFFL..FJ.|.FFJLJ|JJ7|7L7J7--J.|F|
                FFFJ||J.LFJJLJ7.J7|-77-L|-FL-L-F.LL|LJ|FJ-FJF||-F7F7-|FF7FF7J7|--J.-.J-L-FFLJ.FL7L|.|L|FJ.L|.7J|LJ|.||L7L7J.-J||F77F77L|J7LLJ.FL7J|LL7FLJFJ7
                J7.|-L-|-LJ|7.|-L-J7L7-|F-7.7|F|.|JF7.LJL|L7F||FJLJL-7-FF7||-7LJFJJ.FJ7J.LJ7.7JLLF7L|7L|LF-LFF7-.|F-LJJF7LJ-|.7J77||F7J.|J-F---J|-|7||F|-7-J
                |L77J-7J77|-|--..|L7FJ||L7LJLJ||.FFJF-L7FF-F-J||F----J-FJLJ|F77L7|.7LLL.-J7L-J-F-JL--7FL.|-7||||.77JFJ-F|L7.-LJL|7LF|J|FJ7-LJ..FL--.F7.7-LF7
                ||.---FJF-|.7J.L-77.F.FL7.-J77L|7L|FL|J|-J.L-7LJ|F77|7.L--7LJL-7JF-F7JF7J.777L77JFF--FF7-LJFF7F--7..||LLL7L7J-7JJ|7|L|-7F7.|FFJ||-J-7JFF.FL7
                LF-L-F|7|.7.F-FJ-|---7||.FL--JJJL-77FF7|FJ7..L-7||L7JF----JF---JFF7||JF-7-L77-J--|LJF-7777FFJ||F-J--LJ.L-L7JJF-77LFL-7-|-J|-F7JF.F|-J|7L-7-|
                -|7.-LJJ-LL.F7|.|..|-LJF-7|7.F|.L-LLFF-7JFFF--7|||FJLL----7L7F7|FJLJL77.|J-F77J|-7JL|FJF7F7L7||L-77.|J7..|F7-F7||-F-FJ|J|FJF-J7|7JLJJL|LL-7|
                |L-7L|FL-||7|L|J7F|77..LFJF---L-|JF7FL7L7F7L-7LJLJL7F7-F--JFJ||FJF---J.F|FF||J.F--7FJ|F||||FJ||F-JF7F--7F-J|F77---JJF-F-F|J|.LFJJF.|LJ.L7L-J
                77JFF|-|--7--J.|.FJ.F7-FJ.J7|.|L7-|FF7L7|||F7L7F7F7||L7L--7L7||L7|F----7FF7||-FJF-JL7|FJLJ|L7||L-7||L-7|L-7LJ||F|77.|J|LF|F-7JL-7J-L7F|.L7FL
                L|.FF-7JJLLJF|-7FJJ7L7F-77F-7FL-F-F7||FJ|||||FJ|LJ|||FJF7FJFJ|L7|LJF---JF|LJL7L7L7F7||L-7FJJ|LJF7LJ|F-JL-7|F-JF-7-|-|-J7LL|FJ...77LFL|J7LFJ.
                7.|L77L77.F7-J--|J.|FFL7L-J-JFFJ|.|LJLJFJ||||L-JF7|LJL7||L7L7L7||F-JF7F-7|F-7L-JFJ|||L7F||F7L7FJ|F-JL-7F-J|||FJFJ-7J|77|-L|L7F7F7----LL--|.F
                --7-|JL7-.F7JF-7LL-.L|L--77|.|-F-7L---7L7|||L7F7||L-7FJ||L|FJFJ||||FJLJFJ||L|F--JFJLJFJFJ|||FJL7LJF7F7|L7FJL7L7L-7|-|-||F7|FJ|LJ|||.L|.L-J-L
                J7FFJJ-LJ7||FJ.F7||.FJJ7F|--7JLL7L7F-7L7|||L7LJLJL7||L7||FJL7L7LJL7|F--J-LJFJL7F7L--7L-JFJ||L-7L7FJLJ||FJL7FJFJF-J7F7-F7|LJ|FJF-J77L-|---|.J
                FF7L7|-J.F--FJ7LJFF-J7LJ-JFL|7J|L7||FJ|||||LL7F--7L7|FJ||L-7|-|F--J||F77F7FJF7LJL7F7|F-7L-JL7FJFJL--7||L7FJL7L7|F77||FJLJF7||FJF--7J.--F-F7.
                7.J.L--L--JFJLJJ7L|JF7.|.|J-|J|F-JLJL7FJ||L7FJ|F-JFJ||FJ|F-JL-JL7F7|LJL7||L-J|F-7|||LJ|L7F--JL7|LF7FJ||FJL-7L7|||L7|FL--7||LJL-JF-JFJJ||F7--
                --L--JF|7||L-F.L77..||FJ-LJ.77-L----7|L7|L7LJFJL-7|||LJFJ|F7F-7FJ||L--7|||F7-|L7LJ||F7F7|L7F7FJL-J|L7|||F7FJFJ|||FJ-F-F7LJL-7F--JFJ7-F--||L|
                |-L7F|-L-7|L-JF7-F7-J77FJFJ.F7-|F-77||FJL7L--JF-7|L7|F7L7LJLJFJL-JL7LFJLJLJ|FJFJF-J||||LJFJ|LJF---JFJ|||||L7|L|LJL7JFJ||F-7J||7LJF||-LL-FJ-|
                77-F.JL|L-|FLF|L|.F-|.FL7|JFJL-7|FJFJ||F-JF7F7L7|L7|||L-JF-77L--7F-JFJF7F--JL7|J|F-J||L-7L7L-7|F--7L7||LJL7|L7L--7||F7||L7|FJL7L7LF77LL-FJF7
                .|7LJ7FF7|LL-7J7L-7FL-J.|JFL--7||L7L7LJL77||||FJ|FJLJL7F7L7|F---JL7FJFJ|L77F7|L7|L7FJ|F7L7L--J||F-JFJ||F--J|FJFF7||FJ|||FJ|L7FJ.LFJ|7JFL.L7J
                F|L7|-F|-77LFL-F77|-J7|-|.FF--J||FJ-L-7FJFJ|||L7|L-7F-J||FJ|L--7F7||FJ-L7|FJLJFJ|FJL7LJL7L-7F-J|L7FJFJ||F7FJ|JFJ|||L7|||L7|FJL-77L7L-77L-7JJ
                L|JLJ--F-L-JL7JL-J.LFFFF7F7L--7LJL-7|FJL7L7LJ|7||F7||F7|||FJLF7LJ|LJL7F7LJL7F7L7|L--JF--JF-JL-7L7|L7|-||||L7L7|FJ||FJLJ|FJ|L7F-JF7|F-J77.|F7
                FJL7JJ.J-J.-J|7.|-|F-F-JLJ|7-LL--7FJFJF-JFL7FJFJLJLJLJLJ||L-7||F-JF-7LJL--7||L7|L7F-7L7F7L-7F-JFJ|FJ|FJLJ|FJFJ||FJ|L--7|L7|FJL--JLJL--7-7-|J
                FLJ.7F7.|.FJ|-|7F.F7LL--7FJF7F7F7|L7|FJF7F7|L7|F7F----7FJ|F-J||L-7L7L-----JLJ||||LJFJFJ||.FJL7FJFJL7LJF--J|FJFJ||FJF7FJ|FJ||F---7F-7F-JJ7.L7
                F7F|LF7-JF7.7JL-JL||F7J|||FJ||LJ|L7||L7||||L7|||||F--7||F|L-7||F7|FJF--7F--7F-JL7JFJFJFJL7|F7|L7|F-JF7L7JFJL7|FJ|L7|||FJL7LJ|.F7LJFLJ||L7-L|
                J7J|FLL7.LL7J.LJ7||LJ|F7||L7|L-7|F|LJFJ|LJ|FJ|||LJL-7||L7|F-J|LJ||L7|F-JL-7|L--7|FJFJFJF-JLJ||FJ|L7FJL-JFJF7LJL7|FJ||||7-|F-JFJL7F7F777LF7.7
                |JF-JLFJ.LJ|.F77FL|F7LJLJ|FJ|F7|L-JF7L-JF-JL7|LJF7F-JLJFJ|L77L-7|L7||L--7FJL---JLJFJ7L7|.F7-||L7|FJL7F7|L-JL7F-J|L-JLJL7FJL--JF7||LJL-7-||.L
                |J-LJ7LJF-77-LF-F|LJL---7|L7|||L---J|F-7L7F-J|F-J|L---7|FJFJF7FJ|FJLJF--JL7F-----7L-7FJL7|L7||-||L-7LJL7F7F7||F-JF-----J|F-7F7|LJ|F7F-JF-J77
                |.L|L|LF--J|L|J---F-7F7|||F||||-F7F-J|F|FJL-7|L7FJF---J|L7|||||FJL--7L7JF7LJF7F-7|F7||F7||FJ|L7||F7L7F-J|||||LJF7|F-----JL7|||L--J||L777JJFJ
                L..F7L-..|-|F||.|7L7LJ|FJ|FJLJL7|||F7L7LJF7FJL7|L7L---7L7||FJ|||F7F7L7L7|L7FJ|L7LJ|LJ||LJ||JL7LJ||L7|L--J||||F-JLJL----7F-JLJL-7F7|L7L7||FL.
                L---|-LJFL-FLF--7F||F7|L7LJF--7|||LJL7|F7||L-7||FJF7F7L7|||L7|||||||7L7||FJL7|FL7FJF7||F7||F7L7FJL7||F---J|||L7F7F7.F--JL---7F7LJLJ-L7|-7F-7
                LJ-LJ-|FJ--.L|F-JF7LJ|L7L--JF7|||L7F7||||||F-J||L7|LJL7||||FJ||||LJL7FJ||L7FJ|F7|L7|||||LJ|||FJ|F7|||L7F7FJLJFJ|LJL7L-7F----J|L7F77-L||F-7-J
                |..L..-|L77F-JL-7||F7L7L----JLJLJFJ||||||||L7FJ|FJL-7FJ||||L7||||F--JL7||FJ|FJ|||FJ||||L7FJ|||FJ|||||FJ|||F--JFJF--J.FJL----7|FJ||77|LJ7LJ||
                F|7-JJ--7F-JF7F7LJLJ|LL---------7|FJ|||||||FJ|FJ|F7FJ|FJ||L7|||||L7F7FJ||L7||FJ||L7|||L7|L7||||FJ||LJL7|LJL--7L7|FF7FJF--7F-J|L-JL7F7.|JJFL7
                ||F7|JF7FL7FJ|||F--7L-7F7F----7J||L7||||||LJFJ|-LJ|L7|L7||FJ||||L7||||J||FJ|||FJ|FJ||L7LJLLJ|||L7LJF--J|F----JFJL-J|L7|F7LJ|FJF-7FJ|L7|F-7J|
                -LLJL-|F-LLJ|LJLJF-JF7LJ|L---7L-JL-JLJ|||L-7L7L7F-JFJL7||||FJ|||FJ||LJFJ|L7|LJL7||FJL7L-7.F-J|L7L-7L7F-JL----7L7F--JFJLJL---JFJFJL-JFJ-|JF-7
                FJF|-LL--|F---7F7L7FJL7FJF7F7L-------7||L7FJFJFJL7FJ.FJ||||L7LJ||7LJF7|FJFJ|F--J|||F7L-7L7L-7|FJF-JF||F7F----JFJL---JF--7F---JLL-7F-JF7J.|FJ
                L|-JF|J---L--7LJL7LJ|FJ|FJ||||F7-F---JLJFJL7L7|F-JL7FJFJ|||FJF-JL-7L||||-L7||F--J|LJL-7|FJF-J|L7L7F7LJ||L-----JF-7F7FJF7|L--7F7F7LJF-J|FFJL7
                LLLJFJF7JJJ7FJF-7L7F7L7|L7LJL-JL7L----7JL-7L7|||F-7|L7|FJ|LJFL-7F7L-J|||F-J|||F--JF-7FJLJFJF7L7|FJ|L--JL7F7F---JFJ||L-J|L--7||LJ|F7|F-JFJF-J
                LFF7L7L|7JFFL7L7L7LJ|FJL7L-----7L-----JF7J|FJ||LJFJL7||L7L--7F-J|L7F7|LJL-7|LJL7F7|FJL-77L-JL7|LJFJF7F-7|||L-7F7L-JL7F7L-7FJLJF-J||||F-JFJJJ
                .-|JJFLF777F-L-J7L-7|L-7|F7F---JF------JL7|L7|L7FL-7|||7|F-7|L-7L7LJ|L7F--JL7F-J|||L7F7L--7F7LJ7FJFJ||FJLJL-7LJL---7||L7FJL---JF7|||LJF-J|FJ
                .F|JLF7|L7-|.F7-F7FJL--J|||L----JF-----7FJL-JL7L--7LJ|L7|L7||JFJFJ-FJFJL----JL7FJ|L7|||F-7||L---JFJFLJL7F7F7L------J|L7|L7F----JLJLJF7L-7JF|
                -FJJJ|||FJFF7||FJLJF7F-7|||F7F7F7L----7||F7F77L---JF-JFJ|FJ|L7L7L-7L-JF-7F7F--JL7L-J|||L7|||F7F-7L-7F-7LJLJL-------7|FJL7LJF-7F7F--7|L-7|FJ7
                F7JF7|LJ|-FJ|||L---JLJFJLJ||LJ|||F----JLJ|LJL----7.L-7L7|L7|FJJ|F7L-77L7LJLJF-7FJF--J||FJLJLJLJFJF-JL7L-----7F-7F--JLJF7L-7L7LJ|L-7LJ.LLJFJ7
                FLFJ|L-7L-JFJ|L------7L7F7|L-7|||L-------JF--7F--JF-7|FJL-J|L-7LJ|F-JF-JF--7|.LJ.L-7FJ|L7F-7|F7L7L7F-JF--7JFJL7|L-----JL-7L7L-7|F7|F7-F-77|.
                -7L7|F7L--7L7L-7F7F-7L-J||L--JLJL-7F7F7F7FJFFJL-7JL7LJL--7FJF7|F-J|F7L-7|F-JL--7F--J|LL-JL7L-JL-JFJL7FJF7L7L7FJL---------J7L-7|LJ|LJ|FJFJ.F7
                LLJ|LJL--7|FJFFJ|LJ-L---JL7F7F---7LJLJLJ||F7L7F-JF7L-7F7FJL7|||L-7LJ|-FJ|L7F7F-JL7F7L----7L7F-7F-JF7LJFJL-J|LJ-F7|F----7F--77LJF-JF7|L7|F7||
                |LFL-7F-7LJL-7|FJF--7F--7FLJLJLF7L-----7|LJ|FJL--J||FJ||L-7||||F-JF7L7|FJFJ||L-7|LJL7F---JFJL7||F-J|F7L---7F--7||FJF7F-J|F7L--7L-7|LJFJLJLJ|
                7|..FJ|FJF-7FJLJFL-7LJF7L------JL------JL7FJL7F--7|FJFJL7FJLJ||L7FJL7||L7L7||F7L-7F7|L--7FJF7|LJL-7|||F77FJ|F-J|LJFJLJF7||L---JF7|||FJF7F7FJ
                |LF-L-JL7|FJL------JF7|L-7F-7F----------7|||FJL7FJ|L7L-7LJF7J||FJ|F-J||FJFJ|LJ|F7||LJF-7|L-JLJJF7FJLJLJL-JJ||FFJF7L---JLJL-7-F7||LJFJFJLJLJJ
                JJLF--7FLJL-7F-7F7F7|LJF-J|7LJF-----7F--J|L7L--JL-JFJF7L--JL7||L7||F7|||FJFJF7||LJ|F7L7LJF7F-7FJLJF7F7F----JL-JFJL7F------7L-J|||F-JFJJ-LJ|.
                |F|L-7L--7F-J|FJ|||LJ|FJF7|F--JF7F-7LJ-F7L-JF--7F7|L7||F7F7FJLJJ|||||||||FJFJLJL-7LJL7L-7||L7|L---JLJLJF----7F-J7FJL-7-F77L---J||L7FJJJJJJJ.
                L7F|FL--7||F7|L-J||F--JFJLJL--7|LJFL---JL7F7|F-J|L-7||||||||F---J|||LJ||||FL-7F7FJF-7L7FJ|L-JL-7FF7F7F7L---7|L--7L--7L-JL7FF7JFJL-JL--7-|-77
                L|J|FLF-JLJ||L--7LJL---JF-----J|-F---7F-7LJ|||JFJF7|||||LJ||L-7F-J|L-7||||F--J||L7L7L7||FJF7F-7L-JLJLJL-7F7|L-7FJF7-|F7F7L-JL-JF------JL7L|7
                .|F-FFJF--7|L7F-JF---7F7L---7F-JFJF7-LJ||F7LJL-JFJLJLJLJF7LJF7||JLL7FJLJ|||F7FJL7L-JFJLJL-J|L7L7F7F7F7F7LJLJF7LJFJL7LJLJL7F7F-7L-7F7-F7||-J|
                LJ7FLL-JF-JL7|L7FJF-7LJL----J|F7L-JL--7FJ||F--7FJJF--7F7|L7FJLJ|7-LLJ-LJLJ|||L7.L7F7L-7F---JFJ|LJLJLJLJL--7FJL7FJF-JF7F7|||||-|F7LJL-JL7|.F|
                |L-F-LL-L7F7|L7|L-JL|F----7F-J|L----7FJL7|||F-JL--JF7LJLJFJL7F7|--|L7..|LFJ||FJF7||L--JL----JF--7F7F7FF---JL7FJL7L--JLJL7LJ||FJ||F-7F7FJ777J
                J.|L-7|.|||||JLJF---J|F---J|F7L----7LJF7LJLJL-7F-7FJ|F7F7|7.LJLJ|F7LFF-LLL-JLJFJLJ|F-7F7F7F77|F7LJLJL-JF--7FJL--JF7F-7F7L-7|||FJ|L7||LJF7.|F
                LLJ7LJ7-LLJLJFF-JF---JL7F--J|L----7L--JL7F7F7FJL7LJFLJ|||L7-LL|.7L|JLL7.|-|.LFJF-7|L7LJLJLJL7LJL--7F7F7|F-J|F----JLJJ||L--J||LJFJFJ|L--J|-|J
                FJL7.L|L|.LF--JF7|7F77FLJF-7L----7L---7FJ|||||F7L-7F-7LJ|FJ7.LL-JFJJ7-LJ--LF-L7|FJL7|F7F-7F7L7JF7LLJLJLJL--JL-7F--7F7|L7FF7LJF7L7|FJF-7FJJ.L
                LF.L77JF-.FL7F7|LJFJL7F-7L7L77F7.L---7LJFJLJ|LJL--J|FJLFJ|J7..FLLJ.F7.7JJ||J.LLJL--JLJ||FJ|L7L7||F7F7F----7F-7LJF7LJ|L-JFJL-7|L7|||FJ|LJJF-7
                FF-7L|F-JF-LLJLJF7L-7|L7|F|FJFJL7F---JF7|F--JF-----J|F7L7|-L-F77|L.FJFJFF-|.F7LF7F-7F7LJL7|7L7LJLJ|||L---7|L7L--JL--JF7F|F--J|FJLJ|L7--LLJ-7
                F--J--LJFJLF7F7FJ|F-JL7||FJL-JF7|L----J||L---JF7F7F-J|L7LJ7J..LL-JFF-FLLJJL7-|F||L7|||F--JL7-L---7||L----JL7L--------JL-JL-7L|L--7L-J|JLFJ||
                |-|-7JFFJ7.|LJLJFJL7F7LJLJF7F-J|L------J|F----J||||F7|FJLF7|-7-|L|L7--J|LJ|L-LL|L-JLJLJF7F-JF----J||F7F---7|F-7F--7F---7F-7L-JF--JF7F7.|FF--
                J7FF-7FJ||FJF--7L-7LJL---7|LJF-JF--7F---JL-7F7JLJLJ|LJL7FJ|F77.J--JLL|F7L-LJ..LL-7F7F7FJLJF7L-7F-7|LJLJF--JLJ-|L-7|L--7LJFJF-7|F7F||||.|JL|.
                |LL-|-7F--L-JF-JF7L------JL7LL--JF-J|F--7F7LJL7F7F-JF-7LJFJ7LJ-J.|.F.J-J-J|F77F--J||||L-7FJL7FJ|FJL--7-L---7F7L--JL7F7L-7|FJ||LJ|7|||L-7-.|L
                7.|.|JL|J7LF-JF7||F7F------JF-7F7L--JL-7||L---J|||F7|L|F7L7J777.7-L7.L-7|7J|LFL---J|||F-JL7FJL-J|F-7FJF----J||F7FF7|||F-J|L-7|F7L-JLJF7|LFFJ
                |J-F7--J.L.L7FJLJLJLJF--77F7L7|||F--7F-JLJFF7F7|LJ||L7LJL-JJ.77JJ.F|.LF-FJ.F7.F----J|LJ|F-JL----J|FJL7L--7F-J||L-J||||L7FL--JLJL-7F7FJLJ7F-7
                |F|.LJ||FJ.FJ|F-----7L-7L-JL-JLJ|L-7|L--7F7|LJ||F-JL-JF---7..7J.FJ-F7L-.|--L7-|F-7F7|F-7L------7FJ|F7L7F7||F-J|F--J||L7L-7F7F---7||LJ.|J7JL-
                FL|-LJL7JJFL-JL--7F7L7FJF-7F7F-7L--JL7F-J|||F7LJL--7F7L7F-J77.F-L|.J|FJ.L7..F7LJ.LJ||L7|F7F7F--JL7||L7LJLJ|L7FJL--7|L7|F7||LJF7FJLJ7-||FL7.|
                .LJ-J-LL77-|LF---J|L7LJFJ.|||L7L7F--7|L--JLJ||F7F-7LJL-JL-7-7F77-.|J7|FLLJ.FLJ7F-|LLJFJLJLJLJF-7FJ|L7L7F-7|FJ|F---JL-JLJLJ|F-JLJJFLL-L-FJ-J7
                F|J..L||LJ7LFL-7F7|-L-7L7FJ|L-JFLJFFJL------JLJ|L7L7F--7F-J-L-J-JFJ--7F.|F7.L.|JL|-F-JF---7F7|FLJ7L-J.LJ.LJL-JL-7F7F7F7F7FJL-7F7-F7|.|.LFJLJ
                7---7F-7JF||L|FLJLJF7FJFJL-J|F7F7F7L----------7L7L7||F7|L-7-FJFJ-JLFJ|J-F||7.LJ|LF7L-7|F--J||L7F--7F7F------7F7FJ|LJLJ|||L7F-J||7||F--77.F7|
                LJ||L|.L.JLJ|.F7F-7||L-JF7-F-JLJLJL-7F--------JLL-JLJ||L--JF-777FLFF7.|7FJL7-.LF-JL-7LJL-7FJ|FJL-7|||L-7F--7LJLJJ|F---J|L-JL-7|L7|LJF7||LJ-7
                -J-|J|..F|.LF-J|L7LJL---JL-JF7F7F7F7|L----7F---7F----J|F7J|||LF-7J.F7-7FJF7|.F|L---7|F7F7LJ-LJF--JLJL-7LJF-J-F7F7||F7-FJF7F7FJ|FJL7FJLJ-L|7F
                |7LL-J.L--7F|F7L-JF---7F----JLJ||LJ||F----J|F--J|F7F7FJ|L--77LL7||F||FFJFJ||-FF7F--JLJLJL7F-7FJF7F---7L--JF--JLJLJLJL-JFJ|||L-JL--JL--7J7F--
                |F.FL-L-F-FLLJ|F-7|F-7LJF7FF---J|F-JLJF7F7FJL---J|LJ|L-JF7FJ7FFJL7FJL-JFJ|||-||LJF7F7F7F7LJFJL-JLJF77L----JF-7F--7F7F--J-|||F7F-7F7F7FJ-L.FL
                L--L.7J-J|.|L|LJLLJL7L--JL-JF-7FJL----JLJLJF7F7F7L-7|F--JLJ|.FL-7LJF--7L-7LJF7L--JLJLJLJL-7L7F-7JFJL7F7F7F-JFJL-7|||L---7LJ||||7LJ||LJJ|L-F7
                FJJ.-.F.7LF|.F-----7L---7F-7|FJ|F----7F----JLJLJL--JLJ.F-77F77LLL--J.FJF7L-7||F7F--7F-----JFJL7|FJF7LJ||LJF7L---J||L-7F7L7LLJ|L-7FLJJLFFJ|L-
                |J7LL-7F7.-J7L--7F7|F---JL7||L-JL---7|L----7F7F7.F7LF--JFJFJ|JLF-7F-7L7|L--J|LJLJF7|L-7F--7L--JLJFJ|F7LJF-JL----7|L-7LJL-JF-7L7FJF7|7F-|F|J|
                |||-LLL|-LF-7JFFJ||LJF7F7FJ||F------JL----7LJLJL-JL-JF7FJJ|FJ7FL7LJFJFJL---7L---7|LJ.FJ|F-JF-7F-7L7LJL7FJF-----7|L7FJF-7F7|FJFJL-JL--7-.|J--
                LJ--7JL|7||-77FL-JL-7|||||7LJL-----------7L7F-7F7F---JLJF-JL-7F7L-7L-JF----JF---J|F7FJFJ|F7|FLJLL7|F-7LJFJF--77LJ|LJ||FJ|||L7|F-7F7F7|JFFJ|L
                LJ-FL.LL--JFJF7JFF--J|||LJF7-F---------7FJJ|L7LJLJ-F---7L--7FJ||F7L--7|F7.F7L---7|||L7|-LJ||F-7F7LJL7|F7L-JF7L7.F7JF-JL-JLJFJ||FJ|LJLJFFL-JJ
                .|J|L-77.JFL-||7FJF-7|||F-JL7L--------7LJF7L-JF7.F7|F--JF7FJL7||||.F7|||L-JL-7.FJLJ|-LJF7FJ|L7LJ|.F-JLJL---JL7L-JL-JF7F7F-7L7LJL7L7J-L-.LL7.
                LJ-F|JF|.F7J-|L-JFJ.LJLJL--7|F7F7F7F-7L--J|JF7||FJLJL7F7||L7FJ|||L7|LJ||F----JFJF-7|F--J|L-J7L7FJFJF---7F---7|F--7F-J||LJ7|FJJ-LL-JF7LL-7L77
                |.|.|.-JFFLJFL-7FJF-7F7F--7|LJLJLJLJFL-7F7L-J||LJF7F7|||||FJ|FJ||FJL-7LJ|.F--7L-JFJ|L-7FJF7|F-JL7L-JF--J|F7FJLJF7|L-7LJF-7||JL77.|-7J.L|FJL7
                .F7.-7FF777FLJ|||.L7|||L-7|L7F7F------7|||F-7LJF-JLJLJ||||L7|L7LJL7F-JF-JFJF7L7F7L7L7FJ|FJL7L--7L---JF7FJ|LJF7F||L--J-FJFJLJJ.J7-JJL.|-FJFFJ
                F77FL-7LJL-JL-FJ|F-JLJL7FJL7||LJLF----JLJ|L7|F7L----7FJ|||F||-L7F-JL-7L7FJFJL-J||FJFJL7||F-JF7FJF7F7FJLJ|L--J|FJL-----JFJF77F7JJ|.-7.|..F7L-
                F7-JLJ-77FLF--|FJL----7|L-7|||F--JF7F7F7FL-JLJ|F--7FJL7|||FJL7FJL7F77L7||FJ7F-7|||FJF7||||F-JLJFJLJ||F7|FF---JL7F---7F-JFJ|F-7|F7-|7.L7-F|-J
                ||J-FJFLFJL-.FLJJLF--7|L--J|LJL---JLJLJ|F7F--7|L-7LJF-JLJ|L-7|L-7|||F-J||L-7|FJ|LJL7|LJ|||L7F-7|F--J||L7FJF7F7-|L-7.LJF7L7LJFJF7L7.7-L|7|.|.
                |J.||LL7L7-|.|LF77L-7LJF--7L--7F-------J|LJF-JL7FJF7L---7L-7||F-J|||L-7||F-J|L7L--7|L7FJ|||LJ-LJ|F--JL7|L-JLJL-JF7L7F-JL-JF7L-J|-F7J-F-JL77.
                |LFL7J.|-F7|-JF|L--7L--JF7L--7LJF----7F-JF7L-7JLJFJ|F7.FJF-J||L-7||L7FJLJ|F7|FJF-7|L7||FJL7JF7F7LJF7JFJL----7F7FJL7LJF7F--JL---J7||.||J..JJF
                ..77|..|-|||F--L--7L7F--JL---JF7|F--7LJF-J|F7L--7L7|||FJFJ|FJL-7|LJFJL-7FJ||||FL7|L7|||L7FJFJ||L7FJ|FJF7F7F7LJLJF7L--JLJFF7F7F--7||F-7|F|.-7
                FFF7FF7|7.7L777F7-L7|L------7FJ||L-7|F7L-7LJ|F--J.||||L7|F7L-7FJ|F-JF7FJL7|||L7FJL7||||FJ|JL7LJFJL7LJFJLJLJ|F7F-JL7F7F7F-J||LJF-J|||FJ7-777F
                FF||--JL|-F7F7FJL--JL-7F7F--J|FJ|F-JLJL--JF7|L7F-7||||FJ|||F-JL7|L-7||L7FJ|||FJL7FJ||||L7L-7L7FJ|LL-7|F----J|||F--J|||LJF-J|F-JF7|||L--7|7FJ
                LLLJ7.F-LF|||LJF7F7F-7|||L---JL-JL----7F-7||L-JL7LJ|||L7|||L-7FJ|F-J||FJL7|||L7FJL7|||L-JF-JFJL-77F7LJL7F---JLJL---JLJF-JF7||F7|LJLJF--J7JJ|
                |LJ.F|JLLFJLJF7|LJLJ||||L7F7F-----7F--J|FJ|L7FF7L-7|||FJ||L7FJL-JL7-||L7FJ|||FJL7FJ||L--7L7FJF--JFJL7F7LJF7F-7F-------J7FJLJLJLJF--7L--7L|-F
                L7J.F|7||L-7FJ||F----J||FJ||L----7LJF7FJL7L7|FJL-7||||L7||FJL--7F-JFJL7||FJLJL7FJL7||F77L7LJFJJF7L7FJ|L-7|LJFJL---------JF-7F7F7L-7L---JF-7J
                JJFFF|7-FJFLJ|LJL----7LJL-J|F7F7-L-7||L7FJ|||L7F7LJ|||J|||L-7F-J|F7L-7|||L-7F-JL-7||||L--JF-JF7||FJL7|F7LJF7L---7F-7F7F7FJF|||||F7L--7JF7JLF
                L-|J||-J|.7JLLF------JF-7F7|||||F7.LJL7||F7|L7LJL-7||L7||L7FJL-7LJ|F7|LJL7FJL--7FJLJ|L--7FJF-J||LJF-J||L--JL--77LJFLJ|||L7FJ|LJLJL7F-J-F|-J|
                L7JFJ|FFJ-JJ7||F---7F7|FJ||LJLJLJL----J||||L7L7F-7||L7||L-JL77FJF-J|LJF--JL-7F-JL--7|FF-JL7L-7|L7FJF7LJF---7F-JF7F7F7LJ|FJL7L--7-FJ|-JL7LLL-
                FLF7L|J-L--F|7LJF7FJ|||L-JL7F7F7F---7F7||||FJFJL7||L7|LJF7F-JFJFJF7L-7L-7F-7||F7F7FJL7L--7L7FJ|FJ|FJ|F-JF-7|L--JLJLJL-7LJJ-L7F-JL|FJ7-7.--7|
                7-FF7LL|||FFF-7-||L7||L7F--J|||LJ-F-J|||||||FJF7||L7|L-7|LJF7L7||||F7L-7|L7|||||||L7FJF7FL7||FJ|FJ|FJL--JFJ|F--7F--7F7L-7FFFJ|F7F||L|7F-|-LJ
                .F--L--||7-FJFJFJ|FJ|L7|L7F-JLJF-7L7FJLJLJ||L7||||FJ|F-J|F7|L7||FJLJ|F7|L7|||||||L7|L7||F7||||FJL7|L7F7F7|FJL7L|L-7LJ|F-J7.L7||L7||.7-|JL--J
                -|JL||.FLF7L7|LL7|L-JLLJFJ|F-7F|FJFJL--7F-J|.|||||L7|L-7LJLJFJ|||FF-J|LJ7LJ|||||L7||F||||||||||F-J|FJ|||LJL-7L7|F7L-7LJF77FFJLJFJLJJL|...L7.
                .L7|LJF7J||F||F-J|F--7F7L-JL7L-JL-JF7F-J|F-JFJ|LJL7|L7FJF---JFJ|L7L-7L----7||LJ|FJ||FJ|||LJ||LJ|F7||FJ|L---7|FJ||L--JF-JL7FJF--J|FLJ-7.L7LF-
                J.-J7-|L-JL7||L-7|L-7LJ|F7..L--7F7FJ|L7FJ|F-JFJF7F||-|L7|F7F-J7L7|F7|F7F7FJ|L7FJL7||L7||L-7LJ|FJ||||L7L7F--J|L7|L----JF-7|L7L--7F-.L|.|F7L-7
                FJ.F-7L-7F7LJL--JL--JF7LJL7F7F7LJ|L7|FJ|FJL-7L-J|FJL7|FJ|||L---7|||LJ||||||L7|L7FJ||FJ||F-J7F-JFJ|||FJFJL--7L-J|F----7L7LJF|F--J7.L-7|-LJF7L
                FJF|-|7FJ||F-7F-7F--7|L-7FJ|LJL--JFJ|L7||F--JF7FJ|F7|LJ7LJL7F-7|LJ|F7||||L-7LJL|L7||L-J|L7F-JF7L7LJ|L7|F-7FJF7-|L---7L-JJLF|L-7J|F.FLJL--JFL
                -.F|7|FL7|LJFJL7||F-J|F-JL-JF-7F7FJ|L-JLJL-7FJ||7|||L---7F7||-LJF-J|||||L7FJ|F-JFJLJF--JFJL-7||FJLFJFJ||FJ|FJL7|F7F7L----7.L7FJ7|.FJ--77|7||
                J7.LFLJFLJ-|L7FJLJL-7|L-7F7FJFJ||L-7F---7F7|L7||FJ|L7F--J|LJL--7L--J||LJF||F7L-7L--7L-7FJ-F-J|||F-JFJFJ|L7LJF7||||||F----J-JLJJ7||J|F|7-FFJ.
                |.|-7-L7L|F-FJL7-F--JL7FJ|||FL7||F-JL--7LJLJFJ|||FJFJ|F7.|F-7F-JF---JL--7|||L--JF-7|F-J|F7|F7|||L-7L7L7|7L7FJLJ|||||L---7JLFJLF-7|L|LLJJJ|LL
                7-FFL-J|F.FL|F-JFJF--7||FJ||F-J||||F7F7L--7FJ|LJ|L7L7LJ|FJ|FJL-7L-7F7F7FJ||L---7L7||L-7LJ|LJLJLJF7|FJFJL7||L--7LJ||L--7FJLF7J7|J7|-7|.LJLLF.
                |.L-L7.|.FF-J|F-JFJJFJ|||FJ||F7|||FJLJL---JL--7FJFJFJF7|L7||F-7L-7||||LJLLJF7F-JFJ|L-7|F7L7F--7FJLJL7|F7L7|F--JF-JL--7||J7J|-F-7L-J|-7|7.FL-
                --|-JL|-F-L-7|L--JF7L-JLJL7|||||||L---7F7F7F--JL7|FL7|||FJ|||FJF7|LJ|L-7F7FJLJF7L7L-7||||FJL-7LJF-7FJ||L7||L-7L|F7F-7|LJJ|J|J.L|JJF|7F-|F7FJ
                ..F7-F.L||.L||F7F7|L------J|||||||F---J|LJ|L-7F-JL7LLJLJL7|LJL7|||F-JF7|||L-7FJL7L-7||LJ|L-7FJF-JFJL7|L7|||F7L7LJ||7LJJJ77F.F|FJJL-|L|-J7|LJ
                F|L|.L|-LJ-|LJ|LJLJF7F-7F-7|||||LJL---7|F-JF-JL-7FJF7F--7LJF7FJ||||F-JLJ|L--JL77|F-JLJF-JF7|L7|F-JF-J|FJ||||L7L-7|L7|LL---J-L7LJ-FLLFL-..|F7
                LL7JFJL7.L--JFL-7F7|||FJ|FJ|||||F-----J||F7L--7FJL-JLJF7|LFJLJFJLJ||7F-7L-7F-7L7LJLF77L-7||L7LJL-7L-7|L7|||L7L7FJ|FJ77|FJ-JJLJ7J.LJ|.F|J7.FJ
                .LJ.|LFF-L.L|F|F||||LJL7|L-JLJ|||F----7||||F--J|F7F7F-JLJFJF-7L7LFJL-JFJF-JL7L7L---JL7F7LJL7|F---JF7||FJ||L7L7|L7LJ|JLJ-7FJ|7|F7.FF|J7JL7F|7
                F.F7-F.||LFF7.|L||||7F7|L-7F77||||F---J||||L-77|||||L-7FFJFJFJFJFJF7F7|FJF7FJFJF7F7F7LJL--7LJL7F7FJ|||L7|L7L7|L7L--7L|7L|.|F-LJ--7JFJLF7L-JJ
                L|7|J.FL7-L7J-|LLJ|L7|LJF-J|L-J|LJL---7|LJ|F7L7LJ|||F-JFJFJFL7L7L7|LJ||L7||L7L7|||||L--7F-JF--J|||FJ||FJL7|FJL7|F-7L7|FJ|7||||JJ|L7JJLLJJJF7
                F-.|7FF-||.-JFL7|FJFJL-7L-7L7F7L7F----J|.FJ|L7|F7LJ|L-7|FJJF7L7L7|L-7|L7|||FJ7||LJ|L--7|L7.L--7|||L-J|L-7||L7FJLJJ|FJ-L77F|FF||||J|.7.L|7|-|
                -JL|-||||F|F---F-JFJF--JF7|7|||FJL-----JFJFJJ|LJL-7|F-JLJF7||||FJL-7||FJ|||L-7||F-JF7FJL7|F---J||L--7|F-JLJJ|L-7--|||L-|---F-LJ-|FL7.|.--F.|
                F-.|7|JFF7-JLLF|F7|FL7F7|||FJ|LJ|F------JFJF-JF7F7|||F--7||||FJL--7|||||LJ|F-JLJL7FJ|L7-LJ|F-7FJ|F-7||L7JF--JF-JFFLJJLLLFJ-LJ.LFLF-J.L7JFJF7
                JFF|-LF|-FJ7.F-LJLJF-J|LJ|||FJJF-JF7F--7FJFJF7|LJLJLJ|F7LJLJLJF--7|||LJF7.|L---7FJL7L7L--7||FJ|FJL7|||FJFJF-7L--7-J|F-JL|7F|.LFJ.|L|-FJ||-7|
                |-JL--7.LJ|FF|.LLJFL7FJFFLJ|||-L-7|LJF-JL7|FJ|L----7|LJL7F7F-7L-7||||F7|L-JF7F-J|F-JFJF7FJ||L7|L-7|LJ||-|FJJ|F7FJJ|FJJ.F7F-J.|.LL7-J.7.FJ7LJ
                |L---J|7L-7LLF-J..FLLJLF-J.||7-L|LJ.SJF7FJLJFJF7F--JF---J|||FJF-J|||||LJF7FJLJF7LJFL|FJ||FJ|FJ|JFJL-7LJJ||7.||LJL7FF7.7FL7|J.F7-||FJ-LLJ.||.
                F-J-|FFL77FF7|L-7-|J-J-J|.FLJJ7LLLF7L-J||F7.L7|LJF-7L----J||L7L7LLJLJL7FJ||F--JL--7J|L7||L7|L7L7|F7FJF|.LJ7-LJ-|J|FJF-77L|LF.F-JF|J|F.|.J7F7
                |F|7.JJ|LJ|L-.F-J||.FL--7FLLJ||77L||F--JLJL-7||F7L7L------JL7L7L-7F---JL7|||F-7F-7L7|FJ||-LJJ|FJLJ|L-7J7.||.|.F|.F|L||.-.|L|JJLFL7FJJ||||.7|
                LJFJ77J|J-LJ.7J-LF-F7L--|J7LLJJJLFJLJF-7F7F7||LJL7L-7F-7F7F7L7|F-JL--7F7||LJL7|L7L-JLJLLJL|F7|L-7||F7|77J.L-LJ7--|J-JJF|7L-J.LFJ-|-.LLJLLJ77
                FLL-F-.LFFJJ.L77..|L--|7||LJ....|L---JFJ|LJ|||F7FJF-JL7LJ|||FJ|L-7F7FJ|||L7F-JL-JJLJ|LJ|JFFJLJF7|-LJLJJJ7..FL||-7|L.L7FLJLL77-L7.-.F--|7.LF|
                F|LFJ.7J|.7.FF-L7FLJJ7|.LF7.77FFF----7L7L7FJ|LJ|||L7F7|F-J|||FJF7||LJFJLJ-|L---7-F-7|-F---L--7|LJJL|7L77FFJ7.J--LL7.FL7.F|-F-.|FFJ.|JJF|--J|
                ||-|-7-7777|-7-FLLJ|.J.F7|J-.L--L-7F7L7|FJL7|F-JL7FJ|||L7FJLJL7|LJL7FJ7LF-JF--7L-7J7J|.FJ|.JL|L77FFL-.-F-J-|-7||LLF7.F--77.|.FJ|.||||7L-7JJ|
                LL-7-.LLJL-JFJJL||JL77F|J-7|.7.|||LJL7LJL7LLJL-7FJL-J|L7||7-F-JL-7FJ|F--JF7L7-L7FJ.|7|FL-7.|-L-JJFL7L7|LJ|-|.-L|7J|L--JFJ7--F7.J-||LF-LL-J||
                FL7J---JL-L--.F.L77L--FLJ|-7-J7LF----JF-7L-7F7JLJJ7|.L7|LJ-FJF7F-JL-J|F7FJL7|JLLJFFJLL7-|J.L-7.L-|.|7.|7-|.L7LL|JFL7FL-7-J.FLJ-|FJ||L7LFJ.F-
                F.J.||7L|..-|--JL|F--FL7FL.F.LF-L---7FJFJF7LJ|J-FL|--LLJ.JJL-J||-LJ.LLJ|L7FJL7..J-L-|-J7|F-LL|-7FLF--.F7JL-.-J.L-|LFLFJJ|LF-FJ--L7L-L|-J7F7|
                |F|FLF|F--F--J.J7||7--7|7..-.FJ.|J.||L7|FJL7FJJFFJL-7J.J-7L||F|L7FFLJ.LL7|L7FJJ7LLL7|||L.L.F||7-LFJ.LFJ-77LJJF77.LF-JF-J-FJ-LJ.|F|LF-L.LLF7J
                J-FJ.-JL|J.-L--JFJL-FJ-L|J--|LL-7.-LL-JLJ-LLJJ-L7-L7J--J-7.7--L-J-JL|-LLLJLLJ-LJ.L-LJ--..L-|-JJ-L|---|7JFL-L--J|-F-J-|-JJFJ-LJ-LL7-7LJ-7L-JJ
                """;

        String[] inputArray = input.split("\n");
        // Part 1
        Day10 pipeNetwork = new Day10(inputArray);
        int farthestPoint = pipeNetwork.findFarthestPoint();

        System.out.println("The farthest point from the start is " + farthestPoint + " steps away.");

        //Part 2
    }
}

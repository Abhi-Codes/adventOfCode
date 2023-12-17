package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {

    public static void main(String[] args) {

        String input = """
                .....O..#....O.O.....OO#...#.....#...........O#..OO...#O##..#.....#...O.OO...#.O......OO#...OO...O..
                OOO....#O.#..O#.#.OO...OO..O##...O.....O.O...#.O.......OOO.O...#.....#.#.O....O.O.....#...#.OOOO.O..
                ...O..#...#O..#......O#.......#..O##O.#....##......O.O...O..#O.O..##..#..#O......##...OO.#.O#..O...#
                .O..#...##O..#....#...O.O...#........O.O..#...O..#O.O.....#O...OO.#....O..O....OO##OO#...O#.O#...#.#
                #.O......O.#.O..OO.O..#....#.OO....#.#..#..O#.O....OO......#.##O...#.OO........O..OO...OOO#O.#O#.O.#
                ...##.O.#O.O.##.#..O....#.#..#.OO.O...#....#.O..O#O.O.O..O...O.O..##...O#..#.......O.O......#O.#O...
                .O.O..O..O#.O.O..O.O..O..O..............#...O#O#.O.#.##..........##O....O.O...........O..O..O#...#..
                ...#O.O.OO#..O..#.#...#O...O..#...#.........O...#OO#...#.O#....O...##..O...O.#O#.O#...O.O...#OO.O#..
                ...##.##.O...O..#..#O.O.O...O.##..#.#.O..OO.O......OO..#..O.O..#..#.......O..O.....OO.OOO.O..O..#O.#
                ....#O.O...#OO..O.O............O.....#O..#O#...#.........O.O..O...OO....#..O..##.O..####O#O.#.......
                .OOO.......##..O..#O.OO.##.....O....#..OO.#O.......OO..........OO..O..O#...#..O..##.O........#....O.
                ....#.O#...#OO..............O..O.O...O...O...O....O###....O.#OO...O#.OO.#...#.#.##.....O...........O
                .......#O##..#......#.O.#.......O#.#.O#O#..#.#...O.....O..O#..O..#.O#....O..#.......O..O..#O........
                O..O.#.O...#....##...#.....O..#.#......O..#O..#.OO..O.##.........O#O.#....#..#.###OOOO..#OO.#O.O.O#.
                ...O##O.#.............#..O.O....O#.....#OO...OOO......O#.O...#O#.#..#....O..#.#..O..#.O...OO.....O..
                ...O......#..O.#.#..##....O..#....#..O.#.......#..O...##O.O.......#..O.OO..O..........O....##.....#.
                ........#O##..O....#....#.....O...O.O.O.OO..O........#..#.O.........O.....O..O.O..#O.O...O...#..##..
                .O.#.....O.O.#..##O#.....#.#..O......O..##.#.#....##...O...OO.#..........OO.#O#OOO..#....#.#...O.#..
                ....#...#.OO....#..OO#.#OO.O....OO..#..O....OO.......##.##O####O.O.O#..#....O.O......O.....#.O..O...
                .O#O#........O.#...O#O..#O...O.#..#.#..O#........O###..O..#...#..O.##..#............O.......O..O....
                ........#.O.....O...O.....##.....##.#.#.#....OO####.#..O#....#O...#.O#..O.O.#.O......O..#..O.....O..
                ..O#..##O.OOOO...OO....OO#.#.O.....#.#......#...#OO#...####O#....O...O.O.O.O#....O.##OO#..O.#.O..##.
                .##...............#.O#OO.#.......#...#O.#.OOO#.O...#....#....O#..O.#..O...O#...#..O#.O#O#........OO#
                ...#......##...#.#O..O......O#O.O..OOO..O##....#....O.#..O....#........OO#.#...#.O#..O....O.O##..O#.
                .OO......O.....O...##..#...#....O.#....O....O...........OO.#.#..#...#.O#O.#.#......#.O......O.O#.#.#
                O.O....#O...##.O..#O.O...#...O.......O.OO...O.O#.....#..#.O#.....O.O...........O.....O...O.....#....
                .#O#..O....O.......#..OOOO.....O.....####...#.......O...O#O...O..#..#...##.#.....#....O....#...##...
                ........O.O#OO#..#.O.....#.OOO......OO#.#.....O.#..#O.........#OOO......O..#......O..##....#O....#.#
                ..O..#O.....##..#.OO..O..........OO..O...#.##O.#.#..O.O#.#.#......OOO......#..O..#.O....O#...OO#....
                ...#.OOO.O......O#O...O.O.OO....OO#.....O.O.O#.....O##.OO......#......OOO#O.OO.O#...#.....O....OO...
                ...#.#O..O...##.#.###.O.#...#.#....O.OO...O.O.O.O...#.#.O....#OO#.OO...#O...#O...OO.O..O..O....O..O#
                .##.O......#.##.....O...OO#...O.....O..#.....O.........#....O..#....O.####......#...#........O#..OO.
                .#.#.O.#.O#..OO.#....O...#.O...O..#.O.....#OO....#.O.O.O......OO...#.#.O#O#OO.#O.O.....O.........#..
                ..#.#..O...#..#.....OO.#O...O#...OO.O...O#....##O....#.#...OO.#.....#.O#.....O.....O...#.#O.....#...
                .O..OO#.#OO...O..#..........OO.............##..####...#.O....O...#.#...O#..O.O..OO.#....OOO..#...OO.
                #...#....#.........OOOO....OO....O#O..........#..O#..O...#.OOO.....OO.......OO.##...O.#....OOO...##.
                #.........#OO#.O.#..##.#.##O...#...OO....O#O......#.......O.O...#O...O.O.#O##O#..O....O#.#......O.#.
                ...O..O#..#...##..O....O..O......O.O#...OO.......##......OO..#.O....#.##O.OO....#.O.#.OO..O....O.#..
                .O..O.#.O.#............O....#....#....#.#.....#...OO#...OO.##OO.#..#....#....#.O#.OO#.#.O.O#..O.....
                .OO#O...#.#......O...#...O##.O....O#...O..O..O..O.#O..#..###OO..#..O##O.#.O...O#O...OO.O..#.#O#..#.#
                #..OOOO...OOO.O..#O...OO##....O#..OO...O.O.#....#O#O....#.#....O#...O...O....O...#..O.#O..#..#..#O#.
                ##.....OO#.....#.O.O..OO.O.#.....O..O....#....#.........OOO.O.....O#...O..#..........O.O.#..#...#..O
                ##.#.#.O..O.....##.##.#O..#........O#....O..O....O....#..O.##OO#.O.O..O#...###.OO.O#..#.O...##..OO..
                O....#.....O...#.#.OO..OO.###.O.#O.O...#.O.#......O.O#.#O#..O#...O...#.OO##..#.O.......#O....#......
                ..#....#..#.O..#.O.O..O#......#O..#...O#.....#..#O..O.O.O.....#O.#.........O..........O.#O..O.O..O..
                OO####.OO..O...#..#O#.O#.#......#...#.#.O##..##.O.....O.OO.O.O#...O...O...O...O.O#........#..O.O.O..
                .O#O......O...O.#O##.OO.O..OO#..#OO...O.O..O.#...#O..OO....O.#OO.#O.#..O...#....#..OO#.......###....
                ..#O#O........#.O.#...#O.#.O..O.O#..O.O#OO.O.O#O.#OOO....O#.........#..O..#.O...O........O#.......O#
                ...O.#.OO....#OO...#.........O..O.O.#OOO....O.....O..O.#.......OO..#O......#.#O..#.OO#O....O#.....O.
                O.#OO..............#.O...##....OO#OO.......O#.....O.O....##....O........#......O..#..#..###O...#....
                ...O.OO..O#...#.#O..O#...#..OO...OO......O..#..#....O..#.#......#...#....#.OOOO.#....##......O#..#.O
                ..#.......#...##OOO..O.....O..O.O.O..O#.....#.#O........O..#...#O..#...O#...#.OO....#.O..O#..O.#OO.#
                ##.#.O.OO..O..##O...O..O..O.......O.O....#...O......O.O.#.O......O......O...O...O.#.#.OO#OO...##..O.
                .O...OO...#..O.O.#O..#O......#.#..#...........#O....#.###.#....#O.#...O....##...#.OO....O.#.O.O.O..O
                .#..O#..O......#O...O..#.#.....OO...#.##.......#..O.O.......#......#..#....#.##..O.......O...#.OO...
                O..O...#O.O#O#.....#O..#..#..#...O...O#.#O........O.OO.O.....#O#.#...O#.#...#O.#...#O.....#..O.O....
                OO.OO.#....O....O##..O#..O...#.......#......#.OO..O......#O#.OO.#...O.......#.#O....#.O...#.O.....O#
                .O#.....#O.O....OO..O..O.#O.#O.O.OO..#.#...O.........O#...OO....O...#..O....O#.O#..#O#..O.#..O....O.
                OO..##.O.......OO.O..#O...O...O.##...........O#.O...O....O#.#.OO...#O....O.#O......O#.O...........O.
                O.#...........#O#.O.##...O#....O.O...#..O...#.OO.....#OO..O#.#.O..........#.#..O.#...#.......##O#O..
                ....O......OO.OO..........OO.O...O.....#.#...OO..#.O#.O......OO##O.O...#.OOO...#...#..O......OO.#...
                ....#..#.#.O..O#....O.#.O#O#.#.#.O...O.....O..........#...##.O#..O#O.O..O#...###..O..#.O...O...#...#
                ###.....O#.#..##....#..O..#OO.#O.O...#..#O.#..O......O.....#.O..OO##.#.##.#.....#...O#O##....#...O.O
                .....#..O.......#..O.#..#..#..O#...#...#O...O#.OO#.O#O#OO..##.O#.O.#..O.#..O..O....#.O.O..O........O
                ........OO...#OO.#..#.OO#...O..OO....#......OO#O#.#.#...#O.#.O......O.....O#..#...#....#.#O#..#...O.
                O#...##..O........#OO##...#......O...........OO#...O.O.##O........#....##O.....##..O.....O.#O#.#....
                ......O....##.#.O##.O#........##O.O..##...O..#.O..O....#.OO...OO.#.......#.O.....OO#O.##O.O....O#..O
                ..O....#...O.O...##.#....O....O..O......#...O..#..O......O.OO.#..#.O#...#..O.OO........#..#O...O..#.
                #.O.#.O.....O..###.#.............O.##O.#.OO..#.....#.....O.........O.OOOO#...#.O#.##O.......OO.#OO..
                ....OOO..O...OO##.O...#.#.OO..O#...O.#..O..OO.#O.##..##....O.O..OO#O...O#.....O..O##......#.#......O
                ..O.....O..#.........O#..O..#.OO..O##..O.O.##O#....O.OOO...#...O...O#O.#.O..............O....O#.#...
                OO.#....O.......O..O.#.OO....##....OOOO...O.......OOO#....O#...O#.OO..O.....#.##...#..#..O......#O.#
                .O...O...O.#O.O..OO##..#..#OO.O.OO....O........#O.....###OO.OO.OO..O##...O.#.O.....O......O...O##...
                ...O...O..#..O.......O#O....##.O.OO#.O...#OO.#..O.O.#...#O.#.#...O..O...O..OOO..#..O..O..#.#..#.O..O
                .....#.......O.O...#.O.........O...#...#..O.##..OO...#.O..........O#.#..O#.#.O.OO.#........OO..O..O.
                #O.OO..#..#.###...O....O.#...........#O....O.#O.#..........O.........O.....#..O.#..O.....O....OO....
                O......O..O...O...O..O.OO...O#...O......#..#O.O#.OOO..#.#.....#O.....##..#OO...O.....####O..........
                O....O#..#O..OO#O...O.#..O.....#..#..O..#..O..O#O..#.......O.#.#...O.........O..OO#.......#.O..OO.#.
                .#.#...O.#O.....O....O.#O..O.#....OO..O#...#.O.#.OOO.O..O.....O.O..#.#..OO......#.#.O#O.#....O.O.O#.
                ....#O..##..#.#..#..#....#.#O.#.O####.......##.......##O.O......O..OO.##OO.......#...O..O....O#....O
                .......O..OO.#.........O..O...OO...O.O.....#.....#.....O...O..#.##O.O...OO....O...O#O......O..O#....
                .....O....#...O.O.#O...O#..O....O.OO......O......O#O..O.O.O.##O..#...O...O.#.##O....O....O...O..#.#O
                ..O.O#O.....O#.O........##...#...#.O##O....#.#.#..O.O.OOO.##...O...##.O.O.#...O.#.#...##.....#.O..O#
                .O......O.#O...OOO..#..##...#...O...O..O.........O.O#.....##.......##..O.OO...#.#..........OOO..#...
                #O............O..#O#..#OO...O..#.O.O.O....O...#.#.#OO....O..##....O#..#...#OO..#.#........#........O
                .#....#O#.O.#.....#.O....O#.....O....OOO#O.O.O.....O..OO.#O..#..#.OOOO......#OO.#.........O#.O#..#.#
                ...O..#..O.#......O....#..#..O.O.O.###.#.....OOO.#O.O.....O.O.#.#O...#.O#..#.....#......##...O.#.#..
                #...O....#.O....#...###...O.#.O..O#.#.#.O...........O#.#..O.#...O...O..#O..O....#O...O.O##....O....O
                #O...#.OO#.#O........OO......#..O...O.OOOO.#.#..O#.##.O.O....#..O#..O.......#.O...#..#O......O..O...
                ...O....O..O.#OO#...O.....O..#........OO.....OO.....#.O..O......O...#.OOO......OO...O.#O...O..O##...
                ..#O.O.......##..O.OO.........#.........O.O#....O..O#.O...#OOO...O...#O.#O.#O.......#..#.##..##O...#
                ..O....O......#...O.#......O#....O.....O.....O..O......##.#.#O..OO...#.#.O#.O#O.#.#.O.#.#.O....O...#
                ...O...O.......#............O#O#..#.O....O#..OOO.#O#..OO.O...........OO...#...OOOO...#..O##O.O..#...
                .O.O....O.#O.O#...#.#...........#.O.O#.#..O.#........O..O...#....O........O..O.#.#.#...#.........#O.
                .O#..#O...#.O#.#...#...O...#..O##O..O......O.O....#O.#..O#OOO.O.#.........O....O.#.O....#OO....#...O
                O#...#....#.#...#...O..##.#.....O.#..#.O....###O..O.OO#.....O...O.......#.....#O......#...OO.#.O..#.
                O.O#O.#...OO##.#OO..#...O..OO#.O.....##..OO.....##..#O...#...OO#.O...#......#.O#..##..#.O.....O#.#.O
                O..#O.O...OO.#O.O....#.OO...O...#..O.O..#OO............#........O..#.....#OOOO...O......O.##.O....O#
                #...#OO.OO.#.#...O...O....O..##.O...#..O.......####.....#......#..O...#.##....#O..O..O.#.#.#......O.
                .....O...O..#.O#...O..#.#.O#.O.OO.#..........O...OO#O#.....#.OO.........#OO.O...O..OO.O.#......O##..
                """;

        String[] grid = input.split("\n");

        part1(grid);

        part2(grid);


    }


    private static void part1(String[] grid){

        String[] transposedGridForNorth = new String[grid[0].length()];
        for (int i = 0; i < grid[0].length(); i++) {
            transposedGridForNorth[i] = "";
            for (String rowStr : grid) {
                transposedGridForNorth[i] += rowStr.charAt(i);
            }
        }

        String[] modGridForNorth = tiltGrid(transposedGridForNorth);
        String[] northGrid = new String[modGridForNorth[0].length()];
        for (int i = 0; i < modGridForNorth[0].length(); i++) {
            northGrid[i] = "";
            for (String rowStr : modGridForNorth) {
                northGrid[i] += rowStr.charAt(i);
            }
        }

        System.out.println("Score for part 1 is " + computeScore(northGrid));
    }

    private static void part2(String[] grid){

        List<String> path = new ArrayList<>();
        List<String> fullCycle;
        Map<String, String[]> map = new HashMap<>();

        int m=0;
        while (true){
                m++;
                String key = concatenateRows(grid);
                if(map.containsKey(key)){
                    System.arraycopy(map.get(key), 0, grid, 0, grid.length);
                    if(path.contains(key)){
                        fullCycle = path;
                        break;
                    }
                    else{
                        path.add(key);
                    }
                }
                else{
                    path.clear();
                    grid = performCycle(grid);
                    map.put(key,grid);
                }

        }
        int finalGridKey = (1_000_000_000 - m) % fullCycle.size();
        String[] ansGrid = map.get(fullCycle.get(finalGridKey));
        System.out.println("Score for part 2 is " + computeScore(ansGrid));

    }


    /**
     * Computes the total load on north side
     * @param grid
     * @return
     */
    private static  long computeScore(String[] grid){
        long score = 0;
        int k = grid.length;
        for (String string : grid) {
            long count = string.chars().filter(ch -> ch == '0').count();
            score = score + (k * count);
            k--;
        }
        return score;
    }

    /**
     * Concatenate strings row wise to generate the key for cache
     * @param gridRows
     * @return
     */
    public static String concatenateRows(String[] gridRows) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String row : gridRows) {
            stringBuilder.append(row);
        }

        return stringBuilder.toString();
    }

    /**
     * Performs 4 tilt operations
     * @param grid
     * @return
     */
    private static String[] performCycle(String[] grid){

            String[] transposedGridForNorth = new String[grid[0].length()];
            for (int i = 0; i < grid[0].length(); i++) {
                transposedGridForNorth[i] = "";
                for (String rowStr : grid) {
                    transposedGridForNorth[i] += rowStr.charAt(i);
                }
            }

            String[] modGridForNorth = tiltGrid(transposedGridForNorth);
            String[] northGrid = new String[modGridForNorth[0].length()];
            for (int i = 0; i < modGridForNorth[0].length(); i++) {
                northGrid[i] = "";
                for (String rowStr : modGridForNorth) {
                    northGrid[i] += rowStr.charAt(i);
                }
            }
            //printGrid("North Grid", northGrid);

            String[] westGrid = tiltGrid(northGrid);

           // printGrid("West Grid" ,westGrid);

            // Transpose the grid to make column manipulation easy
            String[] transposedGridForSouth = new String[westGrid[0].length()];
            for (int i = 0; i < westGrid[0].length(); i++) {
                transposedGridForSouth[i] = "";
                for (String rowStr : westGrid) {
                    transposedGridForSouth[i] += rowStr.charAt(i);
                }
            }
            //printGrid("Transpose Grid for South" , transposedGridForSouth);

            String[] modGridForSouth = tiltGridOpposite(transposedGridForSouth);
            // printGrid("Modified Grid For South" , modGridForSouth);
            String[] southGrid = new String[modGridForSouth[0].length()];
            for (int i = 0; i < modGridForSouth[0].length(); i++) {
                southGrid[i] = "";
                for (String rowStr : modGridForSouth) {
                    southGrid[i] += rowStr.charAt(i);
                }
            }
            //printGrid("South Grid",southGrid);

            String[] eastGrid = tiltGridOpposite(southGrid);
           // printGrid("East Grid",eastGrid);
        return eastGrid;
    }


    private static void printGrid(String name , String[] grid){
        System.out.println(name);
        for (String s : grid)
            System.out.println(s);
        System.out.println("-".repeat(20));
    }


    /**
     * Performs tilt operations for north and west
     * @param grid
     * @return
     */
    private static String[] tiltGrid(String[] grid){
        String[] modGrid = new String[grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            int numZeroes = 0;
            int numDots = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < grid[i].length(); j++) {
                char ch = grid[i].charAt(j);
                if(ch == '#'){
                    sb.append("0".repeat(Math.max(0, numZeroes)));
                    sb.append(".".repeat(Math.max(0, numDots)));
                    numZeroes = 0;
                    numDots = 0;
                    sb.append("#");
                } else if (ch == '.') {
                    numDots++;
                }
                else {
                    numZeroes ++;
                }
            }
            sb.append("0".repeat(Math.max(0, numZeroes)));
            sb.append(".".repeat(Math.max(0, numDots)));
            modGrid[i] = sb.toString();
        }
        return modGrid;
    }

    /**
     * PErforms tilt operations for south and east
     * @param grid
     * @return
     */
    private static String[] tiltGridOpposite(String[] grid){
        String[] modGrid = new String[grid[0].length()];
        for (int i = grid.length -1; i >=0; i--) {
            int numZeroes = 0;
            int numDots = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = grid[i].length()-1; j >=0; j--) {
                char ch = grid[i].charAt(j);
                if(ch == '#'){
                    sb.append("0".repeat(Math.max(0, numZeroes)));
                    sb.append(".".repeat(Math.max(0, numDots)));
                    numZeroes = 0;
                    numDots = 0;
                    sb.append("#");
                } else if (ch == '.') {
                    numDots++;
                }
                else {
                    numZeroes ++;
                }
            }
            sb.append("0".repeat(Math.max(0, numZeroes)));
            sb.append(".".repeat(Math.max(0, numDots)));
            modGrid[i] = sb.reverse().toString();
        }
        return modGrid;
    }
}

package code;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.apache.commons.math3.util.ArithmeticUtils.lcm;

class Module {
    private String name;
    private String type;
    private String[] outputs;
    private Map<String, String> memory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getOutputs() {
        return outputs;
    }

    public void setOutputs(String[] outputs) {
        this.outputs = outputs;
    }

    public Map<String, String> getMemory() {
        return memory;
    }

    public void setMemory(Map<String, String> memory) {
        this.memory = memory;
    }

    public Module(String name, String type, String[] outputs) {
        this.name = name;
        this.type = type;
        this.outputs = outputs;

        if ("%".equals(type)) {
            this.memory = new HashMap<>();
            memory.put("off","off");
        } else {
            this.memory = new HashMap<>();
        }
    }

    @Override
    public String toString() {
        return name + "{type=" + type + ",outputs=" + String.join(",", outputs) + ",memory=" + memory + "}";
    }
}

public class Day20 {
    public static void main(String[] args) {

        String input = """
                broadcaster -> a
                %a -> inv, con
                &inv -> b
                %b -> con
                &con -> output
                """;

        input = """
                %pr -> ql
                &jg -> mg
                &mg -> rx
                %mq -> gz, nt
                %db -> ff, dz
                %dx -> zs, bm
                %bd -> nt, lj
                %qj -> hj
                %xs -> zs, dx
                %xd -> nt
                %gb -> fx, th
                &nt -> ds, hj, ht, rh, qj
                %ht -> nt, vp
                &rh -> mg
                %sq -> th, cd
                %tt -> pq
                %dh -> sh
                %rz -> zc
                %cx -> xr, nt
                %zq -> tt, th
                &jm -> mg
                %lj -> nt, cx
                %mp -> ff, bq
                %dz -> ff, gd
                %fz -> bk, th
                %hj -> mq
                broadcaster -> gb, ht, vk, zz
                %zc -> dh
                %pj -> xs
                %bn -> fz
                %mr -> bf
                %mj -> th, sq
                %gg -> pj, zs
                %sh -> mr, zs
                %bf -> zs, gg
                &hf -> mg
                %bm -> zs
                %bk -> zg
                %pq -> th, mj
                %xf -> ff, db
                &th -> bn, gb, tt, hf, bk
                %fx -> th, bn
                &ff -> vd, bq, pr, vk, ql, jm
                %xr -> nt, xd
                %bq -> pr
                %zz -> rz, zs
                %gz -> nt, ds
                &zs -> mr, pj, zz, dh, jg, zc, rz
                %vd -> xf
                %vk -> mp, ff
                %cv -> ff
                %cd -> th
                %zg -> th, zq
                %gd -> ff, cv
                %ql -> lt
                %lt -> ff, vd
                %ds -> bd
                %vp -> nt, qj
                """;

        String[] lines = input.split("\n");


        part1(lines);
        part2(lines);
    }

    private static void part1(String[] lines) {

        Map<String, Module> modules = new HashMap<>();
        String[] broadcastTargets = new String[0];


        for(String line : lines){
            String[] parts = line.split(" -> ");
            String left = parts[0];
            String right = parts[1];
            String[] outputs = right.split(", ");



            if ("broadcaster".equals(left)) {
                broadcastTargets = outputs;
            } else {
                String type = left.substring(0, 1);
                String name = left.substring(1);
                modules.put(name, new Module(name, type, outputs));
            }
        }


        for (Map.Entry<String, Module> entry : modules.entrySet()) {
            String name = entry.getKey();
            Module module = entry.getValue();
            for (String output : module.getOutputs()) {
                if (modules.containsKey(output) && "&".equals(modules.get(output).getType())) {
                    modules.get(output).getMemory().put(name, "lo");
                }
            }
        }
        int lo = 0;
        int hi = 0;

        for (int i = 1; i <= 1000; i++) {
            lo++;
            Queue<Triple> q = new ArrayDeque<>();
            for (String broadcastTarget : broadcastTargets) {
                q.add(new Triple("broadcaster", broadcastTarget, "lo"));
            }

            while (!q.isEmpty()) {
                Triple triple = q.poll();
                String origin = triple.origin;
                String target = triple.target;
                String pulse = triple.pulse;

                if ("lo".equals(pulse)) {
                    lo++;
                } else {
                    hi++;
                }

                if (!modules.containsKey(target)) {
                    continue;
                }

                Module module = modules.get(target);

                if ("%".equals(module.getType())) {
                    if ("lo".equals(pulse)) {
                        Map<String,String> map = module.getMemory();
                        String outgoing = "";
                        if(map.containsKey("off")){
                            map.clear();
                            map.put("on","on");
                            outgoing = "hi";
                        }
                        else{
                            map.clear();
                            map.put("off","off");
                            outgoing = "lo";
                        }
                        for (String x : module.getOutputs()) {
                            q.add(new Triple(module.getName(), x, outgoing));
                        }
                    }
                } else {
                    Map<String,String> map = module.getMemory();
                    map.put(origin, pulse);
                    String outgoing = map.values().stream().allMatch(x->x.equals("hi")) ? "lo" : "hi";
                    for (String x : module.getOutputs()) {
                        q.add(new Triple(module.getName(), x, outgoing));
                    }
                }
            }
        }

        System.out.println(lo * hi);
    }


    private static void part2(String[] lines) {

        Map<String, Module> modules = new HashMap<>();
        String[] broadcastTargets = new String[0];

        for(String line : lines){
            String[] parts = line.split(" -> ");
            String left = parts[0];
            String right = parts[1];
            String[] outputs = right.split(", ");

            if ("broadcaster".equals(left)) {
                broadcastTargets = outputs;
            } else {
                String type = left.substring(0, 1);
                String name = left.substring(1);
                modules.put(name, new Module(name, type, outputs));
            }
        }


        for (Map.Entry<String, Module> entry : modules.entrySet()) {
            String name = entry.getKey();
            Module module = entry.getValue();
            for (String output : module.getOutputs()) {
                if (modules.containsKey(output) && "&".equals(modules.get(output).getType())) {
                    modules.get(output).getMemory().put(name, "lo");
                }
            }
        }


        String feed = modules.entrySet()
                .stream()
                .filter(entry -> Arrays.asList(entry.getValue().getOutputs()).contains("rx"))
                .findFirst()
                .orElseThrow()
                .getKey();


        Map<String, Integer> cycleLengths = new HashMap<>();
        Map<String, Integer> seen = modules.entrySet()
                .stream()
                .filter(entry -> Arrays.asList(entry.getValue().getOutputs()).contains(feed))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> 0));

        int presses = 0;

        while (true) {
            presses++;
            Queue<Triple> q = new ArrayDeque<>();
            for (String broadcastTarget : broadcastTargets) {
                q.add(new Triple("broadcaster", broadcastTarget, "lo"));
            }

            while (!q.isEmpty()) {
                Triple triple = q.poll();
                String origin = triple.origin;
                String target = triple.target;
                String pulse = triple.pulse;


                if (!modules.containsKey(target)) {
                    continue;
                }

                Module module = modules.get(target);
                if (module.getName().equals(feed) && "hi".equals(pulse)) {
                    seen.put(origin, seen.getOrDefault(origin, 0) + 1);

                    if (!cycleLengths.containsKey(origin)) {
                        cycleLengths.put(origin, presses);
                    } else {
                        assert presses == seen.get(origin) * cycleLengths.get(origin);
                    }

                    if (seen.values().stream().allMatch(value -> value > 0)) {
                        long x = 1L;
                        for (int cycleLength : cycleLengths.values()) {
                            x = lcm(x,cycleLength);
                        }
                        System.out.println(x);
                        System.exit(0);
                    }
                }

                if ("%".equals(module.getType())) {
                    if ("lo".equals(pulse)) {
                        Map<String,String> map = module.getMemory();
                        String outgoing = "";
                        if(map.containsKey("off")){
                            map.clear();
                            map.put("on","on");
                            outgoing = "hi";
                        }
                        else{
                            map.clear();
                            map.put("off","off");
                            outgoing = "lo";
                        }
                        for (String x : module.getOutputs()) {
                            q.add(new Triple(module.getName(), x, outgoing));
                        }
                    }
                } else {
                    Map<String,String> map = module.getMemory();
                    map.put(origin, pulse);
                    String outgoing = map.values().stream().allMatch(x->x.equals("hi")) ? "lo" : "hi";
                    for (String x : module.getOutputs()) {
                        q.add(new Triple(module.getName(), x, outgoing));
                    }
                }
            }
        }

    }

    private static class Triple {
        String origin;
        String target;
        String pulse;

        public Triple(String origin, String target, String pulse) {
            this.origin = origin;
            this.target = target;
            this.pulse = pulse;
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "origin='" + origin + '\'' +
                    ", target='" + target + '\'' +
                    ", pulse='" + pulse + '\'' +
                    '}';
        }
    }
}

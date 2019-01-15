package BlockStructure;

import java.util.*;
import java.util.stream.Collectors;

public class BlockContainer<T extends Comparable<T>> {
    private int blockSize;
    private int index;
    private Map<Integer, Set<T>> blocks;
    
    public BlockContainer(int size) {
        blocks = new HashMap<>();
        blockSize = size;
        index = 1;
    }

    public void add(T element) {
        if(!blocks.containsKey(index))
            blocks.put(index, new TreeSet<>());
        blocks.get(index).add(element);
        if(blocks.get(index).size() == blockSize) {
            index++;
        }

    }

    public void remove(T element) {
        blocks.get(index).remove(element);
        if(blocks.get(index).size() == 0) {
            blocks.remove(index);
            index--;
        }
    }

    public void sort() {
        List<T> temp = blocks.values()
                                    .stream()
                                    .flatMap(set -> set.stream())
                                    .sorted()
                                    .collect(Collectors.toList());
        blocks = new HashMap<>();
        index = 1;

        temp.stream().forEach(element -> this.add(element));
    }

    public String toString() {
        return blocks.values()
                    .stream()
                    .map(set -> set.stream()
                                    .map(T::toString)
                                    .collect(Collectors.joining(", ", "[","]"))
                        ).collect(Collectors.joining(","));
    }
}

package br.com.search;


/**
 * Class that implements stream logic
 */
public class StreamImpl implements Stream {

    private int index;

    private String sequence;

    public StreamImpl(String sequence) {
        this.sequence = sequence;
    }


    /**
     * Method for retrieve next element
     * @return next element
     */
    public char getNext() {
        return sequence.charAt(index++);
    }


    /**
     * Method for check for element
     * @return true or false
     */
    public boolean hasNext() {
        return index < sequence.length();
    }

}

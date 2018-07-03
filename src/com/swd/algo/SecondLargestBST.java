package com.swd.algo;

public class SecondLargestBST {

    public static class BSTElement {

        private int value;
        private BSTElement left;
        private BSTElement right;
        private BSTElement parent;

        public BSTElement(int value){
            this.value = value;
        }

        public BSTElement getLeft() {
            return left;
        }

        public BSTElement getRight() {
            return right;
        }

        public void setLeft(BSTElement left) {
            this.left = left;
            left.setParent(this);
        }

        public void setRight(BSTElement right) {
            this.right = right;
            right.setParent(this);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public BSTElement getParent() {
            return parent;
        }

        public void setParent(BSTElement parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {

            String leftValue = null == left ? "null" : new Integer(left.getValue()).toString();
            String rightValue = null == right ? "null" : new Integer(right.getValue()).toString();
            String parentValue = null == parent ? "null" : new Integer(parent.getValue()).toString();
            return "BSTElement{" +
                    "value=" + value +
                    ", left=" + leftValue +
                    ", right=" + rightValue +
                    ", parent=" + parentValue +
                    '}';
        }
    }
    public static void main(String[] args){
        new SecondLargestBST().run();
    }

    private void run(){

        {
            BSTElement root = generateLargestNoLeft();
            System.out.println("Answer: " + secondLargestItem(root));
        }

        {
            BSTElement root = generateLargestHasLeft();
            System.out.println("Answer: " + secondLargestItem(root));
        }

        {
            BSTElement root = generateLargestIsRoot();
            System.out.println("Answer: " + secondLargestItem(root));
        }



    }


    private BSTElement secondLargestItem(BSTElement root){

        // Find the largest element
        BSTElement largest = largestItem(root);

        // If no LEFT, 2nd largest is the parent
        if ( null == largest.getLeft()) {
            return largest.getParent();
        }
        // Find the largest on the LEFT
        else {
            return largestItem(largest.getLeft());
        }
    }

    private BSTElement largestItem(BSTElement element){

        if (element.getRight() == null){
            return element;
        }
        else {
            return largestItem(element.getRight());
        }
    }

    private BSTElement generateLargestNoLeft(){

        BSTElement root = new BSTElement(20);
        System.out.println("ROOT --> " + root.toString());

        BSTElement element2 = new BSTElement(26);
        root.setRight(element2);

        BSTElement element3 = new BSTElement(36);
        element2.setRight(element3);

        BSTElement element4 = new BSTElement(24);
        element2.setLeft(element4);

        BSTElement element5 = new BSTElement(40);
        element3.setRight(element5);

        BSTElement element6 = new BSTElement(35);
        element3.setLeft(element6);

        return root;
    }

    private BSTElement generateLargestHasLeft(){

        BSTElement root = new BSTElement(20);
        System.out.println("ROOT --> " + root.toString());

        BSTElement element2 = new BSTElement(26);
        root.setRight(element2);

        BSTElement element3 = new BSTElement(36);
        element2.setRight(element3);

        BSTElement element4 = new BSTElement(24);
        element2.setLeft(element4);

        BSTElement element5 = new BSTElement(40);
        element3.setRight(element5);

        BSTElement element6 = new BSTElement(35);
        element3.setLeft(element6);

        BSTElement element7 = new BSTElement(38);
        element5.setLeft(element7);

        return root;
    }

    private BSTElement generateLargestIsRoot(){

        BSTElement root = new BSTElement(50);
        System.out.println("ROOT --> " + root.toString());

        BSTElement element2 = new BSTElement(26);
        root.setLeft(element2);

        BSTElement element3 = new BSTElement(36);
        element2.setRight(element3);

        BSTElement element4 = new BSTElement(24);
        element2.setLeft(element4);

        BSTElement element5 = new BSTElement(40);
        element3.setRight(element5);

        BSTElement element6 = new BSTElement(35);
        element3.setLeft(element6);

        BSTElement element7 = new BSTElement(38);
        element5.setLeft(element7);

        return root;
    }


}

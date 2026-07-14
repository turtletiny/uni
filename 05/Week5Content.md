The List interface
The Java List interface represents an ordered collection of elements that can contain duplicates. It provides methods to access elements by their index and perform various operations on the list. Java provides a number of implementations of this interface; one that you are already familiar with is the ArrayList. There are others though like the LinkedList and Vector.

Here are some common methods provided by this interface (see the full listing hereLinks to an external site.):

add(E element): Adds an element to the end of the list.
add(int index, E element): Inserts an element at a specified index.
remove(int index): Removes the element at the specified index.
get(int index): Retrieves the element at the specified index.
size(): Returns the number of elements in the list.
indexOf(Object o): Returns the index of the first occurrence of the specified element.
contains(Object o): Checks if the list contains a specified element.
Notice how many of these methods already familiar from the ArrayList class. This isn't surprising since the ArrayList implements this interface!

Here's a simple example which demonstrates how to use this interface with an ArrayList implementation:

import java.util.List;
import java.util.ArrayList;

class Numbers {
    List<Integer> numbers;

    Numbers() {
        this.numbers = new ArrayList<>();
    }

    void addNumber(int n) {
        this.numbers.add(n);
    }

    void removeNumber(int index) {
        this.numbers.remove(index);
    }
}
Create a test class and play around with it. Now if for whatever reason, we find that the ArrayList isn't appropriate, we can swap it out for any other class that implements the List interface. We can use a LinkedList instead (don't worry about the internal representation of this data structure, but if you're curious it you can read about it hereLinks to an external site.):

import java.util.List;
import java.util.LinkedList;

class Numbers {
    List<Integer> numbers;

    Numbers() {
        // We just switch out the data structure!
        this.numbers = new LinkedList<>();
    }

    void addNumber(int n) {
        this.numbers.add(n);
    }

    void removeNumber(int index) {
        this.numbers.remove(index);
    }
}
Note that any other code that depends on this class will not even notice the change, even though the attribute is not private! This ability to switch out implementations as needed is very powerful and allows us to make changes even within large code bases. 



We saw in the previous page that the ArrayList is a particular implementation of the List interface. Similarly there exists in the JCF, a Map interface, of which HashMap provides an implementation.

The Map interface
The Map interface represents a collection of key-value pairs, where each key is associated with exactly one value. Here is a list of important methods specified by this interface (note how many should be familiar to you through your use of the HashMap):

put(K key, V value): Associates the specified value with the specified key.
get(Object key): Returns the value to which the specified key is mapped or null if this map contains no mapping for the key.
remove(Object key): Removes the mapping for the specified key if present.
containsKey(Object key): Returns true if this map contains a mapping for the specified key.
containsValue(Object value): Returns true if this map maps one or more keys to the specified value.
size(): Returns the number of key-value mappings.
isEmpty(): Returns true if this map contains no key-value mappings.
clear(): Removes all of the mappings.
entrySet(): Returns a Set view of the key-value mappings.
keySet(): Returns a Set view of the key contained in the map.
So what's the point of this interface? Just slot in a HashMap and run with it, right? Well one assumption about the HashMap is that its keys are unordered. Consider a simple HashMap that maps family names to Integers, where we insert alphabetically:

HashMap<String, Integer> m = new HashMap<>();
m.put("Agincourt", 7);
m.put("Bartle", 5);
m.put("Ling", 1);
m.put("Zuma", 5);

for (String familyName : m.keySet()) {
    System.out.println(familyName);
}
The output from running the above is:

Ling
Agincourt
Zuma
Bartle
The main purpose of the HashMap is to have fast insertion and retrieval of values; ordering of keys is not considered. If ordering is important to your program you can replace it with a LinkedHashMap:

import java.util.LinkedHashMap;

// ...

LinkedHashMap<String, Integer> m = new LinkedHashMap<>();
m.put("Agincourt", 7);
m.put("Bartle", 5);
m.put("Ling", 1);
m.put("Zuma", 5);

for (String familyName : m.keySet()) {
    System.out.println(familyName);
}
Note in the above code that the keySet() method returns just the keys in the map. Running this code results in:

Agincourt
Bartle
Ling
Zuma
The LinkedHashMap preserves insertion order, so if we inserted the "Zuma" entry first, it would be the first key to be returned from keySet().


Readings
We've now seen the List and Map interfaces from the JCF. They allow us to write more robust code, so that we can swap out implementations for these interfaces as needed. But there is another big benefit to using these interfaces. In this section we introduce the CollectionsLinks to an external site. class from the JCF. It consists purely of static methods that allow us to efficiently search, sort, shuffle and reverse any List implementations. In addition there are some useful methods that work any any general Java collection.

 

Operations on List collections
Sorting a List
The Collections.sort() method will automatically reorder the elements of a List in ascending order from smallest-to-largest:

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CollectionsTest {
    public static void main(String[] args) {

        Integer[] ints = { 5, 1, 0, -1, 54, 1002, 4, 5 };
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(ints));
        System.out.println("Before sorting ------");
        System.out.println(arrayList);

        Collections.sort(arrayList);

        System.out.println("After sorting ------");
        System.out.println(arrayList);
    }
}
Shuffling a List
There are times when you would like to randomise the order of a List e.g. to shuffle playback of your playlist in your music app. The Collections.shuffle() method does this for you:

// Create an array with elements from 0 to 19
Integer[] ints = new Integer[20];
for (int i = 0; i < ints.length; i++) {
    ints[i] = i;
}

ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(ints));
System.out.println("Before shuffling: " + arrayList);

Collections.shuffle(arrayList);
System.out.println("Shuffled: " + arrayList);
The output will look something like this:

Before shuffling: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
Shuffled: [1, 15, 4, 5, 0, 8, 9, 2, 19, 16, 18, 14, 11, 3, 6, 17, 10, 12, 7, 13]
Reversing a List
To reverse a List use Collections.reverse():

Integer[] ints = new Integer[20];
for (int i = 0; i < ints.length; i++) {
    ints[i] = i;
}

ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(ints));
System.out.println("Before: " + arrayList);

Collections.reverse(arrayList); // Reverse

System.out.println("Reversed: " + arrayList);
The output will be:

Before: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
Reversed: [19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
Exercise: Modify the above code to use ArrayLists of Strings. You'll find that it works seamlessly.

Searching for an element in a List
We can also use the Collections class to search for elements in a List, with the Collections.binarySearch() method. Important note: this method only works for a sorted List!

Integer[] ints = { 1, 3, 5, 6, 10, 200, 271, 340, 2000, 10000 };
ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(ints));

int value = 271;
System.out.println(arrayList);
int index = Collections.binarySearch(arrayList, value);
System.out.println(value + " is at index " + index);
The output is:

[1, 3, 5, 6, 10, 200, 271, 340, 2000, 10000]
271 is at index 6
Exercise: Look at what happens if the List is not-ordered.

Sorting non-primitive elements
All the examples we've seen so far have involved simple Integer types, which isn't too interesting. It turns out that all the usual types associated with the language such as Integer, Float, Double, Boolean and String have built-in implementations of order (for those curious these types implement the ComparableLinks to an external site. interface). Now it'd be really neat if we could define orders on our own types, and we can indeed do so! Consider a Student class:

class Student {
    double averageMark;
    String firstName, lastName;

    Student(String firstName, String lastName, double averageMark) {
        this.averageMark = averageMark;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.lastName + ", " + this.firstName + ", average mark: " + this.averageMark;
    }
}
To be able to sort a List of Students, we need to step back and think about what makes one Student object equal/less than/greater than another Student object. Suppose we have the following student names:

Ling, Timothy (so lastName == "Ling" and firstName == "Timothy")
McGregor, Conor
Swift, Taylor
Ling, David
There are many ways to define such an order. A common one is to first sort by lastName, then firstName. Assuming we sort individual names alphabetically (so that "Bob" is deemed less than "Chris", since the letter B comes before C), this is the sorted sequence of students:

Ling, David
Ling, Timothy (same last name as above, but "David" < "Timothy")
McGregor, Conor
Swift, Taylor
So how can we do this in Java? By implementing the ComparatorLinks to an external site. interface, which is part of the JCF. There's a lot to be said about these interfaces, but here we'll focus on the practicalities of creating Comparators for our own classes. The approach described here requires us to implement accessor methods for the attributes that will participate in the sorting. So for this example we need getLastName() and getFirstName(). Add the following to the Student class:

String getLastName() {
    return this.lastName;
}

String getFirstName() {
    return this.firstName;
}
Here's how to create the Comparator. Add the following to the Student class:

static final Comparator<Student> comparator = 
    Comparator.comparing(Student::getLastName)       // First sort by lastName
              .thenComparing(Student::getFirstName); // Then sort by firstName
The notation Student::getLastName is called a method reference. The  comparing() and thenComparing() methods of Comparator needs a reference to the accessor methods to determine ordering of Student objects. Here's how we can sort an ArrayList of Student objects (don't forget to add import java.util.Comparator; to the top of your Java file):

Student s0 = new Student("Taylor", "Swift", 67);
Student s1 = new Student("Conor", "McGregor", 90);
Student s2 = new Student("Timothy", "Ling", 71.1);
Student s3 = new Student("David", "Ling", 91.1);

Student[] array = { s0, s1, s2, s3 };
ArrayList<Student> arrayList = new ArrayList<>(Arrays.asList(array));

// IMPORTANT: the sort method requires the comparator
Collections.sort(arrayList, Student.comparator);

for (Student s : arrayList) {
    System.out.println(" - " + s);
}
Note that since the comparator attribute is marked as static, we can reference it from the class name Student directly. The output is in the sorted order that we expect:

 - Ling, David, average mark: 91.1
 - Ling, Timothy, average mark: 71.1
 - McGregor, Conor, average mark: 90.0
 - Swift, Taylor, average mark: 67.0
Note that by default, sorting is performed in ascending order. You can reverse it by simply adding .reversed() immediately after a call to comparing() or thenComparing() call as follows:

static final Comparator<Student> comparator = Comparator
        .comparing(Student::getLastName).reversed() // First sort by lastName in DESCENDING order
        .thenComparing(Student::getFirstName);      // Then sort by firstName in ascending order
This gives the following result:

 - Swift, Taylor, average mark: 67.0
 - McGregor, Conor, average mark: 90.0
 - Ling, David, average mark: 91.1
 - Ling, Timothy, average mark: 71.1
Exercise: modify the comparator above to first sort by averageMark in descending order (largest-to-smallest). Then sort like usual with lastName then firstName.

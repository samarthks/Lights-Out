public class QueueImplementation<E> implements Queue<E> {


  private static class Elem<T> {
    private T value;


    private Elem<T> next;

    private Elem(T value, Elem<T> next) {
      this.value = value;
      this.next = next;
    }
  }


  private Elem<E> front;


  private Elem<E> rear;

  private int size;

  public QueueImplementation() {
    front = rear = null;
  }

  public boolean isEmpty() {
    return front == null;
  }

  public void enqueue(E newElement) {
    if(newElement == null) {
      throw new NullPointerException("No null references in my queue!");
    }
    Elem<E> newElem = new Elem<E>(newElement, null);
    if (isEmpty()) {
      front = newElem;
      rear = newElem;
    } else {
      rear.next = newElem;
      rear = newElem;
    }
    size++;
  }

  public E dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Dequeue method called on an empty queue.");
    }
    Elem<E> temp;
    temp = front;
    front = front.next;
    if (front == null) {
      rear = null;
    }
    size--;
    return temp.value;
  }


  public int size() {
    return size;
  }
}

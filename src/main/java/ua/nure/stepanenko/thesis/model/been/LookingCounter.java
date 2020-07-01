package ua.nure.stepanenko.thesis.model.been;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
public class LookingCounter implements Serializable {

    private String looking;
    private AtomicInteger count = new AtomicInteger(1);

    public String getLooking() {
        return looking;
    }

    public void setLooking(String looking) {
        this.looking = looking;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    @Override
    public String toString() {
        return "looking - " + looking +
                ", count - " + count;
    }
}

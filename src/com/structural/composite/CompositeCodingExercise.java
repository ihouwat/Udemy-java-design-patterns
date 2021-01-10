// Exercise for composite section -

package structural.composite;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

interface ValueContainer extends Iterable<Integer> {}

class SingleValue implements ValueContainer
{
    public int value;

    // please leave this constructor as-is
    public SingleValue(int value)
    {
        this.value = value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Collections.singleton(value).iterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer
{
}


class MyList extends ArrayList<ValueContainer>
{

    // please leave this constructor as-is
    public MyList(Collection<? extends ValueContainer> c)
    {
        super(c);
    }

    public int sum()
    {
        int sum = 0;
        for (ValueContainer element: this) {
            for (int num : element)
            {
                sum = sum + num;
            }
        }
        return sum;
    }
}

class CompositeExerciseDemo
{
    public static void main(String[] args) {
        SingleValue value = new SingleValue(1);
        SingleValue value1 = new SingleValue(5);
        SingleValue value2 = new SingleValue(10);
        ManyValues list = new ManyValues();
        list.addAll(Arrays.asList(30, 2, new SingleValue(1).value));

        MyList compositeList = new MyList(List.of(value, value1, value2, list));
        System.out.println(compositeList.sum());
    }
}

// Monostate

package creational.singleton;

class ChiefExecutiveOfficer
{
    // Monostate by adding static modifier
    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ChiefExecutiveOfficer.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        ChiefExecutiveOfficer.age = age;
    }

    @Override
    public String toString() {
        return "ChiefExecutiveOfficer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class MonostateSingletonDemo
{
    public static void main(String[] args) {
        {
            ChiefExecutiveOfficer ceo1 = new ChiefExecutiveOfficer();
            ceo1.setName("Adam Smith");
            ceo1.setAge(55);

            ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
            System.out.println(ceo2); // object is initialized with ceo1 info
        }
    }
}

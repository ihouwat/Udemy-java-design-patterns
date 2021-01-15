/*
Exercise for null object section

Null Object Coding ExerciseIn this example, we have a class Account  that is very tightly coupled
to a Log... it also breaks SRP by performing all sorts of weird checks in someOperation().

Your mission, should you choose to accept it, is to implement a NullLog  class that can be fed into
an Account and that doesn't throw any exceptions.
 */

package behavioral.nullobject.exercise;


interface Log
{
    // max # of elements in the log
    int getRecordLimit();

    // number of elements already in the log
    int getRecordCount();

    // expected to increment record count
    void logInfo(String message);
}

class Account
{
    private Log log;

    public Account(Log log)
    {
        this.log = log;
    }

    public void someOperation() throws Exception
    {
        int c = log.getRecordCount();
        log.logInfo("Performing an operation");
        if (c+1 != log.getRecordCount())
            throw new Exception();
        if (log.getRecordCount() >= log.getRecordLimit())
            throw new Exception();
    }
}

class NullLog implements Log
{
    private int recordCount = 0;

    @Override
    public int getRecordLimit()
    {
        return Integer.MAX_VALUE;
    }
    @Override
    public int getRecordCount()
    {
        return recordCount;
    }
    @Override
    public void logInfo(String message)
    {
        recordCount++;
    }
}

class NullObjectExerciseDemo
{
    public static void main(String[] args) throws Exception
    {
        NullLog log = new NullLog();
        Account account = new Account(log);

        account.someOperation();
        account.someOperation();
    }
}
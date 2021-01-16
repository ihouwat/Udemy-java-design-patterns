/*
Exercise for state pattern section:

A combination lock is a lock that opens after the right digits have been entered.
A lock is preprogrammed with a combination(e.g.,12345)
and the user is expected to enter this combination to unlock the lock.

The lock has a status field that indicates the state of the lock. The rules are:

• If the lock has just been locked (or at startup), the status is LOCKED.
• If a digit has been entered, that digit is shown on the screen.
As the user enters more digits, they are added to status.
• If the user has entered the correct sequence of digits, the lock status changes to OPEN.
• If the user enters an incorrect sequence of digits, the lock status changes to ERROR.

Please implement the CombinationLock  class to enable this behavior.
Be sure to test both correct and incorrect inputs.

 */

package behavioral.state;

class CombinationLock
{
    public int [] combination; // array of ints
    public String status;
    public int digitsEntered;

    public CombinationLock(int[] combination)
    {
        this.combination = combination;
        reset();
    }

    public void reset()
    {
        this.status = "LOCKED";
        digitsEntered = 0;
    }

    public void enterDigit(int digit)
    {
        if(status.equals("LOCKED")) status = "";
        status += digit; // add digit to status
        // go to index in combinations array - if numbers don't match, fail
        if(combination[digitsEntered] != digit) {
            status = "ERROR";
            return;
        }
        // increment digits counted if entered digit is correct
        digitsEntered++;
        // if numbers match and is last digit, OPEN safe
        if(digitsEntered == combination.length) {
            status = "OPEN";
        }
    }
}

class StateExerciseDemo
{
    public static void main(String[] args) {
        CombinationLock cl = new CombinationLock(new int[]{1, 2, 3, 4});
        cl.enterDigit(1);
        cl.enterDigit(2);
        cl.enterDigit(3);
        cl.enterDigit(5);
        System.out.println(cl.status);
    }

}
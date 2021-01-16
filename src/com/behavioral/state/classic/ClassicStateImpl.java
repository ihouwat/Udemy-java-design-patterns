// State pattern: classic Gang of Four implementation - simulating light switch

package behavioral.state.classic;

class State
{
    void on(LightSwitch ls)
    {
        System.out.println("Light is already on...");
    }

    void off(LightSwitch ls )
    {
        System.out.println("Light is already off...");
    }
}

class OnState extends State
{
    public OnState()
    {
        System.out.println("Light turned on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Switching light off...");
        ls.setState(new OffState());
    }
}

class OffState extends State
{
    public OffState()
    {
        System.out.println("Light turned off");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Switching light on...");
        ls.setState(new OnState());
    }
}

class LightSwitch
{
    private State state; // OnState or OffState

    public LightSwitch()
    {
        state = new OffState();
    }

    public void setState(State state) {
        this.state = state;
    }

    void on() { state.on(this); }
    void off() { state.off(this); }
}

class ClassicStateDemo
{
    public static void main(String[] args)
    {
        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off();
    }
}
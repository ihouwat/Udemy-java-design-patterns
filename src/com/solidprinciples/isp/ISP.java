// ISP

package solidprinciples.isp;

import javax.print.Doc;

class Document {

}

interface Machine {
    void print(Document d);
    void fax(Document d);
    void scan(Document d);
}

class MultiFunctionPrinter implements Machine {
    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void fax(Document d) {
        //
    }

    @Override
    public void scan(Document d) {
        //
    }
}

class OldFashionedPrinter implements Machine {
    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void fax(Document d) {
        // cannot implement fax with old fashioned printers. What to do?
        // Try throwing new exception
        // throw new Exception();

        // Problem is that you need to add that to method signature and it
        // also propagates to interface
    }

    @Override
    public void scan(Document d) {
        // cannot implement fax with old fashioned printers. What to do?
    }
}

// ISP Approach here

// Segregate/split Machine interface
interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

// YAGNI = You Ain't Going to Need It

class JustAPrinter implements Printer {
    @Override
    public void print(Document d) {
        //
    }
}

// One way to implement multiple interfaces
class Photocopier implements Printer, Scanner {
    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void scan(Document d) {
        //
    }
}

// Another approach is to create an interface that extends the simpler interfaces
interface MultiFunctionDevice extends Printer, Scanner {

}

class MultiFunctionMachine implements MultiFunctionDevice {

    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print (d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}

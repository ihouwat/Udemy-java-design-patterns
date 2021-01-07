package creational.builder;

// Exercise: You are asked to implement the Builder design pattern for rendering simple chunks of code.

import java.util.ArrayList;
import java.util.List;

class Field
{
    public String name, type;
    public Field(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

class CodeBuilder
{
    public String className, name, type;
    private List<Field> fields = new ArrayList<>();

    public CodeBuilder(String className)
    {
        this.className = className;
    }

        public CodeBuilder addField(String name, String type)
    {
        fields.add(new Field(name, type));
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("public class %s\n", this.className));
        sb.append("{\n");
        for (Field f : fields)
            sb.append(String.format("  public %s %s;\n",f.type, f.name));
        sb.append("}");
        return sb.toString();
    }

}

class CodeBuilderDemo {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person")
            .addField("name", "String")
            .addField("age", "int");
        System.out.println(cb);
    }
}
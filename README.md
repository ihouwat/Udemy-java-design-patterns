# Design Patterns in Java
The files in this repository are based on the material and exercises from the Udemy course, [Design Patterns in Java](https://www.udemy.com/course/design-patterns-java/) by [Dmitri Nesteruk](https://www.udemy.com/user/dmitrinesteruk/).
## SOLID Design Principles 
- [Single Responsibility Principle (SRP)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/solidprinciples/srp/SRP.java)
- [Open-Closed Principle (OCP)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/solidprinciples/ocp/OCP.java)
- [Liskov Substitution Principle (LSP)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/solidprinciples/lsp/LSP.java)
- [Interface Segregation Principle (ISP)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/solidprinciples/isp/ISP.java)
- [Dependency Inversion Principle (DIP)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/solidprinciples/dip/DIP.java)
## Creational Patterns
### Builder
- [Builder](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/builder/Builder.java)
- [Fluent Builder Inheritance with Recursive Generics](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/builder/RecursiveGenerics.java)
- [Faceted Builder](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/builder/facets/BuilderFacets.java)
- [Exercise - CodeBuilder](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/builder/CodeBuilder.java)
### Factory
- [Factory Method](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/factories/FactoryMethod.java)
- [Factory Class](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/factories/Factory.java)
- [Abstract Factory](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/factories/AbstractFactory.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/factories/FactoryCodingExercise.java)
### Prototype
- [Cloneables example - avoid this](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/prototype/cloneable/CloneablesBad.java)
- [Copy Constructors](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/prototype/CopyConstructors.java)
- [Copy Through Serialization](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/prototype/CopyThroughSerialization.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/prototype/PrototypeCodingExercise.java)
### Singleton
- [Basic Singleton](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/singleton/BasicSingleton.java)
- [Static Block Singleton](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/singleton/StaticBlockSingleton.java)
- [Lazy Singleton / Inner Static Singleton](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/singleton/LazySingleton.java)
- [Enum Based Singleton](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/singleton/EnumBasedSingleton.java)
- [Monostate](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/singleton/MonostateSingleton.java)
- [Multiton](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/singleton/Multiton.java)
- [Singleton Testability Problems](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/singleton/SingletonTestability.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/creational/singleton/SingletonCodingExercise.java)
## Structural Patterns
### Adapter
- [Adapter](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/adapter/Adapter.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/adapter/AdapterCodingExercise.java)
### Bridge
- [Bridge](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/bridge/Bridge.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/bridge/exercise/BridgeCodingExercise.java)
### Composite
- [Composite: Geometric Shapes](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/composite/CompositeGeometricShapes.java)
- [Composite: Neural Networks](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/composite/CompositeNeuralNetworks.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/composite/CompositeCodingExercise.java)
### Decorator
- [String class Decorator](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/decorator/StringDecorator.java)
- [Dynamic Decorator](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/decorator/dynamic/DynamicDecorator.java)
- [Static Decorator](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/decorator/statik/StaticDecorator.java)
- [Adapter-Decorator](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/decorator/adapter/AdapterDecorator.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/decorator/DecoratorCodingExercise.java)
### Facade
- [Facade](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/facade/Facade.java)
### Flyweight
- [Duplicate Users](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/flyweight/Users.java)
- [Text Formatting](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/flyweight/TextFormatting.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/flyweight/FlyeweightCodingExercise.java)
### Proxy
- [Protection Proxy](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/proxy/ProtectionProxy.java)
- [Property Proxy](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/proxy/PropertyProxy.java)
- [Dynamic Proxy for Logging](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/proxy/DynamicLoggingProxy.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/structural/proxy/exercise/ProxyCodingExercise.java)
## Behavioral Patterns
### Chain of Responsibility
- [Method Chain](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/chainofresponsibility/MethodChain.java)
- [Broker Chain](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/chainofresponsibility/BrokerChain.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/chainofresponsibility/exercise/CORCodingExercise.java)
### Command
- [Command](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/command/Command.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/command/CommandCodingExercise.java)
### Interpreter
- [Interpreter](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/interpreter/Interpreter.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/interpreter/exercise/InterpreterCodingExercise.java)
- [Exercise for this section (refactored)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/interpreter/exercise/InterpreterExerciseRefactor.java)
### Iterator
- [Tree Traversal (using Iterable and Iterator interfaces)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/iterator/TreeTraversal.java)
- [Array-Backed Properties](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/iterator/ArrayBackedProperties.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/iterator/IteratorCodingExercise.java)
### Mediator
- [Chat Room example](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/mediator/MediatorChatRoom.java)
- [Event Broker with Reactive Extensions](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/mediator/ReactiveExtensionsEventBroker.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/mediator/MediatorCodingExercise.java)
### Memento
- [Memento](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/memento/Memento.java)
- [Exercise for this section - also uses Prototype pattern](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/memento/MementoCodingExercise.java)
### Null Object
- [Basic Null Object](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/nullobject/NullObject.java)
- [Dynamic Null Object](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/nullobject/dynamicnullobject/DynamicNullObject.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/nullobject/exercise/NullObjectCodingExercise.java)
### Observer
- [Observer and Observable](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/observer/ObserverAndObservable.java)
- [Event Class Approach](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/observer/events/HandmadeEvents.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/observer/ObserverCodingExercise.java)
- [Instructor solution to exercise](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/observer/exerciseinstructorsolution/ObserverExerciseInstructorSolution.java)
### State
- [Classic GoF Implementation](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/state/classic/ClassicStateImpl.java)
- [Handmade State (more modern)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/state/handmade/Handmadestate.java)
- [Spring Framework State Machine](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/state/spring/SpringStateMachine.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/state/StateCodingExercise.java)
### Strategy
- [Dynamic and Static Implementations](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/strategy/DynamicAndStaticStrategy.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/strategy/StrategyCodingExercise.java)
### Template
- [Template method](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/template/Template.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/template/TemplateCodingExercise.java)
### Visitor
- [Intrusive Visitor](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/visitor/intrusive/IntrusiveVisitor.java)
- [Reflective Visitor](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/visitor/reflective/ReflectiveVisitor.java)
- [Classic Visitor (Double Dispatch)](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/visitor/classic/ClassicVisitor.java)
- [Acyclic Visitor](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/visitor/acyclic/AcyclicVisitor.java)
- [Exercise for this section](https://github.com/ihouwat/Udemy-java-design-patterns/blob/master/src/com/behavioral/visitor/exercise/VisitorCodingExercise.java)
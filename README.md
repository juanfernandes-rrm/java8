# Java 8
Código de estudo de APIs do Java 8

## Conteúdo
- Default Methods
- Lambdas
- Method References
- Interface Function
- Streams
- Optional

## Default Methods

Com o Java 8, foi adicionado os Default Methods, que permitem que uma Interface adicione um novo método, sendo este concreto. 
Isso possibilitou que uma Interface possa evoluir sem quebrar compatibilidade.

## Lambdas

Para simplificar os códigos, foi introduzido os lambdas.

- forEach com classe anônima:
```java
palavras.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
 ```

- forEach com Lambda:
```java
palavras.forEach(s -> System.out.println(s));
```

Através da dedução feita pelo compilador, é possível simplificar o código. Neste exemplo acima, o método o ***forEach()*** recebe um ***Consumer*** que só fornece o método ***accept().*** 

```java
default void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    for (T t : this) {
        action.accept(t);
    }
}
```

*(As interfaces que só tem um método abstrato, são chamadas de **Funcional Interface**.)*

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

Sabendo que o ***forEach()*** só aceita um ***Consumer*** e que este só tem o método ***accept()***, o compilador induz tudo isso, tornando redundante passar uma classe anônima como parâmetro. 

Ao invés disso, é passado um lambda, como se fosse a implementação do método accept(), a cada argumento é feito um comando.

## Method References

O Method References é uma maneira de escrever ***lambdas*** com o objetivo de deixar o código mais legível, já que os tipos ficam mais evidentes.

No caso abaixo, o objeto está invocando um método de sua classe.

```java
//lambda
palavras.sort(Comparator.comparing(s -> s.length()));
//lambda com method references
palavras.sort(Comparator.comparing(String::length));
```

Entretanto, também é possível que este objeto seja passado como argumento de um método de outra classe, como no exemplo abaixo.

```java
//lambda
palavras.forEach(s -> System.out.println(s));
//lambda com method references
palavras.forEach(System.out::println);
```

## Interface Function

Esta interface é uma ***Funcional Interface,*** ou seja, só tem um método abstrato (***apply***). Na sua declaração, ela recebe duas classes, sendo a primeira a Classe que será passada como argumento do método apply e a segunda o será retornado.

```java
//Fuctional Interface
Function<String, Integer> function = new Function<String, Integer>() {
    @Override
    public Integer apply(String s) {
        return s.length();
    }
};
```

## Streams

Stream é um fluxo de objetos. Não é uma collection, não altera a coleção original.

Através do Stream, temos vários métodos para manipular esse fluxo de objetos.

```java
cursos.stream()
        .filter(c -> c.getAlunos() >= 100) //filtra cursos com numero de alunos >= 100
        .map(Curso::getAlunos)//faz um map do numero de alunos
        .forEach(System.out::println); //imprime o numero de alunos
```

Também existem Stream para cada tipo primitivo, que fornecem métodos específicos para manipular esses tipos de dados.

```java
int sum = cursos.stream()
        .filter(c -> c.getAlunos() >= 100)
        .mapToInt(Curso::getAlunos)//retorna um IntStream
        .sum();
```

Se for necessário coletar os dados de um Stream para uma Collection, existe o método collect() que retorna uma collection de acordo com o parâmetro recebido.

```java
cursos = cursos.stream()
        .filter(c -> c.getAlunos() >= 100)
        .collect(Collectors.toList()); //Retorna uma lista

Map<String, Integer> collect = cursos.stream()
                .filter(c -> c.getAlunos() >= 100)
                .collect(Collectors.toMap(Curso::getNome, Curso::getAlunos));
```

## Optional

Uma nova classe do Java que permite trabalhar com referencias sem precisar criar ***ifs*** para checar se o objeto é nulo ou precisar tratar exceções. Além de oferecer diversos métodos que podem tirar proveito dos lambdas.

```java
cursos.stream()
        .filter(c -> c.getAlunos() >= 100)
        .findAny(). //retorna um Optional
        ifPresent(curso -> System.out.println(curso.getNome())); //Realiza uma ação se o objeto for não-nulo.
```

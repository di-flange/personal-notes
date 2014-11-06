=== Основные концепции ===

==== Текущее область имён ====

package ee.jiss.namespace;
...


...
namespace JissNamespace
{
...
}

При этом в Java, package это ещё и папка в которой находится фаил. Тоесть класс
всегда должен находится в той же папке что и его область имён в данном примере
ee/jiss/namespace/MyClass.java.

==== Использование объектов из других области имён ====

import ee.jiss.MyObject;

using Jiss.MyObject;

==== Мета информация ====

[AttributeName]

@annotationName

Дополнительные параметры передаются в анатоцию в скобках:

@annotationName(parameter = "Value")
@annotationName(parameter = { "Value1", "Value2"})

Передавать можно только статические значения.

==== Обращение к наследуемому классу ====

super.methodName;

base.MethodName; 	 	 

==== Не изменяемые элементы ====

public final class ClassName { }
public final void methodName() { }

sealed class ClassName { }
sealed public void MethodName() { }

==== Константы ====

public final int CONST_NAME = 5;

public const int contName = 5;

==== Enum тип ====

public enum EnumName { Value1, Value2, Value3; }

enum EnumName { Value1, Value2, Value3 };

Разница в том, что в Java enum надо объявлять, как класс в отдельном файле. 

==== Перегрузка ====

@Override
public void methodName() { }

public override void methodName() { }

==== Сравнивание объектов ====

Сравнение по ссылке:
object1 == object2

Object.ReferenceEquals(object1, object2)

Сравнение по значением:

object1.equals(object2)


object1 == object2
Object.Equals(object1, object2)
	
Сравнение через == тоже возможно в Java, но только для элементарных типо, не
являющихся объектами.

==== Получение типа класса ====

object1.class

Object.GetType(object1)

==== Работа с исключением ====

Декларация возможности появления исключений в методах:

public void methodName() throws ExceptionName { } 

public implicit operator methodName() { }

==== Конкурентый доступ ====

Блокировка доступа для других потоков на время выполнения:

public synchronized void methodName() { }

synchronized(object1) {

}

lock(object1) {

}


==== Итерации ====

for (ObjectClass object1 : collection1)

foreach(ObjectClass object1 in collection1)

==== Лямда и замыкания ====

object1.methodName(x => x * x);

object1.methodName(new Function<Integer, Integer>()
    {
        @Override
        public Integer methodName(Integer x) {
            return x * x;
        }
    });


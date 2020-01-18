## Постановка задачи
Для файла data.xml выполнить следующие задания:

1.Необходимо сформировать коллекцию, содержащую все виды документов в отсортированном порядке.

2.Вывести имена и значения всех атрибутов для par step="1" name="ГРАЖДАНСТВО"

3.Задача со звездочкой: создать в базе таблицу-справочник со значениями из первой части

## Запуск проекта

Для сборки потребуется Maven. Откройте проект в своей IDE и запустите тесты. 

## Описание классов проекта


1.Класс для связи записи в базе данных и объектов в программе.

```
public class DocumentType 
```

2.Класс отвечающий за извлечение данных из XML.

```
public class XmlDataExtractor
```

3.Сервис для сохранения и получения данных избазы данных.

```
public class DocumentTypeService
```

3.Интерфес репозитория для взаимодействия с базой данных.

```
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long>
```

## Описание тестов

####Тесты для проверки взаимодействия с базой данных.

1.Тест для проверки сохраняется ли значения в базу данных.

```
public void saveTest()
```

2.Тест проверяет дублируются ли одинаковые записи при нескольких сохранениях.

```
public void saveTwoTimesTest()
```

3.Тест в котором выполняется задание 3.

```
public void saveAndCheckDocumentTypeInDb()
```



####Тесты для задания 1 и проверки различных ситуаций которые могут возникнуть.

1.Тест для проверки правильности выполнения задания 1.

```
public void listTest()
```

2.Тест результата при отсутствии списка документов в файле.

```
 public void withoutDataListTest() 
```

3.Тест результата при неправильной структуре тэгов.

```
  public void invalidXmlListTest() throws SAXException
```

4.Тест результата при неправильном пути к файлу.

```
  public void badXmlPathListTest() throws IOException
```

####Тесты для задания 2 и проверки различных ситуаций которые могут возникнуть.

1.Тест проверяет правильность извлечения атрибутов.

```
 public void testExtractingAttributeMap()
```

2.Тест проверяет извлечение атрибутов при неправильной структуре файла.

```
public void testExtractingAttributeMapFromInvalidXml() throws SAXException 
```

3.Тест проверяет правильность работы программы при отсутствии атрибутов.

```
public void testExtractingEmptyAttributeMap()
```

4.Выполнение вывода для задания 2.

```
public void testPrintingMapValues()
```


## Сделано с помощью

* [SpringBoot](https://spring.io/) - SpringBoot framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2](http://h2database.com/html/main.html) - Database


## Автор

* **Любезников Николай**  



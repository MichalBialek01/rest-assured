## Overview of Groovy and GPath for Json in Rest assured

Co to jest GPath - język to język wyrażeń, który służy do łatwego nawigowania po zagnieżdżonych strukturach danych, takich jak XML, JSON czy złożone obiekty w języku Groovy.

Możliwości GPath:

- Nawigacja po hierarchiach - dostęp do elementów danych w strukturach hierarchicznych
- Prosty dostęp do zagnieżdżonych elementów
- Filtracja danych

### Przykład JSON 
```json
{
  "store": {
    "book": [
      {
        "title": "The Lord of the Rings",
        "author": {
          "firstName": "J.R.R.",
          "lastName": "Tolkien"
        },
        "category": "fiction",
        "price": 22.99,
        "availability": {
          "inStock": true,
          "quantity": 12
        }
      },
      {
        "title": "Harry Potter",
        "author": {
          "firstName": "J.K.",
          "lastName": "Rowling"
        },
        "category": "fiction",
        "price": 25.99,
        "availability": {
          "inStock": true,
          "quantity": 7
        }
      },
      {
        "title": "Clean Code",
        "author": {
          "firstName": "Robert",
          "lastName": "Martin"
        },
        "category": "non-fiction",
        "price": 32.99,
        "availability": {
          "inStock": false,
          "quantity": 0
        }
      },
      {
        "title": "The Pragmatic Programmer",
        "author": {
          "firstName": "Andy",
          "lastName": "Hunt"
        },
        "category": "non-fiction",
        "price": 39.99,
        "availability": {
          "inStock": true,
          "quantity": 5
        }
      }
    ],
    "bicycle": {
      "color": "red",
      "price": 19.99
    }
  }
}

```

### Pobranie wszystkich książek
```groovy
@Test
public void testAllBookTitles() {
    given()
    .when()
        .get("/store")
    .then()
        .body("store.book.title", hasItems("The Lord of the Rings", "Harry Potter", "Clean Code", "The Pragmatic Programmer"));
}
```
Łatwo odwołujemy się do pola poprzez: "store.book.title"

### Sprawdzenie, czy wszystkie książki są dostępne w magazynie
```groovy
@Test
public void testBooksInStock() {
    given()
    .when()
        .get("/store")
    .then()
        .body("store.book.findAll { it.availability.inStock == true }.title", hasItems("The Lord of the Rings", "Harry Potter", "The Pragmatic Programmer"))
        .body("store.book.findAll { it.availability.inStock == false }.title", hasItems("Clean Code"));
}
```

W tym przypadku stosujemy warunek. Opertor "it" działa jak "this" w java

### Większa ilość warunków 
```groovy
        .body("store.book.findAll { it.category == 'fiction' && it.price < 30.00 }.title", hasItems("The Lord of the Rings", "Harry Potter"));

```
### Najważniejsze operatory 
* it: domyślny identyfikator dla elementu kolekcji.
* find: zwraca pierwszy element spełniający warunek.
* findAll: zwraca wszystkie elementy spełniające warunek.
* collect: mapuje elementy kolekcji na nową kolekcję.
* any: zwraca true, jeśli co najmniej jeden element spełnia warunek.
* every: zwraca true, jeśli wszystkie elementy spełniają warunek.
* inject: agreguje wartości kolekcji według zadanego sposobu.

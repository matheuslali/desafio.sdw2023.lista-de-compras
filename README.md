# Desafio Santander Dev Week 2023
Java RESTful API criada para o desafio de codigo da DIO para o Santander Dev Week 2k23

## Diagrama de classes
 ``` mermaid

classDiagram
  class User {
    +id: Long
    +username: String    
    +email: String
  }

  class ShoppingList {
    +id: Long
    +name: String
    +description: String
    +user: User
  }

  User "1" *-- "N" ShoppingList

```



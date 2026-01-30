# GTManager

## Description
A data management system for multiple households, together with their members. Allows for observing record changes _in time_.

## Goals

* storage of personal data
* storage of household data (surface, material goods, etc.)
* saving previous records of changed data
* multiple users

### To do / fix:
* add search bars
* degrees can't be awarded in the future
* perfect authentication system
* IDs in temporal tables may point to inexistent households or people
* the state of a person's degrees can't be viewed as it was in the past
* possible optimization: service classes could handle exceptions in one place, not in each method individually
* CNPs must be unique
* data backup capabilities

* abstract controllers to one operational model
* custom exception classes

## Technical details

| Trait | Value |
|-------|-------|
| architecture | MVCS  |
| database | JDBC  |

### Database schema
![gtmanager.png](gtmanager.png)

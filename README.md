# Alifba Eksmo Parser (2020)

## 1. Foreword

This is one of the internal projects which has been made during working on online store [alifba.ru](https://alifba.ru)
(see also https://dipech.github.io/#/portfolio/alifba).
The project itself isn't ideal, it hasn't any tests, it wasn't written using TDD. I was creating it in my spare time, 
and it isn't a crucial part of the Alifba project.

> This is a copy of the original repository made 10th May 2020 to show how to build a production-ready 
> console application using Spring Boot. 

## 2. Project description

I've created a very flexible and robust importing tool inside Alifba admin panel. This tool allows you to import 
products and their characteristics from YML files in a very easy manner.
All the products suppliers gave us YML files except one – Eksmo. But they provided us their products API. So we could
make API calls to fetch the data we needed. 

## 3. Why separate app you might ask?

Our production server is not so powerful as it might be. Moreover, I don't want to change anything only for one supplier.
PHP isn't a good solution for this kind of task as well.

There're some difficulties:
- API provides lots of paginated XMLs.
- There's no Catalogue entity at all but lots of strange entities like Niches, Segments and Subjects do the same things.
- There're lots of "broken" products with confusing data (linked to unknown manufacturers, etc).
- We need only specific manufacturers' products and only from specific sections.
- The total size of data (hundreds of files) is about 3 GB, but in fact, my script reduces it into one single file 50 MB. 
- Lots more troubles...

## 4. How it works

It works the following way:
- Download all the data using eksmo's API.
- Parse downloaded data, preprocess and filter items.
- Convert the data into one final YML file which can be imported in Alifba website.

> There's also a possibility to build and check some statistics.

## 5. How to build and launch

Please take a look at [readme.dev.md](./readme.dev.md)

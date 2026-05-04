//Получить всех студентов, возраст которых находится между 8 и 9
select * from student where age between 8 and 9

//Получить всех студентов, но отобразить только список их имен
select s.name from student as s

//Получить всех студентов, у которых в имени присутствует буква o
select * from student where name like '%o%'

//Получить всех студентов, у которых возраст меньше идентификатора
select * from student where age < id

//Получить всех студентов упорядоченных по возрасту
select * from student order by age asc
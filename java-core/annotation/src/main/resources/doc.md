# @Retention 
    - позволяет указать жизненный цикл аннотации: будет она присутствовать только в исходном коде, 
      в скомпилированном файле, или она будет также видна и в процессе выполнения. Выбор нужного
      типа зависит от того, как вы хотите использовать аннотацию, например, генерировать что-то 
      побочное из исходных кодов, или в процессе выполнения стучаться к классу через reflection.

# @Target 
    - указывает, что именно мы можем пометить этой аннотацией, это может быть поле, метод, тип и т.д.

# @Documented
    - указывает, что помеченная таким образом аннотация должна быть добавлена в javadoc поля/метода и т.д.

# @Inherited 
    - помечает аннотацию, которая будет унаследована потомком класса, отмеченного такой аннотацией.
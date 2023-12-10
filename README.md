## Electronic Library ##
*Spring MVC*\
*CRUD application* *(REST Pattern)*

### Функционал ###
+ Страницы добавления, изменения и удаления человека.
+ Страницы добавления, изменения и удаления книги.
+ Страница со списком всех людей (люди кликабельные - при клике осуществляется переход на страницу человека).
+ Страница со списком всех книг (книги кликабельные - при клике осуществляется переход на страницу книги).
+ Страница человека, на которой показаны значения его полей и список книг, которые он взял. Если человек не взял ни одной книги, вместо списка текст "Человек пока не взял ни одной книги".
+ Страница книги, на которой показаны значения полей этой книги и имя человека, который взял эту книгу. Если эта книга не была никем взята, текст "Эта книга сейчас свободна, кому назначить её ?".
+ На странице книги, если книга взята человеком, рядом с его именем кнопка "Освободить книгу". Эта кнопка нажимается библиотекарем тогда, когда читатель возвращает эту книгу обратно в библиотеку. После нажатия на эту кнопку книга снова становится свободно и пропадает из списка книг человека.
+ На странице книги, если книга свободна, выпадающий список (<select>) со всеми людьми и кнопка "Назначить книгу". Эта кнопка нажимается библиотекарем тогда, когда читатель хочет забрать эту книгу домой. После нажатия на эту кнопку, книга начинает принадлежать выбранному человеку и появляется в его списке книг.
+ Все поля валидируются - с помощью @Valid, поле ФИО дополнительно Spring Validator

# Черновик!  
  
# Лабораторная работа №2. Activity Lifecycle. Alternative resources.  
## Цели  
* Ознакомиться с жизненным циклом Activity  
* Изучить основные возможности и свойства alternative resources  
  
## Задачи  
### Задача 1. Activity  
#### Задание  
Продемонстрируйте жизненный цикл Activity на любом нетривиальном примере.  
  
#### Пояснения  
* Тривиальными считаются следующие примеры:  
  * Создание/открытие/закрытие приложения  
  * Поворот экрана  
* Не обязательно проходить по всем lifecycle методам  
  
#### Указания  
* Проявите фантазию и деструктивные способности: попробуйте позвонить на устройство, отправить СМС (эмулятор поддерживает все эти опции),  
выключить-включить экран, перезагрузить устройство и т.п.  
* Объясните наблюдаемое поведение в отчете  
* Для решения (только этой) задачи можно объединиться в пары или тройки (в отчете укажите, сколько человек было в группе)  
  
### Задача 2. Alternative Resources  
#### Задание  
Продемонстрируйте работу альтернативного ресурса (тип ресурса согласно варианту) на каком-либо примере.  
  
#### Указания:  
* Изучите альтернативные ресурсы по документации (https://developer.android.com/guide/topics/resources/providing-resources)  
  
### Задача 3. Best-matching resource  
Для заданного набора альтернативных ресурсов, предоставляемых приложением, и заданной конфигурации устройства (оба параметра согласно варианту)  
объясните, какой ресурс будет выбран в конечном итоге. Ответ докажите.  
  
#### Указания:  
* Изучите алгоритм для best-matching resource по документации: https://developer.android.com/guide/topics/resources/providing-resources#BestMatch  
* В отчете приведите аналогичные рассуждения (пошаговое исполнение алгоритма) для ситуации из варианта.  
  
### Задача 4. Сохранение состояние Activity.  
Студент написал приложение: URL. Это приложение (по заданию) должно считать, сколько секунд пользователь провел в этом приложении.  
Найдите ошибки в этом приложении и исправьте их.  
  
#### Указания:  
* Для поиска ошибок запустите приложение на устройстве или эмуляторе и проверьте, что приложение делает то, что он него ожидается (фактически, необходимо  
выполнить ручное тестирование приложения). При тестировании обращайте внимание на правильность обработки lifecycle методов.  
* Для исправления ошибок могут понадобиться методы Activity::onSaveInstanceState/onRestoreInstanceState (https://developer.android.com/guide/components/activities/activity-lifecycle#save-simple,-lightweight-ui-state-using-onsaveinstancestate)  
  
## Отчет  
Отчет должен содержать формулировку целей, решения задач с ответами на дополнительные вопросы из раздела «указания» и выводы. Выводы должны содержать убедительное обоснование, почему автор отчета считает, что все цели были достигнуты.  
  
## Вспомогательные материалы:  
* https://classroom.udacity.com/courses/ud9012 (Lesson 4 “Activity & Fragment Lifecycle”)  
* https://developer.android.com/guide/components/activities/activity-lifecycle  
* https://developer.android.com/guide/topics/resources/providing-resources  
* https://startandroid.ru/ru/uroki/vse-uroki-spiskom/60-urok-23-activity-lifecycle-v-kakih-sostojanijah-mozhet-byt-activity.html  
* https://startandroid.ru/ru/uroki/vse-uroki-spiskom/61-urok-24-activity-lifecycle-primer-smeny-sostojanij-s-dvumja-activity.html  
* https://startandroid.ru/ru/uroki/vse-uroki-spiskom/133-urok-70-onsaveinstancestate-sohranenie-dannyh-activity-pri-povorote-ekrana.html
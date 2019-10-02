# Черновик!
# Лабораторная работа №3. Lifecycle компоненты. Навигация в приложении.  
## Цели  
* Ознакомиться с методом обработки жизненного цикла activity/fragment при помощи Lifecycle-Aware компонентов    
* Изучить основные возможности навигации внутри приложения: созданиие новых activity, navigation graph  
  
## Задачи  
### Задача 1. Обработка жизненного цикла с помощью Lifecycle-Aware компонентов  
#### Задание  
Ознакомьтесь с Lifecycle-Aware Components по документации: https://developer.android.com/topic/libraries/architecture/lifecycle и выполните codelabs (ссылка внизу страницы в разделе codelabs)
  
#### Указания  
В отчете укажите, какую новую полезную информацию/навыки удалось усвоить/получить в процессе выполнения задания. 
  
### Задача 2. Навигация (Activity)
#### Задание
Реализуйте навигацию между экранами одного приложения согласно [варианту](VARIANTS02.md) с помощью Activity и Intent.
  
#### Указания
Для решения задачи могут понадобиться методы Activity (https://developer.android.com/reference/android/app/Activity.html#starting-activities-and-getting-results):
* `startActivity`, `startActivityForResult`
* `setResult`, `onActivityResult`
* `finish`

Каждую Activity необходимо зарегистрировать в файле AndroidManifest.xml. В отчете укажите, что произойдет, если забыть зарегистрировать Activity.


### Задача 3. Навигация (Fragments, Navigation Graph) 
Решите предыдущую задачу с использованием fragment и navigation graph. В отчете сравните два решения.

#### Указания
Ознакомьтесь с navigation graph по документации (https://developer.android.com/guide/navigation/navigation-getting-started) или видеоуроку (https://classroom.udacity.com/courses/ud9012 Lesson 3 “App Navigation”))
  
## Отчет  
Отчет должен содержать формулировку целей, решения задач с ответами на дополнительные вопросы из раздела «указания», выводы. Выводы должны содержать убедительное обоснование, почему автор отчета считает, что все цели были достигнуты. Например, обоснование может включать краткую информацию о том, как достигалась каждая из поставленных целей.

В отчете укажите примерное время решения каждой из предложенных задач, включая время ознакомления со справочными материалами (время решения не влияет на оценку: только на анонимный сбор статистики для корректировки сложности задач в будущем).
  
## Вспомогательные материалы:  
### Основные
* https://classroom.udacity.com/courses/ud9012 (Lesson 3 “App Navigation”)
* https://developer.android.com/reference/android/app/Activity.html#StartingActivities
* https://developer.android.com/topic/libraries/architecture/lifecycle

### Дополнительные (на русском языке)  
```
 _________________
|                 | 
| пока ничего нет |
|_________________|
        ||
  (\(\  ||
  ( -.-)づ
  O_(")(")
```
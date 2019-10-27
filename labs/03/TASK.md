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
  
### Задача 2. Навигация (startActivityForResult)
#### Задание
Реализуйте навигацию между экранами одного приложения согласно изображению ниже с помощью Activity, Intent и метода `startActivityForResult`.
![эскиз](activities.svg)


#### Пояснения
* Приложение начинает работу с отображения Activity 1
* Кнопка 'to first' отображает на экране Activity 1
* Кнопка 'to second' отображает на экране Activity 2
* Кнопка 'to third' отображает на экране Activity 3
* В любой момент в backstack любого task приложения должно быть не более 4 activity
* Во всех вариантах Activity 'About' должна быть доступна из любой другой Activity одним из способов согласно варианту.
* (27.10.2019) Activity в BackStack (кроме About) всегда остаются в одном и том же порядке: 1-2-3

#### Подсказки, частые ошибки 
* Для решения задачи не потребуется `getSystemService(Context.ACTIVITY_SERVICE)`
* Все решения (этой и следующих задач) должны приводить к одному и тому же графу навигации (за исключением очевидного отличия, вызванного задачей 4)
 
Используйте [эту подсказку](ANSWER02.md) только если не удалось решить задачу самостоятельно.


#### Варианты задания
Во всех вариантах Activity 'About' должна быть доступна из любой другой Activity одним из указанных способов:

Варианты 1,4,7,10,...: [Navigation Drawer](https://developer.android.com/guide/navigation/navigation-ui#add_a_navigation_drawer)

Варианты 2,5,8,11,...: [Bottom Navigation](https://developer.android.com/guide/navigation/navigation-ui#bottom_navigation)

Варианты 3,6,9,12,...: [Options Menu](https://developer.android.com/guide/topics/ui/menus#options-menu)

#### Указания
Для решения задачи понадобятся методы Activity (https://developer.android.com/reference/android/app/Activity.html#starting-activities-and-getting-results):
* `startActivity`, `startActivityForResult`
* `setResult`, `onActivityResult`
* `finish`

Каждую Activity необходимо зарегистрировать в файле AndroidManifest.xml. В отчете укажите, что произойдет, если забыть зарегистрировать Activity.

Не используйте флаги Intent и атрибуты Activity в этом задании.

### Задача 3. Навигация (флаги Intent/атрибуты Activity)
#### Задание
Решите предыдущую задачу с помощью Activity, Intent и флагов Intent либо атрибутов Activity.

#### Указания
Не используйте `startActivityForResult` в этом задании.

### Задача 4. Навигация (флаги Intent/атрибуты Activity)
Дополните граф навигации новым(-и) переходом(-ами) с целью демонстрации какого-нибудь (на свое усмотрение) атрибута Activity или флага Intent, который еще не использовался для решения задачи. Поясните пример и работу флага/атрибута.

Ограничение на размер backstack к этому и следующему заданию не применяется.

### Задача 5. Навигация (Fragments, Navigation Graph) 
Решите предыдущую задачу (с расширенным графом) с использованием navigation graph. Все Activity должны быть заменены на фрагменты, кроме Activity 'About', которая должна остаться самостоятельной Activity.
В отчете сравните все решения.

#### Указания
* Ознакомьтесь с navigation graph по документации (https://developer.android.com/guide/navigation/navigation-getting-started) или видеоуроку (https://classroom.udacity.com/courses/ud9012 Lesson 3 “App Navigation”))
* Для отображения layout в fragment используйте метод `onCreateView` (см. пример: https://developer.android.com/guide/components/fragments#UI).
* Регистрацию отбработки событий от кнопок выполняйте так же, как для Activity, но в методе fragment `onActivityCreated()`

  
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

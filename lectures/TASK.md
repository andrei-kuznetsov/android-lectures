# Содержание

1. [Задание t4](#задание-t4) (макс. 6 баллов) - программная реализация best match алгоритма
2. [Задание t11](#задание-t11) (макс. 6 баллов) - программная реализация intent resolution алгоритма
3. [Задание t17](#задание-t17) (макс. 2 балла) - разработка Broadcast Receiver
4. [Задание t18.1](#задание-t18.1) (макс. 2 балла) - разработка bound сервиса (LocalBinder)
5. [Задание t18.2](#задание-t18.2) (макс. 3 балла) - разработка bound сервиса (AIDL)
6. [Задание x1](#задание-x1) (макс. 3 балла) = `t17 + t18.1`

---

# Задание t4
## Основное задание
+1 балл, 80 мин

Напишите программную реализацию BestMatch алгоритма (не Android, а обычную java/kotlin программу).

Упрощения:
1. Парные квалификаторы можно рассматривать как независимые:
   * mcc+mnc
   * lang+reg
2. `b+` синтаксис языковых ресурсов можно не рассматривать
3. Можно считать, что `nodpi,tvdpi,anydpi,nnndpi` ресурсов не существует

## Пример входных данных и ожидаемого результата
```java
@Test
public void testE2E(){
    String device = "fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27";
    String res[] = {
            "",
            "rCA-night-trackball",
            "rUS-land-trackball-v25",
            "en-notround-port-12key",
            "rFR-large-notround-hdpi-notouch",
            "notlong-xxhdpi-notouch",
            "rCA-long-round-port-qwerty",
            "rUS-long-notnight-ldpi-nonav-v27",
            "fr-rUS-long-nokeys-trackball",
            "fr-television-night-v27",
            "en-large-night-hdpi-nokeys-v26"
    };

    String best = new Main().match(Arrays.asList(res), device);
    assertEquals("fr-television-night-v27", best);
}
```

Пояснения:
```
rCA-night-trackball contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: REG: CA vs US
rUS-land-trackball-v25 contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: PRIMARY_NONTOUCH_METHOD: trackball vs nonav
en-notround-port-12key contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: LANG: en vs fr
rFR-large-notround-hdpi-notouch contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: SCREEN_SIZE: large vs normal
notlong-xxhdpi-notouch contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: SCREEN_ASPECT: notlong vs long
rCA-long-round-port-qwerty contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: REG: CA vs US
rUS-long-notnight-ldpi-nonav-v27 contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: NIGHT_MODE: notnight vs night
fr-rUS-long-nokeys-trackball contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: PRIMARY_NONTOUCH_METHOD: trackball vs nonav
en-large-night-hdpi-nokeys-v26 contradicts to fr-rUS-normal-long-round-port-television-night-mdpi-notouch-qwerty-nonav-v27: LANG: en vs fr
```

Обратите внимание, что необходио реализовать best-match процедуру для выбора `dpi` и `screensize` ресурсов.

## Доп. задание
+5 баллов, 20 мин

Продемонстрировать разнообразные ситуации с помощью Unit-тестов.

## Вспомогательные материалы
Для ускорения разработки предлагается воспользоваться вспомогательным классом `ResGroups`.
Примеры использования:
```java
@Test
public void testMatch(){
     assertTrue(ResGroups.LANG.matches("fr"));
     assertFalse(ResGroups.DPI.matches("fr"));
}

@Test
public void testParseValue(){
   String r = ResGroups.PLATFORM_VER.parseValue("v26");
   assertEquals("26", r);
}

@Test
public void testSelection(){
   String sample = "rCA";

   for (ResGroups g : ResGroups.values()) {
      if (g.matches(sample)) {
         String parsed = g.parseValue(sample);
         assertEquals(ResGroups.REG, g);
         assertEquals("CA", parsed);
      }
   }
}
```

Реализация класса:
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ResGroups {
    MCC("mcc(\\d+)"),
    MNC("mnc(\\d+)"),
    LANG("([a-zA-Z]{2})"),
    REG("r([a-zA-Z]{2})"),
    LAYOUT_DIRECTION("(ldrtl|ldltr)"),
    SMALLEST_WIDTH("sw(\\d+)dp"),
    AVAILABLE_WIDTH("w(\\d+)dp"),
    AVAILABLE_HEIGHT("h(\\d+)dp"),
    SCREEN_SIZE("(small|normal|large|xlarge)"),
    SCREEN_ASPECT("(long|notlong)"),
    ROUND_SCREEN("(round|notround)"),
    WIDE_CG("(widecg|nowidecg)"),
    HDR("(highdr|lowdr)"),
    SCREEN_ORIENTATION("(port|land)"),
    UI_MODE("(car|desk|television|appliance|watch|vrheadset)"),
    NIGHT_MODE("(night|notnight)"),
    DPI("(ldpi|mdpi|hdpi|xhdpi|xxhdpi|xxxhdpi)"),
    TOUCHSCREEN_TYPE("(notouch|finger)"),
    KEYBOARD_AVAILABILITY("(keysexposed|keyshidden|keyssoft)"),
    PRIMARY_TEXT_INPUT("(nokeys|qwerty|12key)"),
    NAVKEY_AVAILABILITY("(navexposed|navhidden)"),
    PRIMARY_NONTOUCH_METHOD("(nonav|dpad|trackball|wheel)"),
    PLATFORM_VER("v(\\d+)");

    private final Pattern pattern;

    ResGroups(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public static final class ParsedGroup {
        private final ResGroups group;
        private final String value;

        public ParsedGroup(ResGroups group, String value) {
            this.group = group;
            this.value = value;
        }

        public ResGroups getGroup() {
            return group;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return group + ", value=" + value;
        }

        public int getValueAsInt() {
            return Integer.parseInt(value);
        }
    }

    public static ParsedGroup parse(String q) {
        for (ResGroups g : ResGroups.values()) {
            if (g.matches(q)) {
                return new ParsedGroup(g, g.parseValue(q));
            }
        }
        throw new IllegalStateException("Unrecognized qualifier: " + q);
    }

    public boolean matches(String q) {
        return pattern.matcher(q).matches();
    }

    public String parseValue(String q) {
        Matcher matcher = pattern.matcher(q);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            throw new IllegalStateException("Does not match: " + q + ", " + this);
        }
    }
}
```

---

# Задание t11
## Основное задание

+1 балл, 80 мин

Напишите программу (не Android, а обычную java/kotlin), которая для заданных экземпляров классов `MyIntent` и 
`MyIntentFilter` возвращает `true` если интент подходит под фильтр, и `false` в противном случае.

```java

import java.net.URI;

public class MyIntent {
   private String action;
   private final Set<String> categories = new HashSet<>();
   private URI data;
   private String type;
   private int flags;
   private final Map<String, Object> extras = new HashMap<>();
   
   // getters & setters omitted
}

class MyIntentFilter {
   private final Set<String> actions = new HashSet<>();
   private final Set<String> categories = new HashSet<>();
   private String scheme;
   private String host;
   private String port;
   private String path;
   private String type;

   // getters & setters omitted
}
```

Для упрощения будем считать, что в фильтре может быть указано не более одного URI, путь которого (если присутствует) 
не содержит масок (`*`).

MIME тип не содержит масок (достаточно проверить точное совпадение типа). Будем считать, что выводить MIME тип
из URI не нужно.

## Доп. задание
+5 баллов, 20 мин

Продемонстрировать разнообразные ситуации с помощью Unit-тестов.

## Пример входных данных и ожидаемого результата

```java
    @Test
    public void e2eTest() throws Exception {
        MyIntent i = new MyIntent();
        MyIntentFilter f = new MyIntentFilter();
        Solution s = new Solution();

        i.setData(new URI("http://youtube.com/"));
        i.setType("video/mpeg");
        
        f.setScheme("http");
        f.setType("video/mpeg");

        Assert.assertFalse(s.match(i, f));
        // If the filter does not list any actions, there is nothing for an intent to match, so all intents fail the test.
    }
```

---

# Задание t17
## Основное задание

+1 балл, 10 мин

Напишите приложение, которое подписывается на `android.intent.action.BATTERY_CHANGED` бродкаст и выводит сообщение 
с уровнем заряда батареи в лог.

Приложение должно корректно работать на устройствах Android 8+ (API 26+)

Продемонструйте работу приложения.

## Доп. задание
+1 балл, 15 мин

Напишите приложение, которое подписывается на `android.provider.Telephony.SMS_RECEIVED` бродкаст и выводит
текст SMS-сообщения в лог.

Приложение должно корректно работать на устройствах Android 8+ (API 26+)

Продемонструйте работу приложения.

---

# Задание t18.1
## Основное задание
+1 балл, 35 мин

Написать BoundService, имеющий единственный метод `int nextRandomInt()`, 
возвращающий случайное число. Сервис должен поддерживать многопоточную работу
и быть способным обрабатывать только запросы от клиентов, находящихся в том же процессе.

Продемонстрировать работу сервиса на примере простого андроид-приложения.

## Доп. задание
+1 балл, 15 мин

* написать тест на сервис (ServiceTestRule)
* написать тест на демонстрационное приложение (ActivityTestRule)

---

# Задание t18.2
## Основное задание
+1 балл, 35 мин

Написать AIDL-BoundService, имеющий единственный метод `int nextRandomInt()`,
возвращающий случайное число.

Продемонстрировать работу сервиса на примере простого андроид-приложения.

## Доп. задание
+2 балла, 15 мин

* написать тест на сервис (ServiceTestRule)
* написать тест на демонстрационное приложение (ActivityTestRule)

---

# Задание x1
## Основное задание

+3 балла, 80 мин

Выполните основные и дополнительные задания t17 и t18.1 

---

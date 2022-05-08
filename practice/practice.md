## Légitársaság

Hozz létre egy 3 rétegű alkalmazást, amivel egy légitársaság működését tudod szimulálni.

### Általános információk

Haladj sorban a részfeladatokkal, csak akkor bővítsd tovább új funkciókkal az alkalmazást, ha az előző már készen van (
azaz először legyen meg minden a repülőgépekkel kapcsolatban, aztán a repterekkel, és így tovább).  
Ha előre várható hibára futna az alkalmazás (például validációnál), akkor mindig küldj vissza a kliensnek hibaleírást!
Törekedj rá, hogy ezt valamilyen általános megoldással érd el!  
A beérkező HTTP kéréseket logold, az útvonalban szereplő ID-val (ha van) és a body-ban szereplő adatokkal (szintúgy, ha
vannak)!

---

### Repülőgép

Az alkalmazásban lehessen tárolni repülőgépek adatait. Minden repülőgépről tudjuk az alábbiakat:

- belső id (int, egyedi, az alkalmazás osztja ki sorban)
- azonosító (String)
- gyártó (String, de opcionálisan lehet enummal is)
- típus
- férőhelyek száma

Repülőgépekkel kapcsolatban elérhető mind az 5 alapművelet (listázás, id szerint keresés, mentés, módosítás törlés).  
A törlés csak logikai törlés legyen! Logikai törlést követően sem id alapján, sem listázáskor ne jelenjen meg a
repülőgép, és módosításkor is azt a hibaüzenetet add vissza, hogy nincs ilyen id-jú repülő elmentve az alkalmazásban.

Mentéskor és módosításkor is validálj az alábbiakra:

- az azonosító nem lehet null, üres String, vagy csak üres karaktereket tartalmazó String
- a gyártó nem lehet null, és ha Stringben tárolod, akkor lehet üres vagy csak üres karaktereket tartalmazó
- a típus nem lehet null, üres String, vagy csak üres karaktereket tartalmazó String
- a férőhelyek száma min 10 és maximum 853 lehet

---

### Reptér

Az alkalmazásban lehessen eltárolni repterek adatait. Minden reptérről tudjuk az alábbiakat:

- belső id (int, egyedi, az alkalmazás osztja ki sorban)
- azonosító (String)
- név (String)
- város (String)
- ország (String)

Repterekkel kapcsolatban elérhető mind az 5 alapművelet, a törlés csak logikai törlés legyen!

Mentéskor és módosításkor is validálj arra, hogy a paraméterben kapott Stringek nem nullok, üres Stringek vagy csak üres
karaktereket tartalmaznak!

---

### Járat

Az alkalmazásban el lehet tárolni konkrét járatokat, amikről az alábbiakat tudjuk:

- belső id (int, egyedi, az alkalmazás osztja ki sorban)
- azonosító (String)
- repülőgép (Repülőgép típus, de a Command dto-ban csak az ID-ját kérd be)
- indulási hely (Reptér típus, de a Command dto-ban csak az ID-ját kérd be)
- érkezési hely (Reptér típus, de a Command dto-ban csak az ID-ját kérd be)
- indulás ideje (LocalDateTime)
- érkezés ideje (LocalDateTime)

Járatot lehessen id alapján keresni, az összeset listázni és menteni!  
Lehessen járatot módosítani, de csak az indulási és érkezési időt lehessen megváltoztatni!

Mentéskor és módosításkor is validálj az alábbiakra:

- az azonosító nem null, üres String vagy csak üres karaktereket tartalmazó String
- az indulás és érkezés ideje nem null, és mindkettő jövőbeli (arra nem kell ellenőrizni, hogy a gép előbb indul, mint
  hogy megérkezne)

Ha akár a repülőgép, akár az indulási vagy érkezési hely nem található mentésnél a kapott ID alapján, akkor adj vissza
hibaüzenetet a kliens számára, 400 BAD REQUEST hibakóddal, és ugyanabban a formátumban, mint ha validációnál történt
volna a hiba. (Tipp: csinálj egy egyedi exceptiont, amit el tudsz dobni a Service rétegben, és írj rá
ExceptionHandlert!)

---

### Jegyfoglalás

Utolsó funkcióként lehessen jegyet foglalni, amiről mentsük el az alábbi adatokat:

- belső id (int, egyedi, az alkalmazás osztja ki sorban)
- név (String)
- email (String)
- járat (Járat típus, de a Command dto-ban csak az ID-ját kérd be)

Foglalást lehessen listázni, id alapján keresni, menteni és törölni! A törlés legyen logikai törlés, de arra figyelj,
hogy ne foglalja a járaton a helyet utána!  
Lehessen módosítani is, de csak a nevet és az emailt!

Validálj az alábbiakra:

- a név nem null, üres String vagy csak üres karaktereket tartalmazó String
- az email nem null, és megfelel az email formátumnak

Mentéskor ellenőrizd le, hogy van-e még hely az adott járaton, és ha nincs, akkor dobj vissza hibát a kliens számára (
400 BAD REQUEST-tel)!

---

### Opcionális: Járat törlése

Lehessen törölni járatokat, ami törölje az arra már leadott foglalásokat is!

---

### Opcionális 2: ZonedDateTime

Állj át LocalDateTime használatáról ZonedDateTime használatára!
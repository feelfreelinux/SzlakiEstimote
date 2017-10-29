# SzlakiEstimote
Aplikacja "Szlaki Estimote" pozwala na projektowanie własnych szlaków turystycznych opartych o "Zabytki". "Zabytek" to miejsce w terenie, np.: atrakcja turstyczna z przypisanym opisem, lokalizacją oraz indentyfikatorem. Dane te znajdują się w prostej bazie danych, zawierającej id beacona danego punktu, oraz informacje o nim. Punkty są tworzone przez administratora, a ich lista jest niezależna od aplikacji - jest pobierana z sieci. Użytkownicy mogą łączyć te punkty w "szlaki". "Szlak" to lista beaconów, z krótkim quizem stworzonym przez autora szlaku. Użytkownicy którzy zdecydują się na podjęcie szlaku, dostaną lokalizację pierwszego z nich(z możliwością prowadzenia przez google maps). Gdy gracz dotrze do miejsca wyznaczonego w aplikacji, aplikacja wykryje to poprzez komunikacje z beaconami o odpowiednim id, oraz odblokuje quiz dla danego miejsca. Gdy gracz odpowie na pytania zadane w quizie, jego wynik jest zliczany i dodawany do sumy punktów. Wtedy odblokowuje się kolejne miejsce (Użytkownik nie zna lokalizaji miejsc których nie odblokował). Użytkownik może także podzielić się ilością punktów poprzez wiadomość tekstową w wybranym komunikatorze.

Problemy:
Jako że nie miałem zbyt dużo czasu na napisanie aplikacji, to zdecydowałem się zastąpić baze danych wklejkami Github Gist. Ich adresu url są podane w retroficie, więc zastąpienie ich prawdziwym api nie powinno być problemem. Załączony plik apk posiada ustawiony adres na dwa beacony które dostałem do budowy aplikacji. Oczywiście rozszerzanie aplikacji o kolejne miejsca wymaga aktualizacji wklejki w serwisie github. 
Aplikacje oparłem o architekture MVP wraz z biliotekami:
- rxJava2 / rxAndroid
- Retrofit
- Gson
- Dagger2
- Język programowania : Kotlin

Niestety zabrakło mi czasu na zaimplementowanie tej architektury w wszyskich ekranach, i niektóre activity posiadają dużo bałaganu w activity. Aplikacja nie jest duża, wię poprawienie tego poza hackathonem nie będzie problemem.
Aplikacja miała się opierać o wymianę szlaków między użytkownikami - udostępnione szlaki powinny być wysyłane do bazy danych, lecz z powodu wykorzystania github gist nie miałem czasu ani możliwości zaimplementowania tego. Za to dodalem możliwość udostępniania szlaków w osobnych wklejkach, które są automatycznie wysyłane do serwisu Github Gist, oraz skracane poprzez google url shortener. Chciałem napisać obsługę importowania tych linków w aplikacji, ale zrobiło by to bałagan poniewasz aplikacja ma się opierać o jedną bazę danych. Kolejną rzeczą którą mógłbym poprawić, to edycja pytań - narazie nie ma możliwosci edytowania pytania po jego utworzeniu.

Struktura odpowiedzi JSON:
Odpowiedź z listą szlaków posiada taką strukturę json: https://gist.github.com/feelfreelinux/c45bef3bcb63a27c813f7cb16297c6a4
A lista "Zabytków": https://gist.github.com/feelfreelinux/47cad9d961fa4c45309920a5e155a261

Aby dodać nowy zabytek należy dodać go do listy zabytków, i stworzyć nowy szlak.

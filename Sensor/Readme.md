Aby korzystać z Sensora musimy mieć zainstalowane następujące programy i biblioteki:
- CMake w wersji co najmniej 3.3
https://cmake.org/
- Boost w wersji co najmniej 1.52
http://www.boost.org/
- Sigar w wersji co najmniej 1.64
https://support.hyperic.com/display/SIGAR/Home
- yamlcpp
http://yaml.org/
- jsoncpp
https://github.com/open-source-parsers/jsoncpp

CMake instalujemy poprzez skrypt sh, albo wypakowujemy z tarballa i dodajemy ścieżkę do pliku wykonywalnego cmake do zmiennej PATH
export PATH=$PATH:/ścieżka/do/pliku/wykonywalnego/cmake/

Sigar, yamlcpp i jsonncpp instalujemy następująco:
1) Klonujemy repo z gita
2) W katalogu w którym jest plik CMakeLists.txt wywołujemy:
    mkdir build
    cd build
    cmake ..
3) Jeżeli instalacja przebiegła poprawnie wchodzimy do katalogu build i wywołujemy:
    make all
    sudo make install
    (Jeżeli ktoś nie ma praw sudo trzeba będzie zainstalować lokalnie i pokombinować z CMakeLists Sensora)
I to wszystko. Instalacja boosta jest opisana na stronie.
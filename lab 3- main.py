from typing import List

from prettytable import PrettyTable

from automat import Automat, Tranzitie


def _afisare_tranzitii(tranzitii: List[Tranzitie]) -> None:
    tabel = PrettyTable(["Stare inceput", "--simbol-->", "Stare sfarsit"])
    for tranzitie in tranzitii:
        tabel.add_row([tranzitie.stare_inceput, tranzitie.simbol, tranzitie.stare_sfarsit])
    print(tabel)


def afisare_automat(automat):
    print(f"MULTIMEA STARILOR: {automat.stari}")
    print(f"ALFABETUL: {automat.alfabet}")
    print(f"STARI FINALE: {automat.stari_finale}")
    print("TRANZITII:")
    _afisare_tranzitii(automat.tranzitii)


def afisare_meniu():
    print("""
1. Incarca configuratie automat finit
2. Afisare automat finit
3. Calculaza urmatoarea stare dand o satre initiala si un simbol
4. Verifica secventa

0. Exit
    """)


if __name__ == '__main__':
    automat_finit = Automat()

    while True:
        afisare_meniu()
        comanda = int(input("Comanda: "))

        if comanda == 1:
            configuratie = input("Cale configuratie: ")
            print(f"Initializare automat finit cu configuratia '{configuratie}'...")
            automat_finit.incarca_configuratie(configuratie)
            print("Configuratia a fost incarcata cu success\n")
            afisare_automat(automat_finit)

        elif comanda == 2:
            afisare_automat(automat_finit)

        elif comanda == 3:
            stare_curenta = input("Stare curenta: ")
            simbol = input("Simbol: ")
            try:
                urmatoarea_stare, tranzitie = automat_finit.get_urmatoarea_stare(stare_curenta, simbol)
                print(f"Urmatoarea stare: {urmatoarea_stare}")
                _afisare_tranzitii([tranzitie, ])
            except ValueError as e:
                print(f"EROARE: {e}")

        elif comanda == 4:
            secventa = input("Secventa: ")
            valid, tranzitii = automat_finit.verificare_secventa(secventa)
            if valid:
                print("Secventa este ACCEPTATA")
            else:
                print("Secventa NU ESTE ACCEPTATA")
            print("Tranzitii folosite:")
            _afisare_tranzitii(tranzitii)

        elif comanda == 0:
            exit(0)





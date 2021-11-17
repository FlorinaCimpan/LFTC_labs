from typing import List


class Tranzitie:
    """
    Classa asociata unei tranzitii
    """
    def __init__(self, stare_inceput: str, stare_sfarsit: str, simbol: str):
        self.stare_inceput = stare_inceput
        self.stare_sfarsit = stare_sfarsit
        self.simbol = simbol


class Automat:
    """
    Clasa ascociata unui automat finit
    """

    def __init__(self):
        self.stari = ()  # multime pentru a avea toate proprietatile unei multimi
        self.stari_finale = ()
        self.stare_initiala = None
        self.alfabet = ()
        self.tranzitii = []

    def incarca_configuratie(self, path: str) -> None:
        with open(path, 'r') as file:
            self.stari = set(file.readline().strip().split(','))
            self.alfabet = set(file.readline().strip().split(','))
            self.stare_initiala = file.readline().strip()
            self.stari_finale = set(file.readline().strip().split(','))
            for line in file.readlines():
                stare_inceput = line.strip().split(',')[0]
                stare_sfarsit = line.strip().split('-')[-1]
                tranzitie = line.strip().split(',')[-1].split('-')[0]
                self.tranzitii.append(Tranzitie(stare_inceput, stare_sfarsit, tranzitie))

    def get_urmatoarea_stare(self, stare_curenta: str, simbol: str) -> (str, Tranzitie):
        if stare_curenta not in self.stari:
            raise ValueError(f"Starea '{stare_curenta}' nu se afla in multimea starilor {self.stari}")
        if simbol not in self.alfabet:
            raise ValueError(f"Simbolul '{simbol}' nu se afla in alfabet {self.alfabet}")

        for tranzitie in self.tranzitii:
            if tranzitie.stare_inceput == stare_curenta and tranzitie.simbol == simbol:
                return tranzitie.stare_sfarsit, tranzitie

        raise ValueError(f"Nu s-a putut gasi o tranzitie din starea '{stare_curenta}' cu simbolul '{simbol}'")

    def verificare_secventa(self, secventa: str) -> (bool, List[Tranzitie]):
        stare_curenta = self.stare_initiala
        tranzitii_folosite = []
        for simbol in secventa:
            try:
                urmatoarea_stare, tranzitie = self.get_urmatoarea_stare(stare_curenta, simbol)
                tranzitii_folosite.append(tranzitie)
                stare_curenta = urmatoarea_stare
            except ValueError as e:
                print(e)
                return False, tranzitii_folosite

        if stare_curenta in self.stari_finale:
            return True, tranzitii_folosite
        else:
            return False, tranzitii_folosite




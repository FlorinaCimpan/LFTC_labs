import sys
from pprint import pprint

CODIFICARE = {
        "identificator": 0,
        "constanta": 1,
        "program": 2,
        "array": 3,
        "of": 4,
        "var": 5,
        "integer": 6,
        "real": 7,
        "boolean": 8,
        "{": 9,
        "}": 10,
        "read": 11,
        "write": 12,
        "for": 13,
        "while": 14,
        "do": 15,
        "if": 16,
        "then": 17,
        "else": 18,
        "and": 19,
        "or": 20,
        "not": 21,
        ":": 22,
        ";": 23,
        ",": 24,
        ".": 25,
        "+": 26,
        "*": 27,
        "(": 28,
        ")": 29,
        "[": 30,
        "]": 31,
        "-": 32,
        "<": 33,
        ">": 34,
        "==": 35,
        "<--": 36,
        "-->": 37
    }


def get_atomi(input_code):
    while "  " in input_code:
        input_code = input_code.replace("  ", " ")
    return input_code.split(' ')


def read_input_text(input_path):
    input_text = ""
    with open(input_path, 'r') as f:
        for line in f.readlines():
            input_text = input_text + " " + line.strip()
    return input_text.strip()


def is_identificator(atom):
    return atom.isalnum() and atom[0].isalpha()

def is_constant(atom):
    return atom.isnumeric() or (len(atom)>=2 and atom[0]==atom[-1]=='"')
        


if __name__ == "__main__":
    input_file_path = sys.argv[1]
    input_text = read_input_text(input_file_path)

    ATOMI = get_atomi(input_text)
    FIP = []
    TS = {}

    for atom in ATOMI:
        
        if atom.lower() in CODIFICARE:
            FIP.append((CODIFICARE[atom.lower()], -1))
            
        elif is_identificator(atom):
            if atom not in TS:
                TS[atom] = len(TS) + 100
            FIP.append((CODIFICARE["identificator"], TS[atom]))
            
        elif is_constant(atom):
            if atom not in TS:
                TS[atom] = len(TS) + 200
            FIP.append((CODIFICARE["constanta"], TS[atom]))

        else:
            raise ValueError(f"Expresia '{atom}' nu este un atom identificabil")

    print("======== FIP ========")
    pprint(FIP)
    
    print("\n============ TS ===========")
    pprint(TS)
            
            
            
    
    

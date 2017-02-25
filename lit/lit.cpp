#include <iostream>
#include <string>
#include <cstdlib>
using namespace std;

// written by Greg Willard (r3pwn)

void printHeader(void);
void DEBUG(string var1, string var2, string var3);
int post(string var1, string var2, string var3);

int main(void)
{
    start:
    system("clear");
    string firstName, lastName, email;
    printHeader();
    cout << "Enter your first and last name: ";
    cin >> firstName >> lastName;
    cout << "Enter your email address: ";
    cin >> email;
    post(firstName, lastName, email); //TODO: fix <------
    DEBUG(firstName, lastName, email);
    cout << "\nThank you for signing in, and enjoy the hackathon!\n";
    system("sleep 7");
    goto start;
}

void printHeader(void)
{
    // don't worry how terribly distorted this looks, it's fine, trust me.
    cout << "    _____      _       _    _            _          ___   ___  __ ______\n"; 
    cout << "   |  __ \\    | |     | |  | |          | |        |__ \\ / _ \\/_ |____  |\n";
    cout << "   | |__) |__ | |_   _| |__| | __ _  ___| | _____     ) | | | || |   / /\n";
    cout << "   |  ___/ _ \\| | | | |  __  |/ _` |/ __| |/ / __|   / /| | | || |  / /\n";
    cout << "   | |  | (_) | | |_| | |  | | (_| | (__|   <\\__ \\  / /_| |_| || | / /\n";
    cout << "   |_|   \\___/|_|\\__, |_|  |_|\\__,_|\\___|_|\\_\\___/ |____|\\___/ |_|/_/\n";
    cout << "                  __/ |\n";
    cout << "                 |___/\n\n";
}

void DEBUG(string firstName, string lastName, string email)
{
    cout << "\nDEBUG: firstName: " << firstName; 
    cout << "\nDEBUG: lastName: " << lastName;
    cout << "\nDEBUG: email: " << email << endl;
}

int post(string nameFirst, string nameLast, string email)
{
    string out = "curl -s -X POST -F \"entry.114471265=" + nameFirst + "\" -F \"entry.358158617=" + 
                 nameLast + "\" -F \"entry.1738677313=" + email + "\" -F \"submit=Submit\" https://docs.google.com/forms/d/e/1FAIpQLSf9I-k-A9PS1XjfJXSJr030nH8PbE-B5HvsTSCtJSo0F4S2Qw/formResponse > /dev/null";
    system(out.c_str());
}
